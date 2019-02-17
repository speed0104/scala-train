package com.speed.spark.operate

import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
import org.apache.spark.rdd.RDD
import scala.collection.mutable.ListBuffer

object Operator_repartition {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf()
    conf.setMaster("local").setAppName("repartition")
    val sc = new SparkContext(conf)
    
    val rdd1 = sc.makeRDD(List(1,2,3,4,5,6,7),3)
    val rdd2 = rdd1.mapPartitionsWithIndex((partitionIndex,iter)=>{
      val list = new ListBuffer[String]()
      while(iter.hasNext){
        list += "rdd1partitionIndex : "+partitionIndex+",value :"+iter.next()
      }  
      list.iterator
    })
    
    rdd2.foreach{ println }
    
    val rdd3 = rdd2.repartition(4)
    val result = rdd3.mapPartitionsWithIndex((partitionIndex,iter)=>{
      val list = ListBuffer[String]()
      while(iter.hasNext){
        list +=("repartitionIndex : "+partitionIndex+",value :"+iter.next())
      }
      list.iterator
    })
    result.foreach{ println}
    
    
    sc.stop()
  }
}