package com.atguigu.bigdata.scala.chapter05.section;

public class TestJavaClass {

    public static void main(String[] args) {

        OuterClass outer1 = new OuterClass();
        OuterClass outer2 = new OuterClass();

        // 创建Java成员内部类
        OuterClass.InnerClass inner1 = outer1.new InnerClass();
        OuterClass.InnerClass inner2 = outer2.new InnerClass();

        inner1.test(inner2);
        inner2.test(inner1);

        // 创建Java静态内部类
        OuterClass.StaticInnerClass staticInner = new OuterClass.StaticInnerClass();

    }
}
class OuterClass {
    class InnerClass {

        public void test( InnerClass ic ) {
            System.out.println(ic);
        }
    }

    static class StaticInnerClass {

    }
}
