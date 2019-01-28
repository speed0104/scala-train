package com.atguigu.bigdata.scala.chapter05

import com.atguigu.bigdata.scala.chapter05

object Scala01_Class {

    def main(args: Array[String]): Unit = {

        val outer1 : ScalaOuterClass = new ScalaOuterClass();
        val outer2 : ScalaOuterClass = new ScalaOuterClass();

        // Scala创建内部类的方式和Java不一样，将new关键字放置在前，使用  对象.内部类  的方式创建
        val inner1 = new outer1.ScalaInnerClass()
        val inner2 = new outer2.ScalaInnerClass()

        // Scala中内部类从属于外部类的对象，所以外部类的对象不一样，创建出来的内部类也不一样，无法互换使用
        //inner1.test
        //inner2.test
        inner1.test(inner2)
        inner2.test(inner1)

        val staticInner = new chapter05.ScalaOuterClass.ScalaStaticInnerClass()

        println(staticInner)

    }
}
class ScalaOuterClass {
    outer =>
    class ScalaInnerClass {
        //def info = println("name = " + ScalaOuterClass.this.name)
        //def info = println("name = " + outer.name)

        // 在方法声明上，如果使用  外部类#内部类  的方式，表示忽略内部类的对象关系，等同于Java中内部类的语法操作
        // 我们将这种方式称之为 类型投影（忽略对象的创建方式，只考虑类型）
        def test(ic: ScalaOuterClass#ScalaInnerClass): Unit = {
            System.out.println(ic)
        }
    }

    val name = "zhangsan"

}
object ScalaOuterClass {
    class ScalaStaticInnerClass {

    }
}
