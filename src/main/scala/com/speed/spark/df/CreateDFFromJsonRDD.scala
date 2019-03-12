package com.speed.spark.df

import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
import org.apache.spark.sql.SQLContext

object CreateDFFromJsonRDD {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf()
    conf.setMaster("local").setAppName("jsonrdd")
    val sc = new SparkContext(conf)
    val sqlContext = new SQLContext(sc)
    
    val nameRDD = sc.makeRDD(Array(
          "{\"name\":\"zhangsan\",\"age\":18}",
          "{\"name\":\"lisi\",\"age\":19}",
          "{\"name\":\"wangwu\",\"age\":20}"
        ))
    val scoreRDD = sc.makeRDD(Array(
    		"{\"name\":\"zhangsan\",\"score\":100}",
    		"{\"name\":\"lisi\",\"score\":200}",
    		"{\"name\":\"wangwu\",\"score\":300}"
    		))
    val nameDF = sqlContext.read.json(nameRDD)
    val scoreDF = sqlContext.read.json(scoreRDD)
    nameDF.registerTempTable("name") 		
    scoreDF.registerTempTable("score") 		
    val result = sqlContext.sql("select name.name,name.age,score.score from name,score where name.name = score.name")
    result.show()
    sc.stop()
  }
}