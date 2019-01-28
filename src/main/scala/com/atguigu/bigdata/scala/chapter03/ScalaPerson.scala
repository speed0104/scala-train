package com.atguigu.bigdata.scala.chapter03.test

import com.atguigu.bigdata.scala.chapter03.Student

class ScalaPerson {
    // scala中声明属性必须显示的初始化，否则编译出错
    // 因为Scala可以根据属性值的类型自动推断，所以属性类型可以省略
    // 如果给属性设定null作为默认值，那么属性类型最好声明
    // 如果将下划线赋值给属性，那么等同于赋值为当前类型的java默认值, String 就是null, Int就是0
    private var name : String = _

    def setName(name:String) : Unit = {
        this.name = name;
    }

    def getName() : String = {
        return name;
    }
}
// 如果希望类中存在静态内容，可以使用scala中特有的伴生对象
//    从技术的角度来讲，scala还是没有静态的内容，只不过将伴生对象又生成了一个新的类，实现属性get方法的调用
// 伴生对象的名称应该和类名相同
// 伴生类 <==> 伴生对象
// 伴生对象中的属性或方法可以通过类名直接调用访问
// 伴生对象的声明应该和伴生类的声明在同一个源码文件中，但是如果没有伴生类，也就没有所谓的伴生对象了，所以放在哪里就无所谓了。
//object ScalaPerson {
//   var sex : Boolean = true
//}

class ScalaEmp {
    // 在Scala中，声明属性，编译器会自动添加类似于set,get方法

    //    set : 属性名_=(参数)
    //    get : 属性名()

    // 如果属性声明为private访问权限，那么set,get方法也是private
    private var name : String = _
    // 如果属性不声明访问权限，那么set，get方法是公共的
    var age : Int = _
}
object ScalaEmp {

}

object TestScal {
    def main(args: Array[String]): Unit = {




        // 创建对象
        //val person : ScalaPerson = new ScalaPerson()
        // 对象类型可以省略，可以通过赋值操作自动推断
        //val person = new ScalaPerson()
        //person.setName("lisi")
        //println(person.getName())

        //val emp = new ScalaEmp()
        //println(emp.age)

        // set
        //emp.age = 20

        // get
        //println(emp.age)

        //println(ScalaPerson.sex)
        //println("package path...")
        val stu = new Student()

        stu.age

        1 + 1

        1 :: Nil

    }
}
