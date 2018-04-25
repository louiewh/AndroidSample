package com.dawang.androidsample;

import org.junit.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Type;

/**
 * Created by DaWang on 2017/4/12.
 */


public class TypeTest {

    @Test
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
}