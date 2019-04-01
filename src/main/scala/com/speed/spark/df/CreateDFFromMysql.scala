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
		 * 第一种方式读取Postgresql数据库表创建DF
		 */
/*		val options = new HashMap[String,String]();
		options.put("url", "jdbc:postgresql://10.109.46.33/testing")
		options.put("driver","org.postgresql.Driver")
		options.put("user","bluelink")
		options.put("password", "bluelink")
		options.put("dbtable","users")
		val person = sqlContext.read.format("jdbc").options(options).load()
		person.show()
		person.registerTempTable("person")*/
		/**
		 * 第二种方式读取Postgresql数据库表创建DF
		 */
		val reader = sqlContext.read.format("jdbc")
		reader.option("url", "jdbc:postgresql://10.109.46.33/testing")
		reader.option("driver","org.postgresql.Driver")
		reader.option("user","bluelink")
		reader.option("password","bluelink")
		reader.option("dbtable", "users")
		val users = reader.load()
		users.show()
		users.registerTempTable("test_users")
		val result = sqlContext.sql("select * from test_users limit 10")
		result.show()
		/**
		 * 将数据写入到Mysql表中
		 */
		val properties = new Properties()
		properties.setProperty("user", "bluelink")
		properties.setProperty("password", "bluelink")
		result.write.mode(SaveMode.Append).jdbc("jdbc:postgresql://10.109.46.33/testing", "test_users", properties)

		sc.stop()
  }
}