/*
package com.atguigu.bigdata.scala.chapter05

class User

trait Test

 */

package com.atguigu.bigdata.scala {

    import com.atguigu.bigdata.scala.chapter05.Scala06_Package

    class User11 {

    }


    // 声明包对象，应该和包的名称保持一致，一般用于对包功能的补充，用于声明属性，方法
    package object chapter05 {
        val username = "zhangsan"

        def test(): Unit = {

        }
    }

    package chapter05 {

        object Scala06_Package {
            //var user = new User11()
            println(username)
        }
    }

    package test {

    }

    object Test {
        def main(args: Array[String]): Unit = {


            println(Scala06_Package)
        }
    }
}

