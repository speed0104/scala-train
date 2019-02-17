package com.speed.spark.operate

import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
import scala.collection.mutable.ListBuffer

object Operator_mapPartitionsWithIndex {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf()
    conf.setMaster("local").setAppName("mapPartitionsWithIndex")
     val sc = new SparkContext(conf)
      val rdd = sc.makeRDD(List("a","b","c"),3)
      rdd.mapPartitionsWithIndex((index,iter)=>{
      val list = ListBuffer[String]()
        while(iter.hasNext){
          val v = iter.next()
          list.+(v)
      	  println("index = "+index+" , value = "+v)
        }
        list.iterator
      }, false).foreach(println)
    sc.stop();
  
  }
}