package com.speed.spark.operate

import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
/**
 * reduce
 * 
 * 根据聚合逻辑聚合数据集中的每个元素。
 */
object Operator_reduce {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf()
    conf.setMaster("local").setAppName("reduce")
    
    val sc = new SparkContext(conf)
    val rdd1 = sc.makeRDD(Array(1,2,3,4,5))
    
    val result = rdd1.reduce(_+_)
    
    println(result)
    sc.stop()
  }
}