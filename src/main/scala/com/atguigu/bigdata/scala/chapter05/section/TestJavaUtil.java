package com.atguigu.bigdata.scala.chapter05.section;

public class TestJavaUtil {
}

class StringUtil {

   // private static StringUtil util = new StringUtil();
    private static StringUtil util = null;

    private StringUtil() {

    }

    private static StringUtil getInstance() {
        if ( util == null ) {
            util = new StringUtil();
        }
        return util;
    }
}
