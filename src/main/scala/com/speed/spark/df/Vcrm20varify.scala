package com.speed.spark.df

import java.util.Properties

import org.apache.spark.sql.{DataFrame, SQLContext, SaveMode}
import org.apache.spark.sql.hive.HiveContext
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.sql.functions.udf

import scala.io.Source

object Vcrm20varify {

  def main(args: Array[String]): Unit = {


    println("start=========================================")

    val conf = new SparkConf()
    //    要在集群上提交需要去掉Local
    //    conf.setMaster("local").setAppName("HiveSource")
    val sc = new SparkContext(conf)
    //    val sqlContext = new SQLContext(sc)

    /**
      * HiveContext是SQLContext的子类。
      */
    val hiveContext = new HiveContext(sc)
    //    hiveContext.sql("use test")

    //查询传入的SQL
    /*    val inputpath = args(0)

          println(inputpath)

          //文件读取
          val file=Source.fromFile(inputpath)

          var sql_string = ""

          for(line <- file.getLines)
          {
              sql_string += " "  + line

          }

          file.close*/


    var sql_string = args(0)

    val usql = "select * from hkgc_tms_mstr.vcrm20_rt_log where p_date in ('20181217','20181218','20181219')"

    println(usql)
    val df = hiveContext.sql(usql)

    df.show(10, false)

    val cache_df2 = df.cache()

    cache_df2.registerTempTable("vcrm20_rt_log")

    println(sql_string)
    val ubidf: DataFrame = hiveContext.sql(sql_string)

    ubidf.show(10, false)


    /*    val reader = sqlContext.read.format("jdbc")
        reader.option("url", "jdbc:postgresql://10.109.46.33/vcrm20")
        reader.option("driver","org.postgresql.Driver")
        reader.option("user","vcrm20")
        reader.option("password","vcrm20")
        reader.option("dbtable", "users")
        val users = reader.load()
        users.show()
        users.registerTempTable("test_users")
        val result = sqlContext.sql("select * from test_users limit 10")
        result.show()*/

    /**
      * 将数据写入到Mysql表中
      */
    val inputtable = args(1)

    val properties = new Properties()
    properties.setProperty("user", "vcrm20")
    properties.setProperty("password", "vcrm20")
    ubidf.write.mode(SaveMode.Append).jdbc("jdbc:postgresql://10.109.46.33/vcrm20", inputtable, properties)

    sc.stop()

  }

}
