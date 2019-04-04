package com.dawang.androidsample.algorithm.swordoffer;


/*
* 输入一个字符串,按字典序打印出该字符串中字符的所有排列。
* 例如输入字符串abc,则打印出由字符a,b,c所能排列出来的所有字符串abc,acb,bac,bca,cab和cba。
*
*/

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;

public class Permutation {


    @Test
    public void test(){

        ArrayList<String> list = Permutation("123");

        for (String tmp : list) {
            System.err.println(tmp);
        }
    }

    public  ArrayList<String> Permutation(String str) {

        ArrayList<String> result = new ArrayList<>();
        if(str == null || str.length()  == 0){
            return  result;
        }

        HashSet<String>  set = RecursionPermutation(str, 0, str.length());

        result.addAll(set);
        result.sort((o1, o2) -> o1.compareTo(o2));
        return result;
    }

    HashSet<String> RecursionPermutation(String string, int start, int end){
        HashSet<String> set = new HashSet<>();
        if(start == end - 1){
            set.add(string);
            return set;
        }

        char[] arrays = string.toCharArray();
        for (int i = start; i < end; i++){
            swap(arrays, start, i);

            set.addAll(RecursionPermutation(String.valueOf(arrays), start+1, end));

            swap(arrays, i, start);
        }

        return set;
    }

    void swap(char[] array, int i, int j){
        char temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
