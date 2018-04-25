package com.dawang.androidsample;

import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * Created by DaWang on 2017/4/12.
 */


public class ReflectTest {

    @Test
    public void createInstanceTest(){
        Object instance;
        try {
            Class<?> klass = Class.forName("java.util.ArrayList");
            instance = klass.newInstance();
            if(instance instanceof List<?>){
                System.out.println("instance is a List");
            }

            if(instance instanceof List){
                System.out.println("Object is a List");
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }

        try{
            Class<?> klass = Class.forName("java.lang.StringBuilder");
            Constructor<?> constructor = klass.getConstructor(String.class);
            instance = constructor.newInstance("Hello World");
            System.out.println("instance is a :"+ instance.toString());

        } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InvocationTargetException | InstantiationException e) {
            e.printStackTrace();
        }
    }


}