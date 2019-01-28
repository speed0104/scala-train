package com.atguigu.bigdata.scala.chapter06

object Scala01_Array {

    def main(args: Array[String]): Unit = {

        // Scala中使用Array作为数组来使用
        // java : int[] ids = new int[10]
        //        ids[0] = 1

        // 创建数组1
        //val arr1 = new Array[Int](10)

        // 给元素赋值，使用小括号访问索引数据
        //arr1(0) = 1

        //println(arr1(0))

        // 创建数组2:使用apply方法，创建数组对象

        // java.lang
        // scala
        // PreDef

        // Array其实是声明在scala的包对象中，而声明Array 指向了 scala.collection.immutable.Array
        val arr2 = Array(1, 2, 3)
        println(arr2)

        // Array ==> ArrayBuffer
        println(arr2.toBuffer)
    }
}
