package com.sxt.scala.sql.windowfun

import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
import org.apache.spark.sql.hive.HiveContext

object RowNumberWindowFun {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf()
    conf.setAppName("windowfun")
    val sc = new SparkContext(conf)
    val hiveContext = new HiveContext(sc)
    hiveContext.sql("use spark");
		hiveContext.sql("drop table if exists sales");
		hiveContext.sql("create table if not exists sales (riqi string,leibie string,jine Int) "
				+ "row format delimited fields terminated by '\t'");
		hiveContext.sql("load data local inpath '/root/test/sales' into table sales");
		/**
		 * 开窗函数格式：
		 * 【 rou_number() over (partitin by XXX order by XXX) 】
		 */
		val result = hiveContext.sql("select riqi,leibie,jine "
							+ "from ("
								+ "select riqi,leibie,jine,"
								+ "row_number() over (partition by leibie order by jine desc) rank "
								+ "from sales) t "
						+ "where t.rank<=3");
		result.show();
    sc.stop()
  }
}