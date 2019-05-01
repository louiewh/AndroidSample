package com.dawang.androidexample.widget.fragment;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;

import com.dawang.androidexample.R;

public class ActivityTransparent extends AppCompatActivity {
    final String TAG = "ActivityTransparent";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transparent);


        WindowManager m = getWindowManager();
        Display d = m.getDefaultDisplay();  //为获取屏幕宽、高
        WindowManager.LayoutParams p = getWindow().getAttributes();  //获取对话框当前的参数值
        p.height = (int) (d.getHeight() * 0.6);   //高度设置为屏幕的0.6
        p.width = (int) (d.getWidth() * 0.7);    //宽度设置为屏幕的0.7
        p.alpha = 1.0f;      //设置本身透明度
        p.dimAmount = 0.5f;      //设置窗口外黑暗度
        getWindow().setAttributes(p);

        Log.w(TAG, "onCreate");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.w(TAG, "onStart");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.w(TAG, "onRestart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.w(TAG, "onResume");

    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.w(TAG, "onPause");

    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.w(TAG, "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.w(TAG, "onDestroy");
    }
}
