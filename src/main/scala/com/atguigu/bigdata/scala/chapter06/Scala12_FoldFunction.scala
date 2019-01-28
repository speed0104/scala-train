package com.atguigu.bigdata.scala.chapter06

object Scala12_FoldFunction {

    def main(args: Array[String]): Unit = {


        // 折叠
        val list = List(1, 2, 3, 4)

        def minus( num1 : Int, num2 : Int ): Int = {
            num1 - num2
        }

        // ((((5-1)-2)-3)-4)
        //println(list.foldLeft(5)(minus))
        // (1 -(2 -(3 - (4-5)))
        //println(list.foldRight(5)(minus))

        val list4 = List(1, 9, 2, 8)
        //def minus( num1 : Int, num2 : Int ) : Int = {
        //    num1 - num2
        //}

        // 0 1 9 2 8
        val i6 = (0 /: list4)(minus)
        println(i6)
    }
}
