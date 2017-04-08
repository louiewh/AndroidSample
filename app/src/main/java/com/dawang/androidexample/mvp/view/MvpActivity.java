package com.dawang.androidexample.mvp.view;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.TextView;

import com.dawang.androidexample.R;
import com.dawang.androidexample.http.Https12306HandlerThread;
import com.dawang.androidexample.http.WellKnownCAHandlerThread;

/**
 * Created by DaWang on 2017/4/8.
 */

public class MvpActivity extends Activity {

    TextView mHttpsButton;
    TextView mHttpsButtonBD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initViews();
        loadViews();
    }

    void initViews(){
        setContentView(R.layout.https_layout);
        mHttpsButton = (TextView) findViewById(R.id.button_12306);
        mHttpsButtonBD = (TextView) findViewById(R.id.button_baidu);
    }

    void loadViews(){
        mHttpsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
;
            }
        });

        mHttpsButtonBD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
    }
}
