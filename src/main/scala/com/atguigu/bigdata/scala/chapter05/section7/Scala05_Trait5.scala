package com.atguigu.bigdata.scala.chapter05.section7

object Scala05_Trait5 {

    def main(args: Array[String]): Unit = {

        //var mysql = new MySQL5 with DB5 with Data5
        var mysql = new MySQL5 with DB5 with Data5

        // 将数据将数据保存到数据库中
        mysql.insert(5)
    }
}

trait Operate5 {

    def insert(id : Int)
}

trait Data5 extends Operate5 {

    // 重写抽象方法的目的是为了明确super关键字的指向问题
    // 重写抽象方法时需要考虑混入特质的顺序
    abstract override def insert( id : Int ): Unit = {
        print("将数据")
        super.insert(id)
    }
}
trait DB5 extends  Operate5 {
    def insert( id : Int ): Unit = {
        print("将数据保存到数据库中")
    }
}

class MySQL5 {

}
