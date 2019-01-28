package com.speed.spark

import org.apache.spark.sql.SparkSession
import org.apache.spark.{SparkConf, SparkContext}

object DataFrameTest {


  def main(args: Array[String]): Unit = {

    val sparkConf = new SparkConf().setAppName("sparksql").setMaster("local[*]")

    val spark = SparkSession
      .builder()
      .config(sparkConf)
      .getOrCreate()

    val sc = spark.sparkContext

    val employeeDF = spark.read.json("../scala-train/employees.json")

    employeeDF.show()






  }


}
