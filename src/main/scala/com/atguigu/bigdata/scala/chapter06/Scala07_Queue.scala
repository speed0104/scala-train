package com.atguigu.bigdata.scala.chapter06

import scala.collection.mutable

object Scala07_Queue {

    def main(args: Array[String]): Unit = {

        // 队列
        val q = new mutable.Queue[Any]

        // 追加元素
        q += 1

        // 追加集合中的元素
        q ++= List(2, 3, 4)
        //q += List(2, 3, 4)

        // 删除元素 : (2, 3, 4)
        q.dequeue()

        // 增加元素
        q.enqueue(9, 8, 7)

        println(q)

        // 返回队列中第一个（头）元素
        println(q.head)

        // 返回队列中最后一个元素
        println(q.last)

        // 返回队列中尾部数据(排除头部后，剩下的数据集合)
        println(q.tail)
    }
}
