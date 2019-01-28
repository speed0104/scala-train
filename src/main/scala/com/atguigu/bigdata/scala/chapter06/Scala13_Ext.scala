package com.atguigu.bigdata.scala.chapter06

object Scala13_Ext {

    def main(args: Array[String]): Unit = {

        // 拉链
        val list1 = List(1, 2 ,3)
        val list2 = List(4, 5, 6)

        //println(list1.zip(list2))

        //list1 zip list2

        // 迭代器
        val iterator = List(1, 2, 3, 4, 5).iterator
        //while (iterator.hasNext) {
        //    println(iterator.next())
        //}
        for(enum <- iterator) {
            //println(enum)
        }

        // 流
        def numsForm(n: BigInt) : Stream[BigInt] = n #:: numsForm(n + 1)

        //val stream = numsForm(1)
        //println( stream.head )
        //println( stream.tail.head )
        //println(stream.last)

        //def multi(x:BigInt) : BigInt = {
        //    x * x
        //}
        //println(numsForm(5).map(multi))


        // View
        /*
        def multiple( num : Int ) : Int = {
            num
        }
        def eq( i : Int ) : Boolean = {
            i.toString.equals(i.toString.reverse)
        }
        val viewSquares = (1 to 100)
            .view
            .map(multiple)
            .filter(eq)
        println(viewSquares)
        for(x <- viewSquares){
            print(x + "，")
        }
        */

        // 并行集合
        //(1 to 5).foreach(println(_))
        //(1 to 5).par.foreach(println(_))

        /*
        val result1 = (0 to 100).map{case _ => Thread.currentThread.getName}.distinct
        val result2 = (0 to 100).par.map{case _ => Thread.currentThread.getName}.distinct
        println(result1)
        println(result2)
        */

        // 操作符
        val oper = new Operate13
        // 无参函数可以省略小括号
        //  1) 如果不省略，调用函数时，可以省略，也可以不省略
        //  2) 如果省略，调用函数时，必须省略,为了访问一致性
        //oper++

        !oper

    }
}
class Operate13 {

    def ++ = "123"

    // 声明前置运算符
    def unary_! = println("!!!!!!!")
}
