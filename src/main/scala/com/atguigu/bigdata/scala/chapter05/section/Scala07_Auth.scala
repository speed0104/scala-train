package com.atguigu.bigdata.scala.chapter05

// Scala07_Auth$
object Scala07_Auth {

    def main(args: Array[String]): Unit = {

        //var m = new Manager1

        // scala中受保护权限更加的严格，只能子类访问，同包不可以访问。
        //println(m.username)

    }
}
class Manager1 {
    import scala.beans._
    @BeanProperty var  name : String = ""

    protected[bigdata] var username = "zhangsan";
}

class ChildManager extends Manager1 {

    //import scala.beans.{BeanProperty, }

    //@BeanProperty var  age : Int = _
    def test(): Unit = {
        // 子类可以访问父类中protected权限的属性
        //username

        //import scala.collection.mutable.{HashMap, HashSet}

        //java.util.Ha
        //var map = new HashMap()
        //var set = new HashSet()
        //var map = new Hashma

        // 导入的类重命名
        //import java.util.{ HashMap=>JavaHashMap, List}

        //var map = new JavaHashMap()

        //var map = new mutable.HashMap()

        // 隐藏导入的类
        import java.util.{ HashMap=>_, _}
        //HashMap
    }
}
