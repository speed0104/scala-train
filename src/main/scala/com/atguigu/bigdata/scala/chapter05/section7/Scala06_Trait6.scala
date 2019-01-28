package com.atguigu.bigdata.scala.chapter05.section7

object Scala06_Trait6 {

    def main(args: Array[String]): Unit = {

        var mysql = new MySQL6 with DB6

        println(mysql.opertype)
    }
}
trait Operate6 {

    var opertype : String

    def insert()
}
trait DB6 extends  Operate6 {
    var opertype : String = "insert"

    def insert(): Unit = {

    }
}
class MySQL6 {

}
