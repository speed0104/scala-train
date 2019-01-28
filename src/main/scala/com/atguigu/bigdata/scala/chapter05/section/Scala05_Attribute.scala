package com.atguigu.bigdata.scala.chapter05

//import scala.beans.BeanProperty


object Scala05_Attribute {

    def main(args: Array[String]): Unit = {

        // 构建对象
        val manager = new Manager("zhangsan");

        manager.name = "lisi"

        println(manager.name)

        manager.setAge(20)

        println(manager.getAge)
    }
}

class Manager( var name : String ) {
    @scala.beans.BeanProperty var age: Int = _
}
