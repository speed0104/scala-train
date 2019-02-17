package com.speed.spark.operate

import org.apache.spark.SparkConf
import org.apache.spark.SparkContext

object Operator_flatMap {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf()
    conf.setMaster("local").setAppName("flatMap")
    val sc = new SparkContext(conf)
    val lines = sc.textFile("data/word.txt")
    val result = lines.flatMap { _.split(" ")}
    result.foreach { println}
    sc.stop()
  }
}