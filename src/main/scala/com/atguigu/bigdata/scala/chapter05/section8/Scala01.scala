package com.atguigu.bigdata.scala.chapter05.section8

object Scala01 {

    def main(args: Array[String]): Unit = {

        // 隐式转换（类型转换）函数
        implicit def a(d: Double):Int = {
            d.toInt
        }

        // 在当前环境中，不能存在满足条件的多个隐式函数
        /*implicit def b(d: Double):Int = {
            d.toInt
        }
        */
        // double ==> int

        val num : Int = 3.5

        println(num)

        // A（1）  B(2)
    }
}
