package com.dawang.androidexample.view;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.dawang.androidexample.R;

/**
 * Created by DaWang on 2017/4/13.
 */

public class ViewActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.view_activity_layout);
    }
}
