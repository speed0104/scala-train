package com.speed.spark.operate

import org.apache.spark.SparkConf
import org.apache.spark.SparkContext

object Operator_sample {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf()
    conf.setMaster("local").setAppName("sample")
    val sc = new SparkContext(conf)
    val lines = sc.textFile("data/word.txt")
    lines.sample(true, 0.5,10).foreach { println}
  }
}