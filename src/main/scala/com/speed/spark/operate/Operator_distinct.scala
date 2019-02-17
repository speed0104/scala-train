package com.speed.spark.operate

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object Operator_distinct {

    def main(args: Array[String]): Unit = {
        val conf = new SparkConf()
        conf.setMaster("local").setAppName("filter")
        val sc =  new SparkContext(conf)
        val lines = sc.parallelize(Array(1,2,2,3,4,4))
        println(lines.distinct())

    }

}
