package com.atguigu.bigdata.scala.chapter07;

public class TestCurry {
    public static void main(String[] args) {

        //eq("")
    }

    public static boolean eq( String a, String b ) {
        //a.toLowerCase().equals(b.toLowerCase())
        return a.equalsIgnoreCase(b);
    }
}
