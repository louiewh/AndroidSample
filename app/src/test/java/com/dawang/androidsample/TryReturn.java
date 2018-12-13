package com.dawang.androidsample;

import org.junit.Test;

/**
 * @Description
 * @Author: louie.wang
 * @Email:
 * @Date: 2018/5/11
 */
public class TryReturn {

    @Test
    public void testFunction(){
        sum(1, 2);
    }


    public int sum(int a, int b){

        return a + b;
    }


    public int get() {
        try {
            return 0;
        } catch (Exception e) {
            e.printStackTrace();
            return 1;
        } finally {
            return 2;
        }
    }

    public int get2() {
        int a = 10;
        try {
            return a;
        } catch (Exception e) {
            e.printStackTrace();
            return a;
        } finally {
            return 2;
        }
    }

}
