package com.atguigu.bigdata.scala.chapter04;

public class TestException {
    public static void main(String[] args) {

        try {
            // 可疑代码
            int i = 0;
            int b = 10;
            int c = b / i; // 执行代码时，会抛出ArithmeticException异常
        } catch (ArithmeticException e) {
            System.out.println("xxxxx");
        } catch(Exception e) {
            System.out.println("yyyyy");
        }finally {
            // 最终要执行的代码
        }




    }
}
