package com.speed.spark

import com.speed.spark.RDDTest.getClass
import org.apache.spark.mllib.linalg.Vectors
import org.apache.spark.mllib.stat.Statistics
import org.apache.spark.sql.expressions.{MutableAggregationBuffer, UserDefinedAggregateFunction}
import org.apache.spark.sql.types._
import org.apache.spark.sql.{Row, SQLContext, SparkSession}
import org.apache.spark.{SparkConf, SparkContext}

import scala.collection.mutable.ListBuffer

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

        val employeeDF = spark.read.json("D:/WorkSpace/scala/scala-train/employees.json")
        employeeDF.createOrReplaceTempView("employee")
        //注册UDAF函数
        spark.udf.register("average",new AverageSal2)
        spark.sql("select average(salary) from employee group by name").show()


        spark.stop

  }


}


class AverageSal extends UserDefinedAggregateFunction{


  // 输入数据
  override def inputSchema: StructType = StructType( StructField("salary",LongType) :: Nil )

  // 每一个分区中的 共享变量
  override def bufferSchema: StructType = StructType( StructField("sum",LongType) :: StructField("count",IntegerType) :: Nil )

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

class AverageSal2 extends UserDefinedAggregateFunction{


  // 输入数据
  override def inputSchema: StructType = StructType( StructField("salary",StringType) :: Nil )

  // 每一个分区中的 共享变量
  override def bufferSchema: StructType = StructType( StructField("sum",StringType)  :: Nil )

  // 表示UDAF的输出类型
  override def dataType: DataType = StringType

  // 表示如果有相同的输入是否会存在相同的输出，如果是则true
  override def deterministic: Boolean = true

  // 初始化每一个分区中的 共享变量
  override def initialize(buffer: MutableAggregationBuffer): Unit = {
    buffer(0) = ""

  }

  // 每一个分区中的每一条数据聚合的时候需要调用该方法
  override def update(buffer: MutableAggregationBuffer, input: Row): Unit = {
    if(buffer(0) == ""){
      buffer(0) = input.getString(0)
    }else{
      buffer(0) = buffer.getString(0) + "," + input.getString(0)
    }


  }

  // 将每一个分区的输出 合并 形成最后的数据
  override def merge(buffer1: MutableAggregationBuffer, buffer2: Row): Unit = {
    // 合并总的工资
    buffer1(0) = buffer1.getString(0) + buffer2.getString(0)

  }

  // 给出计算结果
  override def evaluate(buffer: Row): Any = {

    var speed_list = scala.collection.mutable.ListBuffer[Any]();

    for(item <- buffer.getString(0).split(",")){
      speed_list += item
    }

    val sparkConf = new SparkConf().setAppName("sparksql").setMaster("local[*]")

    val spark = SparkSession
      .builder()
      .config(sparkConf)
      .getOrCreate()

    val sc = spark.sparkContext

    val speed_rdd = sc.parallelize(speed_list);
    val data1 = speed_rdd.map(f => Vectors.dense(f.asInstanceOf[Double]));
    val stat = Statistics.colStats(data1);
    val variance = stat.variance.apply(0);

    println(variance)

    buffer.getString(0)
  }

}