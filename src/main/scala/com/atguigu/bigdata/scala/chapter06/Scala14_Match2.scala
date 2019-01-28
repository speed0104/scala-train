package com.atguigu.bigdata.scala.chapter06

object Scala14_Match2 {

    def main(args: Array[String]): Unit = {

        // 对象匹配
        /*
        object Square {
            def unapply(z: Double): Option[Double] = Some(math.sqrt(z))
        }
        // 模式匹配使用：
        val number: Double = 36.0
        number match {
            case Square(n) => println(n)
            case _ => println("nothing matched")
        }
        */

        object Names {
            def unapplySeq(str: String): Option[Seq[String]] = {
                if (str.contains(",")) Some(str.split(","))
                else None
            }
        }
        val namesString = "Alice,Bob,Thomas"
        namesString match {
            case Names(first, second, third) => {
                //println("the string contains three people's names")
                // 打印字符串
                //println(s"$first $second $third")
            }
            case _ => println("nothing matched")
        }

        // x y
        //val x = "Hello"
        //val y = " Scala"
        //println("" + 1 + 1 + x + "-" + y + "!")
        // 在字符串前增加s,那么字符串中就可以使用表达式获取变量的值：$变量名
       // println(s"11$x - $y!")

        val map = Map("A"->1, "B"->0, "C"->3)
        for ( (k, 0) <- map ) {
            println(k)
        }

    }
}

