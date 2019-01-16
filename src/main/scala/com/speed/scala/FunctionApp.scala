package com.speed.scala

object FunctionApp {
  def main(args: Array[String]): Unit = {
    println(max(3,8))
    println(add(3,8))
    //没有参数时,括号可以不写
    println(three)
    //默认参数大的方法调用可以省略参数,但括号不能省略
    say()
    say("zhang")
  }


  def max(x: Int, y: Int): Int = {
    if (x > y) {
      x
    } else {
      y
    }
  }

  //最后一行就是返回值
  def add(x:Int,y:Int) : Int={
    x + y
  }

  //写在一行不需要大括号,返回值可不写,自动推断
  def three() = 1+1+1

  //默认参数的方法,Unit表示无返回值
  def say(name:String = "li"): Unit ={
    println(name)
  }

}
