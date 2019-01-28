package com.atguigu.bigdata.scala.chapter07

object Scala01_Function {

    def main(args: Array[String]): Unit = {

        // 声明了匿名函数
        //val t = (x:Int)=>println("Hello")

       // println(t)
        // 调用函数
        //t(10)

        // 声明高阶函数
        /*
        def test(f: Double => Double) = {
            f(10)
        }

        def test1() = ()=>println("...")

        def sum(d:Double):Double = {
            d + d
        }
        */

        //println(test(sum))
        //val f = test1()
        //f()
        //test1()()

        //def multipleThree(x: Double) : Double = {
        //    3 * x
        //}
        //

       // (x: Double) => 3 * x
        // (x) => 3*x
        // x => 3 * x
        // 3 * _

        //val list = List(1, 2, 3, 4)

        //println(list.map((x:Int)=>x + 1))
        //println(list.map((x)=>x + 1))
        //println(list.map(x=>x + 1))
        //println(list.map(_ + 1))

        // 闭包
        /*
        def minusxy(x: Int) = {
           def test(y: Int) : Int = {
               x - y
           }
            test _
        }

        println(minusxy(20)(10))
        */

        // 函数柯里化
        //def mulCurry2(x: Int)(y:Int) = x * y

        //println(mulCurry2(10)(20))

        val a = "a"
        val aa = "A"


        def eq( s1 : String, s2 : String ): Boolean = {
            s1.equals(s2)
        }

        //println(a.checkEq(aa)(eq))
        //println(a.checkEq(aa)( (x, y) => x.equals(y) ))
        println(a.checkEq(aa)( _.equals(_) ))
    }

    implicit class TestEq( s : String ) {
        def checkEq( ss : String )( f: (String, String)=> Boolean ): Boolean = {
            // 把字符串变成小写
            f(s.toLowerCase, ss.toLowerCase)

        }
    }

}
