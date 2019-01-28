package com.atguigu.bigdata.scala.chapter05

object Scala04_Object2 extends App {

    // 继承App特质，等同于执行main方法
    println(LightColorEnum.Green)
}
object LightColorEnum extends Enumeration {
    val Red = Value(0, "Stop")
    val Yellow = Value(1, "Slow")
    val Green = Value(2, "Go")
}

