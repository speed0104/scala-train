package com.atguigu.bigdata.scala.chapter05.section6

object Scala01_Extends {

    def main(args: Array[String]): Unit = {

        // 子类可以继承父类的属性和方法
        //val emp = new Emp

        //emp.name = "zhangsan"

        //println(emp.name)

       // println(emp.test())

        // 获取对象类型
        //println(classOf[String])

        //val s = "zhangsan"

        //println(s.getClass.getName)
        //println(s.isInstanceOf[String])
        //println(s.asInstanceOf[String])

        //val obj : Person = new Student("zhangsan")

        //printNo(obj)

        //println(obj.name)

        // Java动态绑定技术： Java在调用对象的成员方法时会将方法和对象的实际内存进行绑定，然后调用内存中的方法。
        //println(obj.age)

        val emp : Emp1 = new Emp1()
        println(emp.name)

    }

    def printNo( p : Person ): Unit = {
        if ( p.isInstanceOf[Emp] ) {
            val emp = p.asInstanceOf[Emp]
            println(emp.empno)
        } else if ( p.isInstanceOf[Student] ) {
            val student = p.asInstanceOf[Student]
            println(student.sno)
        }
    }

}
abstract class Person( pname : String ) {

    var name : String = prefix + "_name"

    val prefix : String = "person_"

    // 声明了属性的同时，还会有对应get方法（age()）
    // 一个变量没有初始化，那么这个属性就是抽象属性
    // 抽象属性在编译成字节码文件时，会自动生成抽象方法，所以类必须声明为抽象类，属性并不会声明
    var age : Int

    def test() : String = {
        "123"
    }

    def hello()
}
class Emp(empname : String) extends Person(empname) {

    var empno : Int = 10

    // 声明了属性，也会有get方法（age()）
    override var age : Int = 30

    override def test() : String = {
        "emp..."
        //super.test()
    }

    override val prefix : String = "emp_"

    def hello(): Unit = {

    }

}
class Student( studentname : String ) extends  Person(studentname) {

    var sno : Int = 20

    //override var age : Int = 40
    var age : Int = 40

    override def test() : String = {
        "student..."
    }

    def hello(): Unit = {

    }
}
class Emp1 extends {
    override val prefix = "emp_" // 这样的写法让人无法忍受，不推荐使用
} with Person("person") {
    def hello(): Unit = {

    }
    var age : Int = 40
}





