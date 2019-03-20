/*
package com.speed.spark.mytest

import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession

object RDDToDF {

  def main(args: Array[String]): Unit = {

    val sparkConf = new SparkConf().setAppName("sparksql").setMaster("local[*]")

    val spark = SparkSession
      .builder()
      .config(sparkConf)
      .getOrCreate()

    val sc = spark.sparkContext

    val infoRdd = spark.sparkContext.textFile("../scala-train/infos.txt")

    //RDD转DF时需导入隐私转换
    import spark.implicits._
    val infoDF = infoRdd.map(_.split(",")).map(line=>info(line(0).toInt,line(1),line(2).toInt)).toDF()

//    infoDF.show()

    infoDF.createOrReplaceTempView("infos")
    spark.sql("select * from infos where age > 221").show()


    spark.stop()


  }

  //构建一个样例类，用于RDD转DF
  case class info(id:Int,name:String,age:Int){

  }


}
*/
