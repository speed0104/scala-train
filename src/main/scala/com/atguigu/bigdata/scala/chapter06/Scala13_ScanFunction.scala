package com.atguigu.bigdata.scala.chapter06

object Scala13_ScanFunction {

    def main(args: Array[String]): Unit = {

        def minus( num1 : Int, num2 : Int ) : Int = {
            num1 - num2
        }


        //  0 1 2 3 4 5
        val i8 = (1 to 5).scanLeft(0)(minus)
        println(i8)


    }
}
