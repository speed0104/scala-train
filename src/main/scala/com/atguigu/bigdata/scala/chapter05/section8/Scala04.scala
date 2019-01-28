package com.atguigu.bigdata.scala.chapter05.section8

object Scala04 {

    implicit class DB1(val s : MySQL1){ //隐式类
        def addSuffix = s + " Scala"
    }


    def main(args: Array[String]): Unit = {
        // 隐式类

        val mysql = new MySQL1
        mysql.addSuffix
    }
}
class MySQL1 {

}