package com.speed.spark.operate

import org.apache.spark.SparkConf
import org.apache.spark.SparkContext

object Operator_collect {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf()
    conf.setMaster("local").setAppName("collect")
    val sc = new SparkContext(conf)
    val lines = sc.textFile("data/word.txt")
    lines.collect().foreach { println }
    sc.stop()
  }
}