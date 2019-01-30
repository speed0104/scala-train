package com.speed.spark

import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession

object StudentRDDToDF {

  def main(args: Array[String]): Unit = {

    val sparkConf = new SparkConf().setAppName("sparksql").setMaster("local[*]")

    val spark = SparkSession
      .builder()
      .config(sparkConf)
      .getOrCreate()

    val sc = spark.sparkContext

    val StuentRdd = spark.sparkContext.textFile("../scala-train/students.txt")

    //RDD转DF时需导入隐私转换
    import spark.implicits._
    val StuentDF = StuentRdd.map(_.split("\\|")).map(line=>student(line(0).toInt,line(1),line(2).toInt,line(3))).toDF()

//    StuentDF.filter("name != '' and name != 'NULL'").show(10,false)

//    StuentDF.filter("SUBSTR(name,0,1)='z'")show(10,false)

    //排序
//    StuentDF.select(StuentDF("phone").as("telephone")).show(10,false)
    StuentDF.orderBy(StuentDF("phone").desc,StuentDF("name").asc).show(10,false)


    //spark.sql("show functions").show(1000) 查看可以使用的函数
//    spark.sql("show functions").show(1000)




//    StuentDF.take(10).foreach(println)

//    StuentDF.head(2)



















    spark.stop()

  }

  //构建一个样例类，用于RDD转DF
  case class student(id:Int,name:String,phone:Int,email:String){

  }


}



