package com.dawang.androidsample.java;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class Compare {
    @Test
    public void addition_isCorrect() throws Exception {

        ArrayList<Integer> array = new ArrayList<>();
        array.add(1);
        array.add(1);
        array.add(1);

        sort(array);
        assertEquals(4, 2 + 2);
    }


    private void sort(List<Integer> list){
        Collections.sort(list, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                if(o1 > o2){
                    return 1;
                }

                return 0;
            }
        });

    }
}
