package com.speed.spark.df

import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
import org.apache.spark.sql.RowFactory
import org.apache.spark.sql.SQLContext
import org.apache.spark.sql.types.StructType
import org.apache.spark.sql.types.StringType
import org.apache.spark.sql.types.StructField
import org.apache.spark.sql.types.IntegerType


object CreateDFFromRDDWithStruct {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf()
    conf.setMaster("local").setAppName("rddStruct")
    val sc = new SparkContext(conf)
    val sqlContext = new SQLContext(sc)
    val lineRDD = sc.textFile("data/person.txt")
    val rowRDD = lineRDD.map { x => {
      val split = x.split(",")
      RowFactory.create(split(0),split(1),Integer.valueOf(split(2)))
    } }
    
    val schema = StructType(List(
          StructField("id",StringType,true),
          StructField("name",StringType,true),
          StructField("age",IntegerType,true)
        ))
    
    val df = sqlContext.createDataFrame(rowRDD, schema)
    df.show()
    df.printSchema()
    sc.stop()
  }
}