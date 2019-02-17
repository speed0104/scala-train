package com.speed.spark.operate

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object Operator_homework {

    def main(args: Array[String]): Unit = {
        val conf = new SparkConf()
        conf.setMaster("local").setAppName("homework")
        val sc = new SparkContext(conf)
        val rdd1 = sc.parallelize(List(
            1, 2, 3
        ))

        val rdd2 = sc.parallelize(List(
            2, 3, 5
        ))

        //        val rdd3: RDD[Int] = rdd1.intersection(rdd2)
        //        val rdd3: RDD[Int] = rdd1.union(rdd2)

        val rdd3: RDD[Int] = rdd1.subtract(rdd2)
        rdd3.foreach(println)

        //
        //        val rdd1 = sc.parallelize(List(
        //            ("a",100),
        //            ("b",200),
        //            ("c",300),
        //            ("c",400)
        //        ))
        //
        //        val rdd2 = sc.parallelize(List(
        //            ("a",110),
        //            ("b",220),
        //            ("b",330),
        //            ("c",400)
        //        ))

        // k,v 一致 算重复，去除掉
        //        val value: RDD[(String, Int)] = rdd1.distinct()
        //
        //        value.foreach(println)


        //        val rdd3: RDD[(String, (Iterable[Int], Iterable[Int]))] = rdd1.cogroup(rdd2)
        //
        //        rdd3.foreach(x=>{
        //            val key = x._1
        //            val tupel2 = x._2
        //
        //            val iterator1 = tupel2._1.iterator
        //            val iterator2 = tupel2._2.iterator
        //            println(" key :  " + key)
        //            while(iterator1.hasNext){
        //                println(iterator1.next())
        //            }
        //            println("===========")
        //            while(iterator2.hasNext){
        //                println(iterator2.next())
        //            }
        //
        //        })
    }

}
