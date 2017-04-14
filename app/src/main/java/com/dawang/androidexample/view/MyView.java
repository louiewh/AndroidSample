package com.dawang.androidexample.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by DaWang on 2017/4/13.
 */

public class MyView extends View {
    private Paint mPaint = new Paint();

    public MyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyView(Context context) {
        super(context);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        drawText(canvas);
        drawLine(canvas);
        drawRoundRect(canvas);
        drawCircleStroke(canvas);
        drawCircleFill(canvas);
        drawPath(canvas);
    }

    void drawText(Canvas canvas){
        mPaint.setTextSize(30);
        mPaint.setShadowLayer(10, 55, 22, Color.GREEN);
        canvas.drawText("View Hello World", 0, 30, mPaint);
    }

    void drawLine(Canvas canvas){
        mPaint.setColor(0xffffff00);
        mPaint.setStrokeWidth(10);
        canvas.drawLine(0, 60, 200, 60, mPaint);
    }

    void drawRoundRect(Canvas canvas){
        mPaint.setARGB(255, 255, 0, 255);
        Rect rect = new Rect(10, 60, 100, 100);
        mPaint.setShadowLayer(30, 5, 2, Color.DKGRAY);
        canvas.drawRoundRect(new RectF(rect), 10, 10, mPaint);
    }

    void drawCircleStroke(Canvas canvas){
        mPaint.setAntiAlias(true);                 //抗锯齿功能
        mPaint.setColor(Color.RED);                //设置画笔颜色
        mPaint.setStyle(Paint.Style.STROKE);       //设置填充样式
        mPaint.setStrokeWidth(5);                  //设置画笔宽度
        mPaint.setShadowLayer(5, 15, 15, Color.LTGRAY);     //设置阴影

//        canvas.drawRGB(255, 255,255);              //设置画布背景颜色
        canvas.drawCircle(150, 200, 100, mPaint);   //画圆
    }

    void drawCircleFill(Canvas canvas){
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeWidth(5);

        canvas.drawCircle(380, 200, 100, mPaint);   //画圆
    }

    void drawPath(Canvas canvas){
        mPaint.reset();
        mPaint.setColor(0xff00ff00);
        mPaint.setStrokeWidth(10);
        mPaint.setStyle(Paint.Style.STROKE);

        Path path = new Path();
        path.moveTo(0, 500);
        path.lineTo(450, 200);
        path.quadTo(0, 500, 40, 570);

        canvas.drawPath(path, mPaint);
    }
}
