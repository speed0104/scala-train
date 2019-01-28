package com.atguigu.bigdata.scala.chapter04

object Scala06_Function {
    def main(args: Array[String]): Unit = {

        // 声明函数
        // Scala语法中任何的语法结构都可以嵌套其他语法结构
        /*
        def result( i : Int = 0) = {
            if ( i == 1 ) {
                println("i = " + i) // void
            } else {
                i   // Int
            }
        }

        // AnyVal  Any
        //val r = res

        // 如果函数声明参数，那么在调用时，必须传递参数
        //result(1)

        // 如果函数声明参数的同时进行了初始化操作，那么在调用时，可以不传递参数

        // @RequestParam
        println(result(5))
        */

        /*
        def sum ( num1 : Int, num2 : Int = 20 ): Int = {
            return num1 + num2
        }

        println(sum(30))
        */

        // 可变参数
        /*
        def sum( is : Int*): Int = {
            var result = 0

            for ( i <- is) {
                result = result+i
            }

            result
        }

        println( sum(1, 2, 3) )
        println( sum(1, 2, 3, 4, 5) )
        println( sum() )
        */

        // 递归函数
        // 递归函数不能省略返回值类型
        /* */
        /*
        def f8(n: Int) : Int = {
            if(n <= 0)
                1
            else
                n * f8(n - 1)
        }


        println("*" * 20)
        */

        // 如果函数声明时没有返回值类型，那么函数会根据最后一行代码推断返回值类型
        // 如果函数声明时没有返回值类型并且同时没有使用等号声明，那么函数不会推断最后一行代码
        // 我们将这样声明的函数称之为过程
        //  function test() {}
        /*
        def f9(){
            //println("f9")
            "zhangsan"
        }

        println(f9())

        */

        def f10(): Unit = {
            println("f10")
        }

        lazy val result = f10()

        println(".........")
        println(result)


    }
}
