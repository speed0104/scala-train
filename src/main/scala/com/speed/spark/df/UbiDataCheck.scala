package com.speed.spark.df

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.sql.SQLContext
import org.apache.spark.sql.hive.HiveContext

object UbiDataCheck {

  def main(args: Array[String]): Unit = {

    val conf = new SparkConf()

    //    要在集群上提交需要去掉Local
    conf.setMaster("local").setAppName("UbiDataCheck")

    val sc = new SparkContext(conf)
    val sqlContext = new SQLContext(sc)
    val hiveContext = new HiveContext(sc)
    hiveContext.sql("use hkgc_tms_mstr")

    val df = hiveContext.sql("select * from vcrm20_rt_log limit 10")

    df.show()






  }

}
