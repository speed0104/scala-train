package com.atguigu.bigdata.scala.chapter05.section8


object Scala03 {

    def main(args: Array[String]): Unit = {

        // 隐式变量（值）
        implicit val name : String = "Scala"
        //implicit val name1 : String = "World"

        // 声明方法，但是参数无默认值
        // 如果函数调用时，没有传递参数，那么可以声明implicit 关键字，去查找隐式值
        def hello( implicit content : String ): Unit = {
            println("Hello " + content)
        }

        hello
    }
}
