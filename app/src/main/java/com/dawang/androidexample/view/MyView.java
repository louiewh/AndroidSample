package com.dawang.androidexample.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.dawang.androidexample.R;

/**
 * Created by DaWang on 2017/4/13.
 */

public class MyView extends View {
    private Paint mPaint = new Paint();
    private int mDyCircle = 0;
    private boolean mDyDraw = false;

    public MyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.MyView);
        mDyDraw = array.getBoolean(R.styleable.MyView_dy_draw, false);
    }

    public MyView(Context context) {
        super(context);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if(mDyDraw) {
            drawDynamicCircle(canvas);
        } else {
            drawText(canvas);
            drawLine(canvas);
            drawRoundRect(canvas);
            drawCircleStroke(canvas);
            drawCircleFill(canvas);
            drawPath(canvas);
            drawOval(canvas);
            drawArc(canvas);
        }
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
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(5);
        mPaint.setShadowLayer(5, 15, 15, Color.LTGRAY);

//        canvas.drawRGB(255, 255,255);
        canvas.drawCircle(150, 200, 100, mPaint);
    }

    void drawCircleFill(Canvas canvas){
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeWidth(5);

        canvas.drawCircle(380, 200, 100, mPaint);
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

    void  drawOval(Canvas canvas){
        Rect rect = new Rect(100, 500, 400, 600);

        canvas.drawRect(rect, mPaint);
        mPaint.setColor(Color.BLUE);
        canvas.drawOval(new RectF(rect), mPaint);

    }
    void drawArc(Canvas canvas){
//        Rect rect = new Rect(400, 500, 600, 600);
        Rect rect = new Rect(100, 500, 400, 600);
        mPaint.setColor(Color.RED);

        canvas.drawArc(new RectF(rect), 0, 90, false, mPaint);
    }

    void drawDynamicCircle(Canvas canvas){
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(10);
        int width = getWidth();
        int height = getHeight();
        int min = width < height ? width:height;
        Rect rect = new Rect(width/2 - min/2, (height-min)/2, width/2+min/2, (height+min)/2);

        if(mDyCircle % 4 == 0) {
            mPaint.setColor(Color.RED);
            canvas.drawArc(new RectF(rect), 0, 90, false, mPaint);
        } else if(mDyCircle % 4 == 1) {

            mPaint.setColor(Color.BLUE);
            canvas.drawArc(new RectF(rect), 90, 90, false, mPaint);
        } else if(mDyCircle % 4 == 2) {
            mPaint.setColor(Color.DKGRAY);
            canvas.drawArc(new RectF(rect), 180, 90, false, mPaint);
        } else if(mDyCircle % 4 == 3) {
            mPaint.setColor(Color.YELLOW);
            canvas.drawArc(new RectF(rect), 270, 90, false, mPaint);
        }

        mDyCircle ++;
        postInvalidateDelayed(1000);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();

        mDyDraw = false;
    }
}
