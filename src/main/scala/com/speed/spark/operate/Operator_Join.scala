package com.speed.spark.operate

import org.apache.spark.{SparkConf, SparkContext}

object Operator_Join {

    def main(args: Array[String]): Unit = {

        val conf = new SparkConf()
        conf.setMaster("local").setAppName("flatMap")
        val sc = new SparkContext(conf)

        val rdd1 = sc.parallelize(
            Array(("a",1),("b",2),("c",3))
        )

        val rdd2 = sc.parallelize(
            Array(("a",1),("d",2),("e",3))
        )

        val result = rdd1.join(rdd2)

        result.foreach(println)

        rdd1.leftOuterJoin(rdd2).foreach(println)

        rdd1.rightOuterJoin(rdd2).foreach(println)

        rdd1.fullOuterJoin(rdd2).foreach(println)



    }

}
