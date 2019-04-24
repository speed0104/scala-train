package com.speed.scala

object BasicTest {


  def main(args: Array[String]): Unit = {

    var arr = new Array[Int](10)

    for(item<-arr){
      println(s"item:$item")
    }

  }

  /**
    * @author Liling
    * @todo 还没完成
    * @example a = 1 , b = 2  a+ b = 3
    * @param a 数字型第一个参数
    * @param b 数字型第二个参数
    * @return 计算后得到的和
    */
  def sum_test(a:Int,b:Int): Int ={
    a + b
  }

}
