package com.dawang.androidexample.http;

import android.util.Log;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by DaWang on 2017/4/12.
 */
public class OkHttpHandlerThreadTest {
    @Test
    public void syncGet() throws Exception {
        OkHttpHandlerThread okThread = new OkHttpHandlerThread("OkThread");
        String result = okThread.syncGet("http://www.baidu.com");
        Log.e("OkHttp", "AndroidJUnit4:" + result);

        assertEquals(1, 1);
    }

    @Test
    public void asyncGet() throws Exception{
        OkHttpHandlerThread okThread = new OkHttpHandlerThread("OkThread");
        okThread.asyncGet("http://www.baidu.com");
        assertEquals(1, 1);
    }

}