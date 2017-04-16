package com.dawang.androidexample.view;

import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;

import com.dawang.androidexample.R;

import java.util.Random;

/**
 * Created by DaWang on 2017/4/16.
 */

public class RainDrop {
    // y=kx + b
    private Paint mPaint = new Paint();
    private int mStartX = 30;
    private int mStartY = 0;

    private int  mK = 3;
    private int mRainSizeW = 8;
    private int mSpeed = 3;
    private int mDirectX = 1;

    private int mViewW;
    private int mViewH;
    private Random mRandom;
    
    private int mRandRainSizeW;
    private int mRandRainSpeed;
    private int mRainColor;
    private int mRainSize;

    public RainDrop(TypedArray array){
        mRandRainSizeW = array.getInteger(R.styleable.RainView_drop_length, 5);
        mRandRainSpeed = array.getInteger(R.styleable.RainView_drop_speed, 5);
        mRainColor = array.getColor(R.styleable.RainView_drop_color, Color.WHITE);
        mRainSize  = array.getInteger(R.styleable.RainView_drop_length, 3);

        mPaint.setColor(mRainColor);
        mPaint.setStrokeWidth(mRainSize);
    }

    void init(int w, int h){
        mViewW = w;
        mViewH = h;
    }

    void initPos(){
        mRandom = new Random();
        mStartX = mRandom.nextInt(mViewW);
        mStartY = mRandom.nextInt(mViewH);

        mRainSizeW = 5 + mRandom.nextInt(mRandRainSizeW);
        mK  = 3 + mRandom.nextInt(5);
        mSpeed = 1+ mRandom.nextInt(mRandRainSpeed);
        if((mSpeed + mK) %2 == 0){
            mDirectX = 1;
        } else {
            mDirectX = 1;
        }
    }

    void rain(Canvas canvas){
        canvas.drawLine(mStartX, mStartY, mStartX+mRainSizeW, mStartY+mRainSizeW*mK, mPaint);
        mStartX +=mSpeed*mDirectX;
        mStartY +=mSpeed*mK;

        if(mStartX > mViewW || mStartY > mViewH){
            initPos();
        }
    }
}
