package com.atguigu.bigdata.scala.chapter05.section7

object Scala08_Trait8 {

    def main(args: Array[String]): Unit = {

        var e = new MyException
        println(e)
    }
}
trait LoggedException extends Exception{
    def log(): Unit ={
        println(getMessage()) // 方法来自于Exception类
    }
}
class UnhappyException extends LoggedException{
    override def getMessage = "错误消息！"// 已经是Exception的子类了，所以可以重写方法
}
class AAA extends Exception {

}
class MyException extends AAA with LoggedException {

}

