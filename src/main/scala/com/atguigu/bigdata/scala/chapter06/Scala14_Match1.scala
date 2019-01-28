package com.atguigu.bigdata.scala.chapter06

object Scala14_Match1 {

    def main(args: Array[String]): Unit = {

        // 数组匹配
        /*
        for (arr <- Array(Array(0), Array(1, 0), Array(0, 1, 0), Array(1, 1, 0), Array(1, 1, 0, 1))) {

            val result = arr match {
                case Array(0) => "0"
                case Array(x, y) => x + "=" + y
                case Array(0, _*) => "以0开头和数组"
                case _ => "什么集合都不是"

            }

            println("result = " + result)
        }
        */
        // 列表匹配
        /*
        for (list <- Array(List(0), List(1, 0), List(0, 0, 0), List(1, 0, 0))) {
            val result = list match {
                case 0 :: Nil => "0"
                case x :: y :: Nil => x + " " + y
                case 0 :: tail => "0 ..."
                case _ => "something else"
            }
            println(result)
        }
        */
        // 元组匹配
        for (pair <- Array((0, 1), (1, 0), (1, 1))) {

            val result = pair match {
                case (0, _) => "0 ..."
                case (y, 0) => y
                case _ => "neither is 0"
            }
            println(result)
        }

        // 循环
        /*
        val map = Map("A"->1, "B"->2)

        for ( (k, v) <- map ) {

        }
        */
    }
}
