package com.atguigu.bigdata.scala.chapter02

object Scala01_Var {

    // 使用val声明的变量在编译之后等同于增加final关键字修饰，所以值无法改变
    val age : Int = 20

    def main(args: Array[String]): Unit = {

        // 声明变量
        var username : String = "zhangsan"

        username = "lisi"


        // val声明的变量无法改变值
        //age = 30

        // 访问变量
        println(username + "," + age)

       // val User user = userDao.queryUser();


       // user = userDao.queryUser();
    }
}
