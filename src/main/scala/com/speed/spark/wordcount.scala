package com.speed.spark

import org.apache.spark.{SparkConf, SparkContext}

object wordcount {


  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("wordcount").setMaster("local[1]")
    val sc =new SparkContext(conf)

    val lines = sc.textFile("spark.txt",1)
    val words = lines.flatMap{ line => line.split("")}
    val pairs = words.map{word => (word,1)}
    val wordcounts = pairs.reduceByKey{_+_}
    val sortedcordcount = wordcounts.sortBy(x=>x._2,false)
    sortedcordcount.foreach{ x=> println(x._1 + "appears" + x._2 + " times.")}
    sortedcordcount.cache()
    sortedcordcount.count()

  }

}
