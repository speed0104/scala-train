package com.atguigu.bigdata.scala.chapter05.section7

object Scala03_Trait3 {

    def main(args: Array[String]): Unit = {

        // 给对象动态添加功能
        // 在对象创建的时候可以动态混入(mixed in)特质

        // 创建对象
        // new
        // apply
        // 匿名子类
        // 动态混入
        val mysql = new MySQL3 with Operate3

        mysql.insert(4)
    }
}
trait Operate3 {
    def insert( id : Int ): Unit = {
        println("插入数据 = " + id)
    }
}

abstract class MySQL3 {

}
