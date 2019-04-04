package com.dawang.androidsample.java;

import org.junit.Test;

/**
 * @author louie.wang
 * @Date 2018/4/26
 * @Description
 */

public class StringTest {

    @Test
    public void EqualsTest(){

        String str1 = "HelloWorld";
        String str2 = "HelloWorld";
        System.out.println(str1 == str2);

        String str3 = new String("HelloWorld");
        System.out.println(str1 == str3);

        String str4 = str3.intern();
        System.out.println(str4 == str1);
    }
}
