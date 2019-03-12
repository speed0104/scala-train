package com.speed.spark.df

import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
import org.apache.spark.sql.SQLContext

object CreateDFFromJsonFile {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf()
    conf.setMaster("local").setAppName("jsonfile")
    
    val sc = new SparkContext(conf)
    val sqlContext = new SQLContext(sc)
    val df = sqlContext.read.json("../scala-train/employees.json")
//    val df1 = sqlContext.read.format("json").load("sparksql/json")
    
//    val rdd = df.rdd
    
    df.show()
    df.printSchema()
    //select * from table
    df.select(df.col("name")).show()
    //select name from table where age>19
    df.select(df.col("name"),df.col("age")).where(df.col("age").gt(19)).show()
    //select count(*) from table group by age
    df.groupBy(df.col("age")).count().show();
     
    /**
     * 注册临时表
     */
    df.registerTempTable("jtable")
    val result  = sqlContext.sql("select  * from jtable")
    result.show()
    sc.stop()
  }
}