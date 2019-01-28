package com.atguigu.bigdata.scala.chapter04

object Scala07_Exception {

    def main(args: Array[String]): Unit = {


        // 异常处理
        /*
        try {

            val num = 10 / 0
        } catch {
            case ex: Exception => println("捕获了异常")
            case ex: ArithmeticException => println("捕获了除数为零的算数异常")
        } finally {
            println("finally ....")
        }
        */

        // classOf[NumberFormatException] ==> NumberFormatException.class

        /*
        @throws(classOf[NumberFormatException])
        def f11()  = {
            "abc".toInt
        }

        f11()
        */



    }
}
