package com.atguigu.bigdata.scala.chapter05.section7

object Scala09_Trait9 {

    def main(args: Array[String]): Unit = {

    }
}
trait Logger9 {

    this: Exception =>
    def log(): Unit = {
        println( getMessage() )
    }
}
class Console9 extends Exception with Logger9 {

}
