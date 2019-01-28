package com.atguigu.bigdata.scala.chapter05.section7

object Scala01_Trait {

    def main(args: Array[String]): Unit = {

        val console = new Console
        console.log()
    }
}
trait Logger {
    def log()
}
class Console extends Logger with Cloneable with Serializable {
    def log(): Unit = {
        println("console log....")
    }
}
