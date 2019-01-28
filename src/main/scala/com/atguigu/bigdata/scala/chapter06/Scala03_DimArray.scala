package com.atguigu.bigdata.scala.chapter06

object Scala03_DimArray {

    def main(args: Array[String]): Unit = {
        // 多维数组
        // Java中的数组的元素是数组，就是多维数组
        val arr = Array.ofDim[Double](3,4)
        //
        //[ [], [], []  ]

        //arr(1)(1) = 11.11
        //println(arr(10)(10))
    }
}
