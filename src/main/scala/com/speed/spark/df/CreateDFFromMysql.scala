package com.speed.spark.df

import java.util.HashMap
import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
import org.apache.spark.sql.SQLContext
import org.apache.spark.sql.SaveMode
import java.util.Properties

object CreateDFFromMysql {
  def main(args: Array[String]): Unit = {
		val conf = new SparkConf()
    conf.setMaster("local").setAppName("mysql")
    val sc = new SparkContext(conf)
		val sqlContext = new SQLContext(sc)
		/**
		 * 第一种方式读取Mysql数据库表创建DF
		 */
		val options = new HashMap[String,String]();
		options.put("url", "jdbc:mysql://192.168.100.111:3306/spark")
		options.put("driver","com.mysql.jdbc.Driver")
		options.put("user","root")
		options.put("password", "1234")
		options.put("dbtable","person")
		val person = sqlContext.read.format("jdbc").options(options).load()
		person.show()
		person.registerTempTable("person")
		/**
		 * 第二种方式读取Mysql数据库表创建DF
		 */
		val reader = sqlContext.read.format("jdbc")
		reader.option("url", "jdbc:mysql://192.168.100.111:3306/spark")
		reader.option("driver","com.mysql.jdbc.Driver")
		reader.option("user","root")
		reader.option("password","1234")
		reader.option("dbtable", "score")
		val score = reader.load()
		score.show()
		score.registerTempTable("score")
		val result = sqlContext.sql("select person.id,person.name,score.score from person,score where person.name = score.name")
		result.show()
		/**
		 * 将数据写入到Mysql表中
		 */
		val properties = new Properties()
		properties.setProperty("user", "root")
		properties.setProperty("password", "1234")
		result.write.mode(SaveMode.Append).jdbc("jdbc:mysql://192.168.100.111:3306/spark", "result", properties)
		
		sc.stop()
  }
}