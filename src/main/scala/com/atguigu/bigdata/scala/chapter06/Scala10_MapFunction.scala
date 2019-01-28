package com.atguigu.bigdata.scala.chapter06

object Scala10_MapFunction {

    def main(args: Array[String]): Unit = {

        // 创建集合
        //val list = List(1 , 2 , 3)

        // 声明函数 （数据 * 2）
        def multiple( i : Int ): Int = {
            i * 2
        }

        // map方法可以接收一个函数作为参数
        // map方法会将集合的每一个元素作为参数传递给函数，然后将结果放置在新的集合里
        //println(list.map(multiple))
        //println(list.map(_*2))

        /*
        val names = List("Alice", "Bob", "Nick")
        def upper( s : String ) : String = {
            s. toUpperCase
        }
        println(names.flatMap(upper))
        */

        val names = List("Alice", "Bob", "Nick")

        def startA( s : String ): Boolean = {
            s.startsWith("A")
        }

        println(names.filter(startA))

    }
}
