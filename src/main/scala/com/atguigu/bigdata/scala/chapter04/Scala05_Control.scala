package com.atguigu.bigdata.scala.chapter04

import util.control.Breaks._

object Scala05_Control {
    def main(args: Array[String]): Unit = {

        // 逻辑判断 if else
        val value = 1

        // if else条件表达式有结果
        // 如果判断逻辑中返回的结果类型相同，那么变量结果类型能够自动推断出来，如果结果类型不相同，那么会设定类型为Any
        /*
        val result = if ( value == 1 ) {
            println("val = 1")
            "zhangsan"
            // String
        } else {
            println("val != 1")
            "lisi"
            // ()
        }*/

        /*
        val result = if ( value == 1 ) {
            1 + 1 Int
        }
        */

        //val result = if (flg) 1
        //else 0

        // () => Unit => void
        //println(re)

        // while 循环
        /*
        var n = 1;
        val while1 = while(n <= 10){
            n += 1
        }
        println(while1)
        println(n)
        */

        // 可中断函数
        // 函数的参数列表声明可以使用大括号来代替小括号
        // 将一段逻辑代码最为参数传给另外一个方法，这个函数称之为高阶函数
        // λ表达式（运算） ==> 匿名函数 ==> 将一段代码传递给另外一个方法。

        // 所谓break方法其实就是抛出异常，让breakable方法返回

        /*
        var n = 1;
        breakable{
            while(n <= 20){
                n += 1;
                if(n == 18){
                    break()
                }
            }

        }
        */

        // for循环
        // 范围数据的循环
        //int[] ids = new int[4]
        //for ( int id : ids ) {

        // 1 + 1 ==> 1.+(1)
        //for ( i <- 1 to 5) { // 1, 2, 3, 4 ,5
        //for ( i <- 1 until 5) {  // 1, 2, 3, 4
        //    println(i)
        //}

        /*

        for ( int i = 1; i <= 3; i++  ) {
            if ( i == 2 ) {
               continue;
            }

            println(i);
        }

         */

        // 循环守卫
        for ( i <- 1 to 3 if i != 2 ) {
            //println(i)
        }

        //
        for ( i <- 1 to 3; j = 4 - i  ) {
           // println(j)
        }

        // 嵌套循环
        /*
           // Java
           for ( int i = 0; i < 10; i++ ) {
               for ( int k = 0; k < 5; k++ ) {
                   println(i + " = " + k);
               }
           }

         */
        // Scala
        //for { i <- 1 to 3;
        // j <- 1 to 3 } {
        //    print(i*j + " ")
        //}

        // 循环产生新集合
        val for5 = for(i <- 1 to 10) yield i
        println(for5)






    }
}
