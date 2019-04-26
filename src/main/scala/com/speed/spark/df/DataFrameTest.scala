package com.speed.spark.df

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.sql.SQLContext

object DataFrameTest {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf()
    conf.setMaster("local").setAppName("jsonrdd")
    val sc = new SparkContext(conf)
    val sqlContext = new SQLContext(sc)

    val nameRDD = sc.makeRDD(Array(
          "{\"name\":\"zhangsan\",\"age\":11}",
          "{\"name\":\"zhangsan\",\"age\":22}",
          "{\"name\":\"lisi\",\"age\":33}",
          "{\"name\":\"lisi\",\"age\":55}",
          "{\"name\":\"wangwu\",\"age\":34}",
          "{\"name\":\"wangwu\",\"age\":65}"
        ))



    val nameDF = sqlContext.read.json(nameRDD)

    nameDF.show()

    sc.stop()
  }
}