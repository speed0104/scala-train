package com.atguigu.bigdata.scala.chapter05.section6;

public class Test {
    public static void main(String[] args) {

        A a = new B();

        // 动态绑定技术
        // 在运行过程中，调用对象的成员方法，JVM会将当前调用的方法和对象的实际内存进行绑定
        // 成员属性没有动态绑定技术，在哪里声明，在哪里使用
        System.out.println(a.sum());

        // 匿名子（实现）类
        C c = new C() {};
    }
}

abstract class C {

}

class A {

    public int i = 10;

    public int sum() {
        return getI() + 10;
    }

    public int getI() {
        return i;
    }
}
class B extends A {
    public int i = 20;

//    public int sum() {
//        return i + 20;
//    }

    public int getI() {
        return i;
    }
}
