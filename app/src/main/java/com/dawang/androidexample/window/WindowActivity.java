package com.dawang.androidexample.window;

import android.graphics.PixelFormat;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.WindowManager;
import android.widget.Button;

import com.dawang.androidexample.R;


public class WindowActivity extends AppCompatActivity {

    private Button mButton;
    private WindowManager.LayoutParams mLayoutParam;
    private WindowManager mWindowManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.window_main);

        initWindow();
    }

    public void initWindow(){
        mButton = new Button(this);
        mButton.setText("Hello Window, I am Button");
        mButton.setBackgroundResource(R.color.colorAccent);

        mLayoutParam = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.TYPE_APPLICATION, 0,
                PixelFormat.TRANSLUCENT);

        mLayoutParam.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
                | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                | WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON;
        mLayoutParam.gravity = Gravity.LEFT | Gravity.TOP;
        mLayoutParam.x = 200;
        mLayoutParam.y = 200;


        mWindowManager =  (WindowManager) getSystemService(
                getApplicationContext().WINDOW_SERVICE
        );

        mWindowManager.addView(mButton, mLayoutParam);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        int x = (int) event.getRawX();
        int y = (int) event.getRawY();

        switch (action) {
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_MOVE:
                mLayoutParam.x = x;
                mLayoutParam.y = y;
                mWindowManager.updateViewLayout(mButton, mLayoutParam);
                break;
            case MotionEvent.ACTION_UP:
                break;
                default:
                    return super.onTouchEvent(event);
        }

        return super.onTouchEvent(event);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mWindowManager.removeView(mButton);
    }
}
