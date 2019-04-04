package com.dawang.androidsample.algorithm;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class Permutation {

    @Test
    public void test(){
        String str = "abc";
        List<String> result = new ArrayList<>();

        Permutation(str.toCharArray(), 0, str.length()-1, result);
        System.err.println(result);


        str = "acc";
        result.clear();

        Permutation(str.toCharArray(), 0, str.length()-1, result);
        System.err.println(result);
    }

    void Permutation(char[] arrays, int start, int end,  List<String> list){
        if(arrays == null || arrays.length == 0) return;

        if (start == end) {
            list.add(new String(arrays));
        } else {
            for (int i = start; i <= end; i++) {
                if (isLast(arrays, i, end)) {

                    swap(arrays, start, i);
                    Permutation(arrays, start + 1, end, list);
                    swap(arrays, start, i);
                }
            }
        }
    }

    private void swap(char[] arrays, int start, int i) {
        char temp = arrays[start];
        arrays[start] = arrays[i];
        arrays[i] = temp;
    }

    private boolean isLast(char[] arrays, int start, int end){
        for (int i = start+1; i <= end; i++){
            if(arrays[start] == arrays[i]){
                return  false;
            }
        }

        return  true;
    }
}
