package com.speed.spark.df

import java.util.Properties

import org.apache.spark.sql.{DataFrame, SQLContext, SaveMode}
import org.apache.spark.sql.hive.HiveContext
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.sql.functions.udf

import scala.io.Source

object HiveToPostgreUBIVcrm1 {

  def main(args: Array[String]): Unit = {
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
    val date_args = args(0)

      println(date_args)

      //文件读取
 /*     val file=Source.fromFile(inputpath)

      var sql_string = ""

      for(line <- file.getLines)
      {
          sql_string += " "  + line

      }


      println(sql_string)

      file.close*/

    val usql = "select * from hkgc_tms_mstr.vcrm10_rt_log where p_date = '"+  date_args +"'"

    println(usql)

    val df = hiveContext.sql(usql)

    df.show(10,false)

    val cache_df = df.cache()

    cache_df.registerTempTable("vcrm10_rt_log")

    val df_sql =
      " SELECT '" + date_args + """' as p_date, b.vin,from_unixtime(unix_timestamp(b.ignitiontime)) start_time,b.start_gps,a.end_time,b.end_gps,
                 |a.driver_duration,a.driver_mileage,a.acc_count,a.break_count,a.sharp_turn_count,a.avg_speed,a.q_speed,a.speed0_40,a.speed40_80,a.speed80_100,a.speedover120,a.speed_variance
                 |FROM
                 |(SELECT A.vin,A.ignitiontime,A.acc_count,A.break_count,A.sharp_turn_count,A.driver_duration,A.driver_mileage,
                 |A.avg_speed,A.speed0_40,A.speed40_80,A.speed80_100,A.speedover120,A.speed_variance,b.q_speed,A.end_time
                 |from
                 |(SELECT
                 |    A.vin,
                 |    A.ignitiontime,
                 |    A.end_time,
                 |    SUM (
                 |        CASE WHEN vs >= 30 AND acc_sas >= 60 THEN 1 ELSE 0 END
                 |    ) AS sharp_turn_count,
                 |    SUM (
                 |        CASE WHEN acc_kph * 5 / 18 > 3 THEN 1 ELSE 0 END
                 |    ) AS acc_count,
                 |    SUM (
                 |        CASE WHEN acc_kph * 5 / 18 < - 3 THEN 1 ELSE 0 END
                 |    ) AS break_count,
                 |    count(1) driver_duration,
                 |    sum(vs)/ 3600 * 1000 driver_mileage,
                 |    avg(vs) avg_speed,
                 |    sum(case when vs <= 40 then vs else 0 end)/ 3600 * 1000 as speed0_40,
                 |    sum(case when vs <= 80 and vs > 40 then vs else 0 end)/ 3600 * 1000 as speed40_80,
                 |    sum(case when vs <= 100 and vs > 80 then vs else 0 end)/ 3600 * 1000 as speed80_100,
                 |    sum(case when vs > 120 then vs else 0 end)/ 3600 * 1000 as speedover120,
                 |    variance(vs) speed_variance
                 |FROM
                 |    (
                 |        SELECT
                 |            vin,
                 |            ignitiontime,
                 |            T,
                 |            vs,
                 |            from_unixtime(unix_timestamp(first_value(t) over (PARTITION BY vin,ignitiontime ORDER BY T desc))) as end_time,
                 |            (LEAD (vs) OVER ( PARTITION BY vin,ignitiontime ORDER BY T ) - vs ) AS acc_kph,
                 |            (LEAD (sas_angle) OVER (PARTITION BY vin,ignitiontime ORDER BY T) - sas_angle) AS acc_sas
                 |        FROM vcrm10_rt_log
                 |     )A
                 |group by     A.vin,
                 |    A.ignitiontime,
                 |    A.end_time) A
                 |join
                 |    (SELECT vin,
                 |            ignitiontime,
                 |     concat_ws(',',collect_set(cast(m_speed as string))) as q_speed
                 |    FROM (
                 |        SELECT
                 |            vin,
                 |            ignitiontime,
                 |            rn,
                 |            max(vs) m_speed
                 |        from
                 |            (
                 |                SELECT
                 |                    vin,
                 |                    ignitiontime,
                 |                    vs,
                 |                    NTILE(20) OVER(partition BY vin,ignitiontime ORDER BY vs) AS rn
                 |                        FROM vcrm10_rt_log
                 |            ) t_dist
                 |        group by vin,ignitiontime,rn
                 |        order by m_speed
                 |    )A group by vin,ignitiontime ) B
                 |on A.vin=B.vin and A.ignitiontime=B.ignitiontime) a
                 |join
                 |( SELECT vin,ignitiontime,start_gps,end_gps,count(1) cnt
                 | FROM
                 | (SELECT
                 |    vin,
                 |    ignitiontime,
                 |    concat_ws(',',cast(first_value(latitude) over (PARTITION BY vin,ignitiontime ORDER BY T) as string),cast(first_value(longitude) over (PARTITION BY vin,ignitiontime ORDER BY T) as string)) as start_gps,
                 |    concat_ws(',',cast(first_value(latitude) over (PARTITION BY vin,ignitiontime ORDER BY T desc) as string),cast(first_value(longitude) over (PARTITION BY vin,ignitiontime ORDER BY T desc) as string)) as end_gps
                 |        FROM vcrm10_rt_log where latitude is not null and longitude is not null
                 |) a
                 |group by vin,ignitiontime,start_gps,end_gps) b
                 |on a.vin = b.vin and a.ignitiontime = b.ignitiontime
               """.stripMargin
    println(df_sql)
    val ubidf: DataFrame = hiveContext.sql(df_sql)



    ubidf.show(10,false)


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
