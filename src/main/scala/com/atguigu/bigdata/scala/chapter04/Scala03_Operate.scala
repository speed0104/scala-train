package com.atguigu.bigdata.scala.chapter04

object Scala03_Operate {
    def main(args: Array[String]): Unit = {

        //println(1 + 1)

        //var num : Int = 1

        // Scala中的运算符是可以通过方法调用的。
        //println(num.+(num))

        //var person = new Person();

        //println(person.sum(10))
        //println( person sum 20)

    }
}
class Person {
    def sum( num : Int ): Int = {
        return num + 10
    }
}
