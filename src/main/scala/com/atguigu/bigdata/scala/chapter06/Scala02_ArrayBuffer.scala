package com.atguigu.bigdata.scala.chapter06

import scala.collection.mutable.ArrayBuffer

object Scala02_ArrayBuffer {

    def main(args: Array[String]): Unit = {
        // 可变集合
        // 可变集合在mutable包中
        val arr2 = ArrayBuffer[Int]()

        // 追加值
        arr2.append(1, 2, 3)

        // 修改值
        arr2(0) = 7
        //println(arr2)

        // ArrayBuffer ==> Array
        println(arr2.toArray)
    }
}
