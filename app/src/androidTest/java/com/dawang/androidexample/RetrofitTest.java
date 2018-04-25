package com.dawang.androidexample;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.dawang.androidexample.rxjava.RetrofitDemo;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

/**
 * Created by DaWang on 2017/4/12.
 */


@RunWith(AndroidJUnit4.class)
public class RetrofitTest {
    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();
        RetrofitDemo retrofitDemo = new RetrofitDemo();

        retrofitDemo.getGithubUserSync("octocat");
        retrofitDemo.getGithubUserAsync("octocat");
        retrofitDemo.getRepoList("octocat");

        assertEquals("com.dawang.androidsample", appContext.getPackageName());
    }
}