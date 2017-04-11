package com.dawang.androidexample.designpattern;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by DaWang on 2017/4/11.
 */

public class DesignPatternActivity extends Activity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Book book = new Book();
        book.setName("Design Pattern");
        book.setAuthor("XXXX");
        book.setPrivce(66);
        book.setISBN("1234567890");

        Log.e("Book", Book.formatXML(book));
        Log.e("Book", Book.formatJson(book));
    }
}
