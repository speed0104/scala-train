package com.atguigu.bigdata.scala.chapter06

import scala.collection.mutable

object Scala06_List {

    def main(args: Array[String]): Unit = {

        // 创建列表数据
        // List默认为不可变的集合
        val arr1 = List(1, 2, 3)
        //println(arr1)
        // 创建空列表 ： Nil
        val arr2 = Nil
        //println(arr2)

        // 访问列表中的元素
        //println(arr1(1))
        // 向列表中增加元素,因为Scala默认为不可变集合，所以增加元素以后，集合本身不变，但是会产生信息的列表对象

        // :+运算符表示在列表的最后增加数据
        val arr3 = arr1 :+ 4
        //println(arr3)

        // +:运算符表示在列表的前面增加数据
        val arr4 = 100 +: arr1
        //println(arr4)

        // 使用::运算符来创建集合
        // 创建List
        // ::可以向集合中添加元素
        // 使用::运算符时，集合对象一定要放置在最右边, 运算规则是从右向左

        // List(1, 2, 3, List(1, 2, 3))
        val list5 = 1 :: 2 :: 3 :: arr1 :: Nil

        // List(1, 2, 3, 1, 2, 3)
        val list6 = 1 :: 2 :: 3 :: arr1

        // 使用:::运算符是将集合中的每一个元素加入到空集合中去
        val list7 = 1 :: 2 :: 3 :: arr1 ::: Nil

        println(list5)
        println(list6)
        println(list7)

    }
}