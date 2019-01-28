package com.atguigu.bigdata.scala.chapter05.section7

object Scala04_Trait4 {

    def main(args: Array[String]): Unit = {

        // 同时混入多个特质，称之为叠加
        // Scala在叠加特质的时候，会首先从后面的特质开始执行
        // Scala中特质中如果调用super，并不是表示调用父特质的方法，而是向前面（左边）继续查找特质，如果找不到，才会去父特质查找
        //val mysql = new MySQL4 with DB4 with File4
        val mysql = new MySQL4 with File4 with DB4

        // 向数据库插入数据
        // 向文件插入数据
        // 向文件向数据库插入数据
        mysql.insert(1)
    }
}
trait Operate4 {
    println("Operate4...")
    def insert(id : Int)
}
trait Data4 extends Operate4 {
    println("Data4")
    override  def insert(id : Int): Unit = {
        println("插入数据 = " + id)
    }
}
trait DB4 extends Data4 {
    println("DB4")
    override def insert(id : Int): Unit = {
        print("向数据库")
        super.insert(id)
    }
}
trait File4 extends  Data4 {
    println("File4")
    override def insert(id : Int): Unit = {
        print("向文件")
        super.insert(id)
    }
}
class MySQL4 {

}
