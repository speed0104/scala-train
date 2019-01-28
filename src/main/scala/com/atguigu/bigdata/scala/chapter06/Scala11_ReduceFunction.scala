package com.atguigu.bigdata.scala.chapter06

object Scala11_ReduceFunction {

    def main(args: Array[String]): Unit = {

        // 创建数据列表
        // 化简
        val list = List(1, 2, 3, 4 ,5)

        def minus( num1 : Int, num2 : Int ): Int = {
            num1 - num2
        }

        //((((1 - 2) - 3) - 4) - 5) = -13
        println(list.reduceLeft(minus))

        // (1- (2- (3- (4-5))))
        //println(list.reduceRight(minus))
    }
}
