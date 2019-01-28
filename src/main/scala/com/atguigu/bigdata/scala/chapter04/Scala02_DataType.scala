package com.atguigu.bigdata.scala.chapter04

object Scala02_DataType {
    def main(args: Array[String]): Unit = {

        // Scala语言是完全面向对象语言，所以所有的值全部都是对象
        println(1.toString)

        test()

    }

    def test() : Nothing = {
        throw new Exception()
    }
}
