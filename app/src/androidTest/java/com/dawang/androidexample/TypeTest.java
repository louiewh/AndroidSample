package com.dawang.androidexample;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Type;

/**
 * Created by DaWang on 2017/4/12.
 */


@RunWith(AndroidJUnit4.class)
public class TypeTest {
    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        Class<?> clazz = RetrofitTest.class;
        Method[] methodArray = clazz.getMethods();

        Method method = clazz.getDeclaredMethod("useAppContext");

        Annotation[] annotations = method.getAnnotations();
        Type[] types = method.getParameterTypes();
        Annotation[][] parameterAnnotations = method.getParameterAnnotations();
    }
}