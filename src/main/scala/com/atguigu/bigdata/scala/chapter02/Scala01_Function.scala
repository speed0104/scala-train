package com.atguigu.bigdata.scala.chapter02

object Scala01_Function {

    def main(args: Array[String]): Unit = {

        //printMsg()
        //println("result = " + sum1(1, 2));
        printMsg

        // 函数返回值类型总结
        // 1) 如果明确函数无返回值，那么可以采用Unit声明，等同于java中void
        // 2）如果函数体采用return关键字返回结果，那么函数在声明时应该增加返回值类型
        //   2-1）如果返回值类型为Unit, 那么return关键字即使声明也会被忽略
        // 3）如果函数明确没有返回值，那么Unit可以省略, 但是最好不要省略
        // 4）如果函数省略返回值类型，那么函数体中不能显示调用return, 函数体会根据最后一行代码自动推断返回值类型
    }
    /*

    function sum(  ) {
        alert("");
    }
     */

    def printMsg() = {
        println("函数声明")
    }


    // 如果函数明确没有返回值，那么函数体中return是不起作用。那么函数的结果为（）
    def sum( v1 : Int, v2 : Int ): Int = {
        return v1 + v2
    }

    // Scala中函数的返回值可以不用return声明, 函数会使用最后一行代码的结果作为函数的返回值
    def sum1( v1 : Int, v2 : Int ) = {
        //return v1 + v2
        //println(v1 + v2)
        v1 + v2
    }
}
