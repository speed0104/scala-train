package com.atguigu.bigdata.scala.chapter05.section7;

public class TestInterface {
    public static void main(String[] args) {

        // 多态传递
        //A a = new C();
        A a = new C();

        //System.out.println(a);

        System.out.println(C.class.getInterfaces().length);
    }
}
interface A {

}
class B implements  A {

}

class C extends B {

}
