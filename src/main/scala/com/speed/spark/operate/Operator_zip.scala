package com.speed.spark.operate

import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
/**
 * 将两个RDD中的元素（KV格式/非KV格式）变成一个KV格式的RDD,两个RDD的个数必须相同。
 */
object Operator_zip {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf()
    conf.setAppName("zip").setMaster("local")
    val sc = new SparkContext(conf)
    val nameRDD  = sc.makeRDD(Array("zhangsan","lisi","wangwu"))
    val scoreRDD = sc.parallelize(Array(1,2,3))
    val result = nameRDD.zip(scoreRDD)
    result.foreach(println)
    sc.stop()
    
  }
}