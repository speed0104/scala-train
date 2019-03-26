package com.speed.spark.df

import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
import org.apache.spark.sql.hive.HiveContext
import org.apache.hadoop.hive.conf.HiveConf
import org.apache.spark.sql.{SQLContext, SaveMode}

object CreateDFFromHive {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf()
    conf.setMaster("local").setAppName("HiveSource")
    val sc = new SparkContext(conf)
    /**
     * HiveContext是SQLContext的子类。
     */
    val hiveContext = new HiveContext(sc)
   hiveContext.sql("use test")
/*     hiveContext.sql("drop table if exists student_infos")
    hiveContext.sql("create table if not exists student_infos (name string,age int) row format  delimited fields terminated by '\t'")
//    val df1 = hiveContext.sql("select * from drv_info limit 10")
//    df1.show()


    hiveContext.sql("load data local inpath 'data/student_infos' into table student_infos")

    hiveContext.sql("drop table if exists student_scores")
    hiveContext.sql("create table if not exists student_scores (name string,score int) row format delimited fields terminated by '\t'")
    hiveContext.sql("load data local inpath 'data/student_scores' into table student_scores")

    val df = hiveContext.sql("select si.name,si.age,ss.score from student_infos si,student_scores ss where si.name = ss.name")
    hiveContext.sql("drop table if exists good_student_infos")
    /**
     * 将结果写入到hive表中
     */
    df.write.mode(SaveMode.Overwrite).saveAsTable("good_student_infos")*/

    val df = hiveContext.read.json("data/employees.json")
    df.write.mode(SaveMode.Overwrite).saveAsTable("employees")
    
    sc.stop()
  }
}