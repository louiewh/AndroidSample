package com.dawang.androidexample.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import com.dawang.androidexample.R;
import java.util.Calendar;
import java.util.TimeZone;

/**
 * Created by DaWang on 2017/5/7.
 */

public class ClockView extends View {
    final int DEGREE_WIDTH = 3;
    final int DEGREE_LENGTH = 30;
    final int POINTER_SECOND_WIDTH = 15;
    final int POINTER_MINUTE_WIDTH = 25;
    final int POINTER_HOUR_WIDTH = 35;

    private int mRadius;
    private int mOriginRadius;
    private Point mPoint;
    private Paint mPaint;
    private Paint mTextPaint;
    private int mFontH;
    private int mPadding = 5;
    private int mTextSize = 40;

    public ClockView(Context context) {
        this(context, null);
    }

    public ClockView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ClockView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public ClockView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ClockView, defStyleAttr, defStyleRes);
        mTextSize = typedArray.getDimensionPixelSize(R.styleable.ClockView_hour_text_size, mTextSize);
        typedArray.recycle();
        init();
    }

    public void init(){
        mPoint = new Point();
        mTextPaint = new Paint();

        mTextPaint.setColor(Color.BLACK);
        mTextPaint.setTextSize(mTextSize);
        mTextPaint.setAntiAlias(true);
        Paint.FontMetrics fm = mTextPaint.getFontMetrics();
        mFontH = (int) (fm.descent - fm.ascent);

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        mRadius = Math.min(w, h) / 2 - mPadding;
        mOriginRadius = mRadius / 10;
        mPoint.set(w/2, h/2);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawClockDial(canvas);
        drawPointers(canvas);

        postInvalidateDelayed(1000);
    }

    void drawClockDial(Canvas canvas){
        mPaint.setColor(Color.BLACK);
        mPaint.setStrokeWidth(3);
        mPaint.setStyle(Paint.Style.STROKE);
        canvas.drawCircle(mPoint.x, mPoint.y, mRadius, mPaint);

        mPaint.setStrokeWidth(3);
        mPaint.setStyle(Paint.Style.FILL);
        canvas.drawCircle( mPoint.x, mPoint.y, mOriginRadius, mPaint);

        int degreeLen;
        for (int i = 0; i < 60; i++){
            mPaint.setStrokeWidth(DEGREE_WIDTH);
            degreeLen = DEGREE_LENGTH;

            canvas.save();
            canvas.rotate(360/60* i, mPoint.x, mPoint.y);
            if(i % 5 == 0){
                mPaint.setStrokeWidth(DEGREE_WIDTH*2);
                degreeLen += 10;
                int hour;
                if(i == 0){
                    hour = 12;
                }else{
                    hour = i / 5;
                }

                int textW = (int) mTextPaint.measureText(Integer.toString(hour));
                canvas.drawText(Integer.toString(hour),
                        mPoint.x - textW / 2,
                        mPoint.y - mRadius + degreeLen + mFontH + 10,
                        mTextPaint);
            }
            canvas.drawLine(mPoint.x, mPoint.y - mRadius + degreeLen,
                    mPoint.x, mPoint.y - mRadius, mPaint);
            canvas.restore();
        }
    }

    void drawPointers(Canvas canvas){

        Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
        int hour = calendar.get(Calendar.HOUR);
        int minute = calendar.get(Calendar.MINUTE);
        int second = calendar.get(Calendar.SECOND);

        canvas.save();
        canvas.rotate(360/60*second, mPoint.x, mPoint.y);
        mPaint.setColor(Color.BLACK);
        RectF rectF = new RectF(mPoint.x - POINTER_SECOND_WIDTH / 2 ,
                mPoint.y - mRadius* 10 /12,
                mPoint.x + POINTER_SECOND_WIDTH / 2,
                mPoint.y + mRadius*3/12);
        Path path = new Path();
        path.addRect(rectF, Path.Direction.CW);
        path.quadTo(mPoint.x,
                mPoint.y - mRadius* 11 /12,
                mPoint.x + POINTER_SECOND_WIDTH / 2,
                mPoint.y - mRadius* 10 /12);
        canvas.drawPath(path, mPaint);
        canvas.restore();

        canvas.save();
        canvas.rotate(360/60*minute, mPoint.x, mPoint.y);
        mPaint.setColor(Color.BLUE);
        RectF rectFMin = new RectF(mPoint.x - POINTER_MINUTE_WIDTH / 2 ,
                mPoint.y - mRadius* 9 /12,
                mPoint.x + POINTER_MINUTE_WIDTH / 2,
                mPoint.y + mRadius*3/12);
        Path pathMin = new Path();
        pathMin.addRect(rectFMin, Path.Direction.CW);
        pathMin.quadTo(mPoint.x,
                mPoint.y - mRadius* 10 /12,
                mPoint.x + POINTER_MINUTE_WIDTH / 2,
                mPoint.y - mRadius* 9 /12);
        canvas.drawPath(pathMin, mPaint);
        canvas.restore();

        canvas.save();
        canvas.rotate(360/12*hour, mPoint.x, mPoint.y);
        mPaint.setColor(Color.RED);
        RectF rectFHour = new RectF(mPoint.x - POINTER_HOUR_WIDTH / 2 ,
                mPoint.y - mRadius* 8 /12,
                mPoint.x + POINTER_HOUR_WIDTH / 2,
                mPoint.y + mRadius*3/12);
        Path pathHour = new Path();
        pathHour.addRect(rectFHour, Path.Direction.CW);
        pathHour.quadTo(mPoint.x,
                mPoint.y - mRadius* 9 /12,
                mPoint.x + POINTER_HOUR_WIDTH / 2,
                mPoint.y - mRadius* 8 /12);
        canvas.drawPath(pathHour, mPaint);

        canvas.restore();
    }
}
