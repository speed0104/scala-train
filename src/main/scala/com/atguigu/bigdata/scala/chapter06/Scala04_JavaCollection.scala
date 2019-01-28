package com.atguigu.bigdata.scala.chapter06

import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer

object Scala04_JavaCollection {

    def main(args: Array[String]): Unit = {

        // Scala集合和Java集合互相转换
        val arr = ArrayBuffer("1", "2", "3")

        import scala.collection.JavaConversions.bufferAsJavaList

        // ArrayBuffer ==> java.util.List
        val javaArr = new ProcessBuilder(arr)

        println(javaArr.command())

        import scala.collection.JavaConversions.asScalaBuffer
        // java.util.List ==> Buffer
        val scalaArr: mutable.Buffer[String] = javaArr.command()

        println(scalaArr)

        // 数组的循环
        for ( item <- scalaArr ) {
            println(item)
        }
    }
}
