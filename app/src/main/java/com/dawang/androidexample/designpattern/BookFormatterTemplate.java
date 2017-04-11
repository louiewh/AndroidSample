package com.dawang.androidexample.designpattern;

import android.util.Log;

/**
 * Created by DaWang on 2017/4/11.
 */

abstract public class BookFormatterTemplate {
    final static String TAG = "BookFormatterTemplate";

    static void beforFormat(){
        Log.e(TAG, "BookFormatterTemplate beforFormat");
    }

    protected abstract String formatting(Book book);

    static void afterFormat(){
        Log.e(TAG, "BookFormatterTemplate afterFormat");
    }

    String formatter(Book book){
        beforFormat();
        String result = formatting(book);
        Log.e(TAG, "BookFormatterTemplate formatting");

        afterFormat();

        return result;
    }
}
