/*
package com.speed.spark.mytest

import org.apache.spark.SparkConf
import org.apache.spark.sql.types.{IntegerType, StringType, StructField, StructType}
import org.apache.spark.sql.{Row, SparkSession}

object RDDToDF2 {

  def main(args: Array[String]): Unit = {

    val sparkConf = new SparkConf().setAppName("sparksql").setMaster("local[*]")

    val spark = SparkSession
      .builder()
      .config(sparkConf)
      .getOrCreate()

    val sc = spark.sparkContext

    val infoRdd = spark.sparkContext.textFile("../scala-train/infos.txt")

    val infoRow = infoRdd.map(_.split(",")).map(line=>Row(line(0).toInt,line(1),line(2).toInt))

    val structType = StructType(Seq(StructField("id",IntegerType,true),StructField("name",StringType,true),StructField("age",IntegerType,true)))

    val infoDF =  spark.createDataFrame(infoRow,structType)

    infoDF.printSchema()
    infoDF.show()

    spark.stop()


  }



}
*/
