package com.dawang.androidexample.http;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.TextView;

import com.dawang.androidexample.R;


/**
 * Created by DaWang on 2017/4/3.
 */

public class HttpsActivity extends Activity{
    TextView mHttpsButton;
    TextView mHttpsButtonBD;

    Handler mHandler;
    static  final int HTTPS_SUCCES_12306 = 1;
    static  final int HTTPS_SUCCES_WellKnown = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
        initViews();
        loadViews();
    }

    void initViews(){
        setContentView(R.layout.https_layout);
        mHttpsButton = (TextView) findViewById(R.id.button_12306);
        mHttpsButtonBD = (TextView) findViewById(R.id.button_baidu);
    }

    void initData(){
        mHandler = new Handler(getMainLooper(), new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                switch (msg.what){
                    case HTTPS_SUCCES_12306:
                        mHttpsButton.setText("12306 Https Success!");
                        break;
                    case HTTPS_SUCCES_WellKnown:
                        mHttpsButtonBD.setText("WellKnown Https Success!");
                        break;
                    default:
                        mHttpsButton.setText("default!");
                        break;
                }

                return false;
            }
        });
    }

    void loadViews(){
        mHttpsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Https12306HandlerThread thread = new Https12306HandlerThread("12306", mHandler);
                thread.start();
                thread.postHttpsConnect();
            }
        });

        mHttpsButtonBD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WellKnownCAHandlerThread thread = new WellKnownCAHandlerThread("WellKnown", mHandler);
                thread.start();
                thread.postHttpsConnect();
            }
        });
    }
}
