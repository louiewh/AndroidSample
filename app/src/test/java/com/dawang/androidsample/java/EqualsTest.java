package com.dawang.androidsample.java;

import org.junit.Test;

/**
 * @author louie.wang
 * @Date 2018/4/26
 * @Description
 */

public class EqualsTest {
    volatile int a = 100;
    @Test
    public void EqualsTest(){

        Integer a = new Integer(8);
        Integer b = 8;
        Integer b2 = 8;

        int c = 8;

        System.out.println( a.equals(b));
        System.out.println( a == b);
        System.out.println( b == b2);

        System.out.println( b == c);
    }
}
