package com.dawang.androidexample.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by DaWang on 2017/5/28.
 */

public abstract class PaintCanvasView extends View {
    protected Paint mPaint = new Paint();
    protected int mWidth;
    protected int mHeigth;
    protected RectF mRectF;

    public PaintCanvasView(Context context) {
        super(context);
        mRectF = new RectF();
    }

    public PaintCanvasView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public PaintCanvasView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public PaintCanvasView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        onChildDraw(canvas);
    }

    protected abstract void onChildDraw(Canvas canvas);

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeigth = h;

        mRectF.left = 0;
        mRectF.top = 0;
        mRectF.right = w;
        mRectF.bottom = h;
    }
}