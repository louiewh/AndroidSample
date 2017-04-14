package com.dawang.androidexample.http;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.util.Log;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by DaWang on 2017/4/12.
 */

public class OkHttpHandlerThread extends HandlerThread {

    private Handler mHandler;
    private OkHttpClient mOkHttpClient;

    public OkHttpHandlerThread(String name) {
        super(name);
//        initHandler();
        mOkHttpClient = new OkHttpClient();
    }

    void initHandler(){
        mHandler = new Handler(getLooper(), new Handler.Callback(){

            @Override
            public boolean handleMessage(Message msg) {
                return false;
            }
        });
    }

    public String syncGet(String url){
        Request request = new Request.Builder().url(url).build();

        try {
            Response response = mOkHttpClient.newCall(request).execute();
            return response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void asyncGet(String url){
        Request request = new Request.Builder().url(url).build();
        Call call = mOkHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.i("asyncGet", "onFailure" + e.toString());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.e("asyncGet", response.body().string());
            }
        });
    }
}
