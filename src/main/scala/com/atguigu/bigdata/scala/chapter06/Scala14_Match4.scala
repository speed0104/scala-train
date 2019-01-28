package com.atguigu.bigdata.scala.chapter06

object Scala14_Match4 {

    def main(args: Array[String]): Unit = {

        val list = List(1, 2, 3, 4, "abc")

        /*
        def addOne( i : Any ): Any = {
            i match {
                case x:Int => x + 1
                //case _ => i
            }
        }
        */

        val addOne = new PartialFunction[Any, Int] {
            def apply(any: Any) = any.asInstanceOf[Int] + 1
            def isDefinedAt(any: Any) = if (any.isInstanceOf[Int]) true else false
        }

        // 返回偏函数
        def f2:PartialFunction[Any, Int] = {
            case i:Int => i+1 // case语句会自动转换为偏函数
        }


        //println(list.collect(f2))
        println(list.collect{ case i: Int => i+1 }  )

        //list.map
    }
}
