package com.speed.spark.operate

import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
/**
 * countByValue
 * 根据数据集每个元素相同的内容来计数。返回相同内容的元素对应的条数。
 */
object Operator_countByValue {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf()
    conf.setMaster("local").setAppName("countByValue")
    val sc = new SparkContext(conf)
    
    val rdd1 = sc.makeRDD(List(
      ("a",100),    
      ("a",100),
      ("b",300),
      ("b",300),    
      ("c",400) 
    ))
    val rdd2 = rdd1.countByValue()
    rdd2.foreach(println)
    sc.stop()
  }
}