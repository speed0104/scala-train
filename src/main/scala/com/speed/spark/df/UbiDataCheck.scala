package com.speed.spark.df

import java.util.Properties

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.sql.{DataFrame, SQLContext, SaveMode}
import org.apache.spark.sql.functions._
import org.apache.spark.sql.hive.HiveContext
import org.apache.spark.sql.types.{IntegerType, LongType, StringType, TimestampType}

object UbiDataCheck {

  def main(args: Array[String]): Unit = {

    val conf = new SparkConf()
    val inputsql = args(0)
    //    要在集群上提交需要去掉Local
    conf.setMaster("local").setAppName("UbiDataCheck")

    val sc = new SparkContext(conf)
    val sqlContext = new SQLContext(sc)
    val hiveContext = new HiveContext(sc)
    hiveContext.sql("use hkgc_tms_mstr")

//    val df = hiveContext.sql("select vin,ign_on_time,n,vs,t,sas_angle,latitude,longitude from vcrm20_rt_log where p_date = '20190103' and vin = 'LBES6AFD8JW008964' and ign_on_time = '2019-01-03 09:01:29.585'")
    val df = hiveContext.sql(inputsql)

    println("去重前： " + df.count())
    println("去重前分区数" + df.rdd.getNumPartitions)
//    df.dropDuplicates(Seq("vin","ign_on_time")).show(12,false)
//    df.dropDuplicates().show(12,false)
    df.show(5,false)


    println("开始UBI数据处理逻辑！")
    val dfCached: DataFrame = df.cache()
    // 系统时间
    import org.apache.spark.sql.expressions._
    val part_vin = Window.partitionBy("vin","ign_on_time")
    val part_vin_ntil= Window.partitionBy("vin","ign_on_time","speed_ntil")

    //    原始数据清洗
    /*
    * 添加原始数据清洗模块
    * 1、行程数据去重
    * 2、trip数据从第一次转速不为零的开始
    * 3、avg_speed、mileage数据类型转换成double
    * 4、不正常的点火时间行程数据去掉
    * 5、q_speed 为255的数据去掉
    * 6、速度大于220的行程数据去掉
    * */


    val repatitiondf: DataFrame = dfCached.repartition(200)

    val distinctdf: DataFrame = repatitiondf.dropDuplicates()

    println("去重后： " + distinctdf.count())
    println("去重后分区数" + distinctdf.rdd.getNumPartitions)

    val nstatuedf: DataFrame = distinctdf
      .withColumn("nStatue",when(col("n") > 0, 1).otherwise(0))
      .withColumn("driveStatue",sum("nStatue").over(part_vin.orderBy("t")))

    //    val drivedf: DataFrame = nstatuedf.where("driveStatue > 0 and ign_on_time < now() and ign_on_time > '2018-10-01'")

    //    val aggDir: DataFrame = drivedf
    //      .groupBy("vin", "ign_on_time")
    //      .agg(
    //        max("vs") as "maxVS"
    //      ).where("maxVS < 220").select("vin", "ign_on_time")
    //    val cleanedDf: DataFrame = drivedf.join(aggDir, Seq("vin","ign_on_time"), "inner")

    val dCached: DataFrame = nstatuedf.cache()
    //字段清洗提取
    val df2: DataFrame = dCached
      .where("driveStatue >0 and ign_on_time < now() and ign_on_time > '2018-10-01'")
      .select("vin", "ign_on_time", "t", "longitude", "latitude", "sas_angle", "vs")
      .withColumn("gps",concat_ws(",", col("latitude"), col("longitude")).cast(StringType))
      .withColumn("start_gps",first("gps").over(part_vin.orderBy(asc("t"))))   //开始GPS
      .withColumn("end_gps",first("gps").over(part_vin.orderBy(desc("t"))))   //结束GPS
      .withColumn("acc_kph",lead("vs",1,0).over(part_vin.orderBy(asc("t"))) - col("vs"))  //acc_kph
      .withColumn("acc_sas",lead("sas_angle",1,0).over(part_vin.orderBy(asc("t"))) - col("sas_angle"))  //acc_sas
      .withColumn("vs40", when(col("vs") <= 40 ,col("vs") * 1000 / 3600).otherwise(0))
      .withColumn("vs80",when(col("vs") > 40  and col("vs") <= 80 ,col("vs") * 1000 / 3600).otherwise(0))
      .withColumn("vs100",when(col("vs") > 80  and col("vs") <= 100 ,col("vs") * 1000 / 3600).otherwise(0))
      .withColumn("vs120",when(col("vs") > 120,col("vs") * 1000 / 3600).otherwise(0))
      .withColumn("speed_ntil",ntile(20).over(part_vin.orderBy(asc("vs"))))
      .withColumn("q_speed", concat_ws("_", max("vs").over(part_vin_ntil).cast(IntegerType),col("speed_ntil")) )

    //字段计算
    val df2_drvDur_Spe: DataFrame = df2
      .groupBy("vin", "ign_on_time")
      .agg(
        max("vs") as "maxVS",
        (min("t")+col("ign_on_time").cast(LongType)).cast(TimestampType).cast(StringType) as "start_time", //开始时间
        (max("t")+col("ign_on_time").cast(LongType)).cast(TimestampType).cast(StringType) as "end_time",  //结束时间,
        max("start_gps") as "start_gps",            //起始GPS
        max("end_gps") as "end_gps",                    //起始GPS
        count("t").cast(IntegerType) as "drive_duration",     //驾驶时长
        avg("vs" ) as "avg_speed",      //平均速度 转换成double
        (sum("vs" ) * 1000 / 3600 ) as "drive_mileage",     //驾驶里程
        sum("vs40") as "speed0_40",           //驾驶里程 〈40
        sum("vs80") as "speed40_80",             //驾驶里程  40，80
        sum("vs100") as "speed80_100",              //驾驶里程  80，100
        sum("vs120") as "speedover120",                 //驾驶里程 〉120
        sum(when(df2("vs") >= 30 and df2("acc_sas") >= 60,1).otherwise(0)) as "sharp_turn_count",  //急转弯次数
        sum(when(df2("acc_kph") * 1000 / 3600 > 3 ,1).otherwise(0)) as "acc_count",           //急加速次数
        sum(when(df2("acc_kph") * 1000 / 3600 < - 3  ,1).otherwise(0)) as "break_count" ,   //急减速次数
        variance("vs") as "speed_variance",               //平方差
        regexp_replace(regexp_replace(regexp_replace(concat_ws(",",collect_set( "q_speed").cast(StringType)),"_[0-9]*",""),"\\[",""),"\\]","") as "q_speed"    //速度分位数
      )

    val dfoutput: DataFrame = df2_drvDur_Spe
      .where("maxVS < 220")
      .select("vin", "start_time", "start_gps", "end_time", "end_gps", "drive_duration", "drive_mileage",
        "acc_count", "break_count", "sharp_turn_count", "avg_speed", "q_speed", "speed0_40", "speed40_80", "speed80_100", "speedover120", "speed_variance")

    dfoutput.show()

    val properties = new Properties()
    properties.setProperty("user", "bluelink")
    properties.setProperty("password", "bluelink")
    dfoutput.write.mode(SaveMode.Append).jdbc("jdbc:postgresql://10.109.46.33/testing", "ubi_data", properties)

    sc.stop()


  }

}
