package com.dawang.androidsample.algorithm.swordoffer;

import org.junit.Test;

public class FirstNotRepeatingChar {

    @Test
    public void test(){
        String charStr = "alslflsjflsjafr";
        int result = FirstNotRepeatingChar(charStr);

        System.err.print(result+":"+charStr.charAt(result));
    }

    public int FirstNotRepeatingChar(String str) {

        int[] index = new int[256];
        for(int i = 0; i < str.length(); i++){
            if(index[str.codePointAt(i)] == 0){
                index[str.codePointAt(i)] = i+1;
            } else if (index[str.codePointAt(i)] != 0){
                index[str.codePointAt(i)] = -1;
            }
        }

        int min = -1;
        for(int i = 0; i < 256; i++){
            if(index[i] > 0 ){
                if(min == -1){
                    min = index[i];
                } else if (min > index[i]){
                    min = index[i];
                }
            }
        }

        return min - 1;
    }
}
