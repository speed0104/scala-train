package com.speed.spark.mytest

import org.apache.spark.SparkConf
import org.apache.spark.mllib.linalg.Vectors
import org.apache.spark.mllib.stat.Statistics
import org.apache.spark.sql.expressions.{MutableAggregationBuffer, UserDefinedAggregateFunction}
import org.apache.spark.sql.types._
import org.apache.spark.sql.{Row, SparkSession}

object UDFTest {


  def main(args: Array[String]): Unit = {


    val sparkConf = new SparkConf().setAppName("sparksql").setMaster("local[*]")


    val spark = SparkSession
      .builder()
      .config(sparkConf)
      .getOrCreate()

    val sc = spark.sparkContext




    //        val peoploDF = spark.read.json("D:/WorkSpace/scala/scala-train/peoplo.json")
    //        peoploDF.select("name").show()
    //        peoploDF.createOrReplaceTempView("people")
    //        //注册UDF函数
    //        spark.udf.register("add",(x:String)=>"A:" + x)
    //        //直接在SQL中使用
    //        spark.sql("select add(name) from people").show()


    val employeeDF = spark.read.json("../scala-train/employees.json")
    employeeDF.createOrReplaceTempView("employee")
    //注册UDAF函数
    spark.udf.register("average", new AverageVariance())
    spark.sql("select name,average(salary) from employee group by name").show()


    spark.stop

  }


}


class AverageSal extends UserDefinedAggregateFunction {


  // 输入数据
  override def inputSchema: StructType = StructType(StructField("salary", LongType) :: Nil)

  // 每一个分区中的 共享变量
  override def bufferSchema: StructType = StructType(StructField("sum", LongType) :: StructField("count", IntegerType) :: Nil)

  // 表示UDAF的输出类型
  override def dataType: DataType = DoubleType

  // 表示如果有相同的输入是否会存在相同的输出，如果是则true
  override def deterministic: Boolean = true

  // 初始化每一个分区中的 共享变量
  override def initialize(buffer: MutableAggregationBuffer): Unit = {
    buffer(0) = 0L
    buffer(1) = 0
  }

  // 每一个分区中的每一条数据聚合的时候需要调用该方法
  override def update(buffer: MutableAggregationBuffer, input: Row): Unit = {
    //获取这一行中的工资，然后将工资加入到sum中

    buffer(0) = buffer.getLong(0) + input.getLong(0)
    //将工资的个数加1
    buffer(1) = buffer.getInt(1) + 1
  }

  // 将每一个分区的输出 合并 形成最后的数据
  override def merge(buffer1: MutableAggregationBuffer, buffer2: Row): Unit = {
    // 合并总的工资
    buffer1(0) = buffer1.getLong(0) + buffer2.getLong(0)
    // 合并总的工资个数
    buffer1(1) = buffer1.getInt(1) + buffer2.getInt(1)
  }

  // 给出计算结果
  override def evaluate(buffer: Row): Any = {


    // 取出总的工资 / 总的工资个数
    buffer.getLong(0).toDouble / buffer.getInt(1)
  }

}


//计算方差的UDAF
//class AverageVariance extends UserDefinedAggregateFunction {
//
//  // 输入数据
//  override def inputSchema: StructType = StructType(StructField("vs", DoubleType) :: Nil)
//
//  // 每一个分区中的 共享变量
//  override def bufferSchema: StructType = StructType(StructField("vs_list", ArrayType(DoubleType)) :: Nil)
//
//  // 表示UDAF的输出类型
//  override def dataType: DataType = DoubleType
//
//  // 表示如果有相同的输入是否会存在相同的输出，如果是则true
//  override def deterministic: Boolean = true
//
//  // 初始化每一个分区中的 共享变量
//  override def initialize(buffer: MutableAggregationBuffer): Unit = {
//    buffer(0) = Nil
//
//  }
//
//  // 每一个分区中的每一条数据聚合的时候需要调用该方法
//  override def update(buffer: MutableAggregationBuffer, input: Row): Unit = {
//      println(input.getDouble(0))
//      buffer(0) = buffer.getSeq[Double](0) :+ input.getDouble(0)
//  }
//
//  // 将每一个分区的输出 合并 形成最后的数据
//  override def merge(buffer1: MutableAggregationBuffer, buffer2: Row): Unit = {
//    val buf1 = buffer1.getSeq[Double](0)
//    val buf2 = buffer2.getSeq[Double](0)
//    buffer1(0) = buf1 ++ buf2
//
//  }
//
//  // 给出计算结果
//  override def evaluate(buffer: Row): Any = {
//
//    var speed_list = scala.collection.mutable.ListBuffer[Double]();
//
//    for (item <- buffer.getSeq[Double](0)) speed_list += item
//
//    val sparkConf = new SparkConf().setAppName("sparksql").setMaster("local[*]")
//
//    val spark = SparkSession
//      .builder()
//      .config(sparkConf)
//      .getOrCreate()
//
//    val sc = spark.sparkContext
//
//    val speed_rdd = sc.parallelize(speed_list);
//    val data1 = speed_rdd.map(f => Vectors.dense(f));
//    val stat = Statistics.colStats(data1);
//    val variance = stat.variance.apply(0);
//
//    variance
//
//  }
//
//}


//计算方差的UDAF
class AverageVariance extends UserDefinedAggregateFunction {


  // 输入数据
  override def inputSchema: StructType = StructType(StructField("vs", StringType) :: Nil)

  // 每一个分区中的 共享变量
  override def bufferSchema: StructType = StructType(StructField("vs_list", StringType) :: Nil)

  // 表示UDAF的输出类型
  override def dataType: DataType = DoubleType

  // 表示如果有相同的输入是否会存在相同的输出，如果是则true
  override def deterministic: Boolean = true

  // 初始化每一个分区中的 共享变量
  override def initialize(buffer: MutableAggregationBuffer): Unit = {
    buffer(0) = ""

  }

  // 每一个分区中的每一条数据聚合的时候需要调用该方法
  override def update(buffer: MutableAggregationBuffer, input: Row): Unit = {
    if (buffer(0) == "") {
      buffer(0) = input.getString(0)
    } else {
      buffer(0) = buffer.getString(0) + "," + input.getString(0)
    }

  }

  // 将每一个分区的输出 合并 形成最后的数据
  override def merge(buffer1: MutableAggregationBuffer, buffer2: Row): Unit = {

    buffer1(0) = buffer1.getString(0) + buffer2.getString(0)

  }

  // 给出计算结果
  override def evaluate(buffer: Row): Any = {

    var speed_list = scala.collection.mutable.ListBuffer[Double]();

    var item_double: Double = 0

    for (item <- buffer.getString(0).split(",")) {

      item_double = parseDouble(item).getOrElse(0)
      speed_list += item_double

    }

    val sparkConf = new SparkConf().setAppName("sparksql").setMaster("local[*]")

    val spark = SparkSession
      .builder()
      .config(sparkConf)
      .getOrCreate()

    val sc = spark.sparkContext

    if (speed_list.length > 0) {
      val speed_rdd = sc.parallelize(speed_list);
      val data1 = speed_rdd.map(f => Vectors.dense(f));
      val stat = Statistics.colStats(data1);
      val variance = stat.variance.apply(0);

      variance
    }else{
      0.0
    }
  }

  //将String转Double
  def parseDouble(s: String): Option[Double] = try {
    Some(s.toDouble)
  } catch {
    case _ => None
  }

}