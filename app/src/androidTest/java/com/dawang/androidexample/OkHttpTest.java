package com.dawang.androidexample;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import com.dawang.androidexample.http.OkHttpHandlerThread;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

/**
 * Created by DaWang on 2017/4/12.
 */


@RunWith(AndroidJUnit4.class)
public class OkHttpTest {
    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();
        getTest();
        assertEquals("com.dawang.androidsample", appContext.getPackageName());
    }

    @Test
    public void getTest(){
        OkHttpHandlerThread okThread = new OkHttpHandlerThread("OkThread");
        String result = okThread.syncGet("http://www.baidu.com");
        Log.e("OkHttp", "AndroidJUnit4:" + result);

        assertEquals(1, 1);
    }
}