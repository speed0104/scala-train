package com.atguigu.bigdata.scala.chapter02

object Scala02_Function {
    def main(args: Array[String]): Unit = {

        var v2 = f2 _ // 在函数的后面增加下划线，表示将函数当成类型赋值，而不会执行
        v2()()
    }

    def f1(): Unit = {
        println("f1...")
    }

    def f2()  = {
        f1 _
    }
}
