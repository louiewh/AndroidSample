package com.dawang.androidexample.mvp.view;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.dawang.androidexample.R;
import com.dawang.androidexample.mvp.presenter.MvpPresenter;

/**
 * Created by DaWang on 2017/4/8.
 */

public class MvpActivity extends Activity implements IMvpView {

    TextView mHttpsButton;
    TextView mHttpsButtonWellKnown;
    MvpPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initViews();
        loadViews();
    }

    void initViews(){
        setContentView(R.layout.https_layout);
        mHttpsButton = (TextView) findViewById(R.id.button_12306);
        mHttpsButtonWellKnown = (TextView) findViewById(R.id.button_baidu);
    }

    void loadViews(){
        mPresenter = new MvpPresenter(this);
        mHttpsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.start12306HttpsConnect();
            }
        });

        mHttpsButtonWellKnown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.startWellKnowCAConnect();
            }
        });
    }

    @Override
    public void set12306View(String result) {
        mHttpsButton.setText(result);
    }

    @Override
    public void setWellKnown(String result) {
        mHttpsButtonWellKnown.setText(result);
    }
}
