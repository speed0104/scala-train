package com.speed.spark.mytest

import org.apache.spark.{SparkConf, SparkContext}

object RDDTest {

  def main(args: Array[String]): Unit = {

    //创建spark连接上下文，方式一：
//    val sparkConf = new SparkConf().setAppName("sparksql").setMaster("local[*]")
//
//    val spark = SparkSession
//      .builder()
//      .config(sparkConf)
//      .getOrCreate()
//
//    val sc = spark.sparkContext

    //创建spark连接上下文，方式二：
    def getSparkContext : SparkContext = {
      new SparkContext(
        new SparkConf()
          .setAppName(getClass.getName)
          .setMaster("local[2]")
          .set("spark.ui.enabled", "false")
      )
    }
    val sc = getSparkContext
//    val sqlContext = new SQLContext(sc)

    val mytest  = sc.textFile("D:/WorkSpace/scala/scala-train/rddtext.txt")

    //打印RDD
    mytest.collect().foreach {println}

    var speed_list = scala.collection.mutable.ListBuffer[Any]();

//    for (i <- mytest){
//      println(i.getClass)
//    }


//    speed_list ++= mytest.collect().toList

    println(speed_list)

  }


}
