package com.atguigu.bigdata.scala.chapter05.section7

object Scala02_Trait2 {

    def main(args: Array[String]): Unit = {

        val mysql = new MySQL

        mysql.insert(2)
    }
}

trait Operate {
    def insert( id : Int ): Unit = {
        println("保存数据="+id)
    }
}
trait DB extends Operate {
    override  def insert( id : Int ): Unit = {
        print("向数据库中")
        super.insert(id)
    }
}
class MySQL extends DB {

}
