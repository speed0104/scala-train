package com.atguigu.bigdata.scala.chapter06;

import java.util.HashMap;
import java.util.Map;

public class Test {
    public static void main(String[] args) {

        String sentence = "AAAAAAAAAABBBBBBBBCCCCCDDDDDDD";

        Map<Character, Integer> charCountMap = new HashMap<Character, Integer>();

        char[] cs = sentence.toCharArray();

        for ( char c : cs ) {

            if ( charCountMap.containsKey(c) ) {
                Integer count = charCountMap.get(c);
                charCountMap.put(c, count + 1);
            } else {
                charCountMap.put(c, 1);
            }

        }


        // 1)  map, c= 'A'
        // 2)  map, c='A'
        // 3)  map, c='B'

        System.out.println(charCountMap);

    }
}
