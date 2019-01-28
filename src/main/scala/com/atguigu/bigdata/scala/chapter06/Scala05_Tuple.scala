package com.atguigu.bigdata.scala.chapter06

object Scala05_Tuple {

    def main(args: Array[String]): Unit = {

        // Scala可以将多个无关的数据封装为一个整体，称之为元组
        var t1 = (1, "a", "b", true, 2)

        // 元组中的数据最多只能放置22个
        //Tuple4
        // 访问元组中的数据,可以通过索引（productElement）访问，也可以采用顺序号（_顺序号）
        //println(t1.productElement(2))

        // 循环元组
        for ( item <- t1.productIterator ) {
            println(item)
        }
    }
}
