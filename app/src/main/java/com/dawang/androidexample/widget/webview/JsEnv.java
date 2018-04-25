package com.dawang.androidexample.widget.webview;

import android.webkit.JavascriptInterface;

/**
 * Created by louie.wang on 2017/12/8.
 */

public class JsEnv {
    private Object mJavaObject;
    private OnJsCallJava mOnJsCallJava;

    @JavascriptInterface
    void call(String method, String args, String jsCallback){
        if(mOnJsCallJava != null){
            mOnJsCallJava.call(method, args, jsCallback);
        }
    }


    interface OnJsCallJava {
        void call(String method, String args, String jsCallback);
    }

    public void setOnJsCallJava(OnJsCallJava onJsCallJava){
        mOnJsCallJava = onJsCallJava;
    }
}
