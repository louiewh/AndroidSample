package com.dawang.androidsample;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by DaWang on 2017/4/12.
 */


public class CollectionTest {

    @Test
    public void removeTest(){
        ArrayList<String> arrayList = new ArrayList<>();

        arrayList.add("1");
        arrayList.add("2");
        arrayList.add("3");
        arrayList.add("4");
        arrayList.add("5");
        arrayList.add("6");
        arrayList.add("7");

        Iterator<String> iterator = arrayList.iterator();
        while (iterator.hasNext()) {
            String value = iterator.next();
            if ("5".equals(value)) {
                iterator.remove();
            }
        }
    }

    @Test
    public void removeTest2(){
        CopyOnWriteArrayList<String> arrayList = new CopyOnWriteArrayList<>();

        arrayList.add("1");
        arrayList.add("2");
        arrayList.add("3");
        arrayList.add("4");
        arrayList.add("5");
        arrayList.add("6");
        arrayList.add("7");

        Iterator<String> iterator = arrayList.iterator();
        while (iterator.hasNext()) {
            String value = iterator.next();
            if ("5".equals(value)) {
                arrayList.remove(value);
            }
        }
    }
}