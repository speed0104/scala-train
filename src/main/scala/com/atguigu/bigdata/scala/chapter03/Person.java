package com.atguigu.bigdata.scala.chapter03;

public class Person implements Play {

    private String name;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
class Emp extends Person {

}
class Test {
    public static void main(String[] args) throws  Exception {

        //Person person = new Person();

        //person.setName("zhangsan");

        //System.out.println(person.getName()); // name
        Thread t1 = new Thread();
        Thread t2 = new Thread();

        t1.start();
        t2.start();

        t1.sleep(1000); // 和t1无关，Thread（当前运行的线程）
        t2.wait();  // 和t2相关

        Emp emp = new Emp();


    }
}