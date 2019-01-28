package com.atguigu.bigdata.scala.chapter06

import scala.collection.mutable

object Scala08_Map {

    def main(args: Array[String]): Unit = {

        // 创建不可变映射集合Map
        // Java : key-Val
        // 构建Map集合中，集合中的元素其实是Tuple2类型
        val map1 = Map("Alice" -> 10, "Bob" -> 20, "Kotlin" -> 30)
       // println(map1)

        // 创建可变映射集合Map
        val map2 = mutable.Map("Alice" -> 10, "Bob" -> 20, "Kotlin" -> 30)
        //println(map2)


        // 创建空的Map集合
        val map3 = new mutable.HashMap[String, Int]()
       // println(map3)

        // 使用元组的方式构建Map集合
        var map4 = mutable.Map( ("A", 1), ("B", 2), ("C", 3) )
       // println(map4)

        // 获取数据
        //println(map4("A"))

        // 如果从Map集合中获取不存在的key的数据，会发生异常：NoSuchElementException
        //println(map4("AB"))
        //if( map4.contains("B") ) {
            //println(map4("B"))
        //}

        // 上面的方式在访问时会出现异常，所以不推荐使用
        // get方法会将数据进行包装
        //println(map4.get("CC").get)

        //println(map4.getOrElse("CC", 100))

        // 更新集合中的数据
        map4("A") = 20

        //println(map4("A"))

        // 增加元素
        //map4 += ( "D" -> 4 )
        //map4 += ( "B" -> 50 )

        // 删除元素
        map4 -= ("B", "D")

        // 增加多个元素

        //map4 = map4 + ("E"->1, "F"->3)
        val map5 = map4 + ("E"->1, "F"->3)

        //println(map5)

        // 循环集合
        for ( (k, v) <- map5 ) {
            //println(k +"="+v)
        }

        for ( k <- map5.keys ) {
            //println(k + "=" + map5(k))
        }

        for ( v <- map5.values ) {
            //println(v)
        }

        for ( t <- map5 ) {
            println(t._1 + "=" + t._2)
        }
    }
}
