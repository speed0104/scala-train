package com.atguigu.bigdata.scala.chapter03

import com.atguigu.bigdata.scala.chapter03.test.ScalaEmp

class Student extends ScalaEmp with ScalaPlay with ScalaFly {
   def test(): Unit = {
       println("test")
   }
}
object Student {

}
