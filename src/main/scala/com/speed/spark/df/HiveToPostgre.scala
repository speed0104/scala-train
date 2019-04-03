package com.speed.spark.df

import java.util.Properties

import org.apache.spark.sql.{SQLContext, SaveMode}
import org.apache.spark.sql.hive.HiveContext
import org.apache.spark.{SparkConf, SparkContext}

object HiveToPostgre {

  def main(args: Array[String]): Unit = {
    val conf = new SparkConf()
//    要在集群上提交需要去掉Local
//    conf.setMaster("local").setAppName("HiveSource")
    val sc = new SparkContext(conf)
    val sqlContext = new SQLContext(sc)

    /**
      * HiveContext是SQLContext的子类。
      */
    val hiveContext = new HiveContext(sc)
//    hiveContext.sql("use test")

    //查询传入的SQL
    val inputsql = args(0)

    val df = hiveContext.sql(inputsql)

    df.show()

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
    df.write.mode(SaveMode.Append).jdbc("jdbc:postgresql://10.109.46.33/vcrm20", inputtable, properties)

    sc.stop()

  }

}
