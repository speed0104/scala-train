/*
package com.speed.spark.mytest

import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession

object DataFrameTest {


  def main(args: Array[String]): Unit = {

    val sparkConf = new SparkConf().setAppName("sparksql").setMaster("local[*]")

    val spark = SparkSession
      .builder()
      .config(sparkConf)
      .getOrCreate()

    val sc = spark.sparkContext

    val employeeDF = spark.read.json("../scala-train/employees.json")

    employeeDF.printSchema()

//    employeeDF.show()
//    employeeDF.select("name").show()
//    employeeDF.select(employeeDF.col("name"),(employeeDF.col("salary") + 100).as("more")).show()
//    employeeDF.filter(employeeDF.col("salary") > 5).show()
    employeeDF.groupBy("salary").count().show()








    spark.stop()
  }


}
*/
