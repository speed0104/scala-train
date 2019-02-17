package com.speed.spark.operate

import org.apache.spark.SparkConf
import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
import org.apache.spark.rdd.RDD

/**
  * countByKey
  *
  * 作用到K,V格式的RDD上，根据Key计数相同Key的数据集元素。返回一个Map<K,Object>
  */
object Operator_countByKey {
    def main(args: Array[String]): Unit = {
        val conf = new SparkConf()
        conf.setMaster("local").setAppName("countByKey")
        val sc = new SparkContext(conf)

        val rdd: RDD[(String, Int)] = sc.makeRDD(List(
            ("a", 100),
            ("b", 200),
            ("a", 300),
            ("c", 400)))

        val rdd1 = sc.parallelize(List(
            ("a", 100),
            ("b", 200),
            ("a", 300),
            ("c", 400)
        ))


        val result = rdd1.countByKey()

        /* val rdd1 = sc.parallelize(Array((1,"a"),(2,"b"),(3,"c"),(4,"d"),(4,"d")))*/
        //    val result = rdd1.countByKey()
        result.foreach(println)
        sc.stop()
    }
}