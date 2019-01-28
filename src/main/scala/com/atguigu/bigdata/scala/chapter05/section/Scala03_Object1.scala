package com.atguigu.bigdata.scala.chapter05

object Scala03_Object1 {

    def main(args: Array[String]): Unit = {


        // Scala03_Object1$
        //println(Scala03_Object1)

        // 使用伴生对象的apply方法构建对象
        // 伴生对象()
        val p = Person("zhangsan")

        println(p)
    }
}
class Person private() {

}
object Person {
    def apply(): Person = new Person() // 这里也可以采用单例模式实现
    def apply(name : String) : Person = new Person()


}
