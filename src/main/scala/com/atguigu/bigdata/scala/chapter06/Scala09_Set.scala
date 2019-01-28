package com.atguigu.bigdata.scala.chapter06

import scala.collection.mutable

object Scala09_Set {

    def main(args: Array[String]): Unit = {

        // 创建集 Set
        //var set = Set(1, 2, 3)
        var set = mutable.Set(1, 2, 3, 4, 5)

        // 增加元素
        set.add(7)

        set += 7

        //set = set.+(8)
        set = set + 8

        // 删除元素
        set -= 8
        set.remove(7)

        //println(set)

        // 循环遍历
        for ( item <- set ) {
            //println(item)
        }


    }
}
