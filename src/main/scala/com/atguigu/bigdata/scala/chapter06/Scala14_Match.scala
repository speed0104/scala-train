package com.atguigu.bigdata.scala.chapter06

object Scala14_Match {

    def main(args: Array[String]): Unit = {

        // 模式匹配，类似于Java的switch语法
        /*
        var result = 0

        val c = '*'

        c match {
            case '-' => result = 1
            case '+' => result = -1
            case '%' => result = 100
            case _ if (c.toString.equals("/")) => println("//////") // 模式匹配守卫
            //case '*' =>
            case _   => result = 1000
        }

        println(result)
        */

        /*
        val str = "+-3!"
        for (i <- str.indices) {
            var sign = 0
            var digit = 0

            str(i) match {  // char
                case '+' => sign = 1
                case '-' => sign = -1
                case ch if Character.isDigit(ch) => digit = Character.digit(ch, 10)
                case _ =>
            }
            println(str(i) + " " + sign + " " + digit)
        }
        */

        // 类型匹配
        val a = 3
        val obj = if(a == 1) 1
        else if(a == 2) "2"
        else if(a == 3) BigInt(3)
        else if(a == 4) Map("aa" -> 1)
        else if(a == 5) Map(1 -> "aa")
        else if(a == 6) Array(1, 2, 3)
        else if(a == 7) Array("aa", 1)
        else if(a == 8) Array("aa")

        //println(obj)
        // 模式匹配中的类型匹配不考虑泛型
        val result = obj match {
            case i : Int => i
            case _ : BigInt => Int.MaxValue
            case m : Map[String, Int] => "对象是一个字符串-数字的Map集合"
            case m : Map[Int, String] => "对象是一个数字-字符串的Map集合"
            case a : Array[String] => "对象是一个字符串数组"
            case a : Array[Int] => "对象是一个数字数组"
            case _ => "啥也不是"
        }

        // Array[String] =>  String[]
        // Array[Int] => int[]
        // Map[String, Int]

        println(result)

    }
}
