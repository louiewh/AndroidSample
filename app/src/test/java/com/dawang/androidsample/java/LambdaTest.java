package com.dawang.androidsample.java;

import org.junit.Test;
import java.util.Arrays;
import java.util.Comparator;

/**
 * @Description
 * @Author: louie.wang
 * @Email:
 * @Date: 2018/5/8
 */

public class LambdaTest {

    @Test
    public void lambdaTest(){
        String[] stringArray = {"123456", "1234"};

        Comparator<String>  o = (a, b)->a.length() - b.length();

        Arrays.sort(stringArray, o);

        System.out.println(Arrays.toString(stringArray));
    }

    @Test
    public void innerTest(){
        String[] stringArray = {"123456", "1234"};

        Arrays.sort(stringArray, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.length() - o2.length();
            }
        });

        System.out.println(Arrays.toString(stringArray));
    }
}
