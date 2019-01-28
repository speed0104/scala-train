package com.atguigu.bigdata.scala.chapter05

object Scala02_Object {
    def main(args: Array[String]): Unit = {

        // 构造对象时，会执行主构造函数，所谓的主构造函数，其实就是类的主体内容

        // 主构造方法如果没有参数，那么小括号可以省略，构建对象时调用的构造方法的小括号也可以省略
        //val user = new Emp1("zhangsan", 10);
        //val emp = new Emp1(age=30)

        // 创建对象，但是主构造器是私有的, 不能直接调用
        val emp = new Emp2("zhangsan");
        println(emp)
    }
}
class Emp( name : String ) {
    // 类体
    // 构造方法体
    println("name =" + name)
}
class Emp1() {

    //println(name + "," + age)


    // 辅助构造器
    // 辅助构造器不能直接构建对象，需要调用主构造器调用对象
    def this( name : String ) {
        // 在辅助构造器中一定要有调用主构造器的逻辑
        this()
    }

    def this( name : String, age : Int ) {
        this(name)
    }

}

class Emp2 private () {

    // 赋值构造器的声明不能和主构造器的声明一致，会发生错误
    def this(name : String) {
        this
    }
}








