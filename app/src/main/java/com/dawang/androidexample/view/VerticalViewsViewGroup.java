package com.dawang.androidexample.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by DaWang on 2017/4/23.
 */

//http://www.cnblogs.com/qifengshi/p/5774852.html

public class VerticalViewsViewGroup extends ViewGroup {
    private int mChildMargin = 20;

    public VerticalViewsViewGroup(Context context) {
        super(context);
    }

    public VerticalViewsViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public VerticalViewsViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        measureChildren(widthMeasureSpec, heightMeasureSpec);

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heigthMode = MeasureSpec.getMode(heightMeasureSpec);
        int heigthSzie = MeasureSpec.getSize(heightMeasureSpec);

        if(widthMode == MeasureSpec.AT_MOST && heigthMode == MeasureSpec.AT_MOST){
            View childView = getChildAt(0);
            setMeasuredDimension(childView.getMeasuredWidth(), childView.getMeasuredHeight()*getChildCount());
        } else if (widthMode == MeasureSpec.AT_MOST){
            View childView = getChildAt(0);
            setMeasuredDimension(childView.getMeasuredWidth(), heigthSzie);
        } else if(heigthMode == MeasureSpec.AT_MOST){
            View childView = getChildAt(0);
            setMeasuredDimension(widthSize, childView.getMeasuredHeight()*getChildCount());
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int height = 0;
        int count = getChildCount();
        for(int i = 0; i < count; i++){
            View childView = getChildAt(i);
            childView.layout(mChildMargin, height, childView.getMeasuredWidth(), childView.getMeasuredHeight() + height);
            height += childView.getMeasuredHeight();
        }
    }
}
