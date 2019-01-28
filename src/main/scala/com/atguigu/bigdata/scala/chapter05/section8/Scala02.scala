package com.atguigu.bigdata.scala.chapter05.section8

object Scala02 {

    def main(args: Array[String]): Unit = {

        implicit def addDelete( mysql : MySQL ): DB = {
            new DB
        }


        // MySQL ==> DB
        val mysql = new MySQL
        mysql.delete()

    }
}
class MySQL {
    def insert( ): Unit = {
        println("插入数据")
    }
}
class DB {
    def delete(): Unit = {
        println("删除数据")
    }
}