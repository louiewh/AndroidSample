package com.dawang.androidsample.java;

import org.junit.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Type;

/**
 * Created by DaWang on 2017/4/12.
 */


public class TypeTest {

//    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.

        Class<?> clazz = TypeTest.class;
        Method[] methodArray = clazz.getDeclaredMethods();

        Method method = clazz.getDeclaredMethod("useAppContext");
        Annotation[] annotations = method.getAnnotations();

        method = clazz.getDeclaredMethod("testApi");
        Type[] types = method.getParameterTypes();
        Annotation[][] parameterAnnotations = method.getParameterAnnotations();
    }


    @Deprecated
    public void testApi(int a){
        System.err.println(a);
    }


    @Test
    public void quickSortTest(){
//        int[] array = {5, 6, 9, 1, 7, 6,2};
        int[] array = {5, 6};

        quickSort(array, 0, array.length-1);
        System.err.println(array);

    }

    void quickSort(int[] array, int start, int end){

        if (start < end) {
            int i = start;
            int j = end;
            int number = array[start];
            while (i < j) {
                while (i < j && array[j] >= number) {
                    j--;
                }

                array[i] = array[j];

                while (i < j && array[i] <= number) {
                    i++;
                }

                array[j] = array[i];
            }

            array[i] = number;

            quickSort(array, start, i - 1);
            quickSort(array, j + 1, end);
        }
    }
}