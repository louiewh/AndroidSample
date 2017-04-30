package com.dawang.androidexample.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.dawang.androidexample.R;

import java.util.ArrayList;

/**
 * Created by DaWang on 2017/4/25.
 */

public class FlowLayout extends ViewGroup {
    private ArrayList<Rect> mList = new ArrayList<>();
    private final int mDefaultDividerW = 10;
    private final int mDefaultDividerH = 10;
    private int mDividerW;
    private int mDividerH;

    public FlowLayout(Context context) {
        super(context);
    }

    public FlowLayout(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray array  = context.obtainStyledAttributes(attrs, R.styleable.FlowLayout);
        mDividerW = array.getInteger(R.styleable.FlowLayout_dividerW, mDefaultDividerW);
        mDividerH = array.getInteger(R.styleable.FlowLayout_dividerH, mDefaultDividerH);
    }

    public FlowLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public FlowLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        measureChildren(widthMeasureSpec, heightMeasureSpec);

        int widthMode  = MeasureSpec.getMode(widthMeasureSpec);
        int heigthMode = MeasureSpec.getMode(heightMeasureSpec);
        int widthSize  = MeasureSpec.getSize(widthMeasureSpec);
        int heigthSize = MeasureSpec.getSize(heightMeasureSpec);

        int count = getChildCount();
        int heigth = 0;
        int width = 0;
        int lineW = 0;
        int lineH = 0;

        for(int i = 0; i < count; i++){
            View childView = getChildAt(i);
            Rect rect = new Rect();
            mList.add(rect);
            LayoutParams lp = childView.getLayoutParams();
            Log.e("Flowlayout", lp.toString());

            int childW = childView.getMeasuredWidth();
            int childH = childView.getMeasuredHeight();

            if(childW + lineW > widthSize){
                lineW = childW + mDividerW;
                lineH  = childH;

                heigth +=lineH + mDividerH;
                width = Math.max(lineW, childW);
            } else {
                lineW += childW + mDividerW;
                lineH  = childH;
                width = Math.max(lineW, width);
                if(i == 0){
                    heigth += lineH + mDividerH;
                }
            }

            rect.left   = lineW - childW - mDividerW;
            rect.top    = heigth - childH;
            rect.right  = lineW - mDividerW;
            rect.bottom = heigth - mDividerH;
        }

        int measureWidth = widthMode == MeasureSpec.AT_MOST ? width:widthSize;
        int measureHeigth = heigthMode == MeasureSpec.AT_MOST? heigth:heigthSize;
        setMeasuredDimension(measureWidth, measureHeigth);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int count = getChildCount();
        for(int i = 0; i < count; i++){
            View childView = getChildAt(i);
            Rect rect = mList.get(i);
            childView.layout(rect.left, rect.top, rect.right, rect.bottom);
        }
    }
}
