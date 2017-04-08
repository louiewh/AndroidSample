package com.dawang.androidexample.mvp.presenter;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import com.dawang.androidexample.http.WellKnownCAHandlerThread;
import com.dawang.androidexample.mvp.module.Https12306HandlerThread;
import com.dawang.androidexample.mvp.view.IMvpView;

/**
 * Created by DaWang on 2017/4/8.
 */

public class MvpPresenter {

    private Handler mHandler;
    private IMvpView mView;

    public static  final int HTTPS_SUCCES_12306 = 1;
    public static  final int HTTPS_SUCCES_WellKnown = 2;

    public MvpPresenter(IMvpView view){
        mView = view;
        initHandler();
    }

    public void start12306HttpsConnect(){
        Https12306HandlerThread thread = new Https12306HandlerThread("12306", mHandler);
        thread.start();
        thread.postHttpsConnect();
    }


    public void startWellKnowCAConnect(){
        WellKnownCAHandlerThread thread = new WellKnownCAHandlerThread("WellKnown", mHandler);
        thread.start();
        thread.postHttpsConnect();
    }

    void initHandler(){
        mHandler = new Handler(Looper.getMainLooper(), new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                switch (msg.what){
                    case HTTPS_SUCCES_12306:
                        mView.set12306View("12306 Https Success!");
                        break;
                    case HTTPS_SUCCES_WellKnown:
                        mView.setWellKnown("WellKnown Https Success!");
                        break;
                    default:
                        break;
                }

                return false;
            }
        });
    }
}
