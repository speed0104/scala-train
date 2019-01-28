package com.atguigu.bigdata.scala.chapter06;

import java.util.ArrayList;
import java.util.List;

public class TestList {
    public static void main(String[] args) {

        List<Integer> list = new ArrayList<Integer>();
        list.add(1);
        list.add(2);
        list.add(3);

        List<Integer> newlist = new ArrayList<Integer>();

        for ( Integer i : list ) {
            newlist.add(i * 2);
        }

        System.out.println(newlist);

    }
}
