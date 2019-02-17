package com.speed.spark.operate

import org.apache.spark.SparkConf
import org.apache.spark.SparkContext

object Operator_groupByKey {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf()
    conf.setMaster("local").setAppName("groupByKey")
    val sc = new SparkContext(conf)
    val rdd1 = sc.makeRDD(Array(
        (1,"a"),
        (1,"b"),
        (2,"c"),
        (3,"d")
       ))
    
        
    val result = rdd1.groupByKey()
    result.foreach(println)
    sc.stop()
  }
}