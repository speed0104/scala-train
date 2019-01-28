package com.atguigu.bigdata.scala.chapter05.section;

public class TestJavaObject {
    public static void main(String[] args) {

        // 创建对象
        // 构建对象时，其实等同于调用类的默认无参构造方法
        User user = new User("zhangsan");

        User user1 = new User("zhangsan", 1);
    }
}
class User {

    // Java中如果给类提供了有参的构造方法，那么无参的构造方法不再提供
    public User(String s) {
        //System.out.println("123");
        this(s, 10);
    }

    public User( String s, int i ) {

    }
}
