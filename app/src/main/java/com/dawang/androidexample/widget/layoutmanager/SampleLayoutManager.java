package com.dawang.androidexample.widget.layoutmanager;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.SparseArray;
import android.util.SparseBooleanArray;
import android.view.View;

/**
 * Created by louie.wang on 2017/10/16.
 * http://www.jianshu.com/p/7bb7556bbe10
 * http://blog.csdn.net/huachao1001/article/details/51594004#rd
 * http://jcodecraeer.com/a/anzhuokaifa/androidkaifa/2015/0517/2880.html
 * http://wiresareobsolete.com/2014/09/building-a-recyclerview-layoutmanager-part-1/
 */

public class SampleLayoutManager extends RecyclerView.LayoutManager {

    private int mTotalHeight;
    private int mVerticalScrollOffset;
    private boolean mLoop = false;
    private SparseArray<Rect> mSparseArray = new SparseArray<>();
    private SparseBooleanArray mAttachedItems = new SparseBooleanArray();

    @Override
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        if (getItemCount() <= 0 || state.isPreLayout()) {
            return;
        }

        detachAndScrapAttachedViews(recycler);
        Log.e("onLayoutChildren",  "getChildCount 1:"+getChildCount());

        layoutChildren(recycler);
        Log.e("onLayoutChildren",  "getChildCount 2:"+getChildCount());

        detachAndScrapAttachedViews(recycler);
        Log.e("onLayoutChildren",  "getChildCount 3:"+getChildCount());

        fill(recycler, state);
        Log.e("onLayoutChildren",  "getChildCount 4:"+getChildCount());
        Log.e("onLayoutChildren",  "getItemCount 4:"+getItemCount());
    }

    void layoutChildren(RecyclerView.Recycler recycler){
        mTotalHeight = 0;
        for (int i = 0; i < getItemCount(); i++){
            View view = recycler.getViewForPosition(i);
            measureChildWithMargins(view, 0, 0);
            addView(view);

            Log.e("layoutChildren", getDecoratedTop(view)+" "+view.toString());
            Log.e("layoutChildren", "getDecoratedTop:"+(getHeight() - getPaddingBottom()));

            int width = getDecoratedMeasuredWidth(view);
            int height = getDecoratedMeasuredHeight(view);

            Rect rect = mSparseArray.get(i);
            if(rect == null){
                rect = new Rect();
                rect.set(0, mTotalHeight, width, mTotalHeight+height);
            }

            Log.e("layoutChildren", "i:"+i+" Rect:"+rect.toString());
            mSparseArray.put(i, rect);
            mAttachedItems.put(i, false);
            mTotalHeight += height;
        }
    }

    void fill(RecyclerView.Recycler recycler,  RecyclerView.State state){
        if (getItemCount() <= 0 || state.isPreLayout()) {
            Log.e("removeAndRecycleView", "getItemCount:"+getItemCount());

            return;
        }

        final View topView = getChildAt(0);
        final View bottomView = getChildAt(getChildCount()-1);

        Rect displayFrame = new Rect(0, mVerticalScrollOffset, getWidth(), mVerticalScrollOffset+getHeight());
        Log.e("fill", "displayFrame:"+displayFrame.toString());
        Log.e("fill", "getChildCount:"+getChildCount());

        Rect childFrame = new Rect();
        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            childFrame = mSparseArray.get(i);
            Log.e("removeAndRecycleView", "childFrame:"+childFrame.toString());
            if (!Rect.intersects(displayFrame, childFrame)) {
                removeAndRecycleView(child, recycler);
                Log.e("removeAndRecycleView", "removeAndRecycleView:"+i);
            }
        }

        for (int i = 0; i < getItemCount(); i++){
            if(Rect.intersects(displayFrame, mSparseArray.get(i))){
                View scrap = recycler.getViewForPosition(i);
                measureChildWithMargins(scrap, 0, 0);
                addView(scrap);

                Rect frame = mSparseArray.get(i);
                //将这个item布局出来
                layoutDecorated(scrap,
                        frame.left,
                        frame.top - mVerticalScrollOffset,
                        frame.right,
                        frame.bottom - mVerticalScrollOffset);

                Log.e("layoutDecorated", "layoutDecorated:"+i);
            }
        }
    }

    @Override
    public int scrollHorizontallyBy(int dx, RecyclerView.Recycler recycler, RecyclerView.State state) {
        return super.scrollHorizontallyBy(dx, recycler, state);
    }

    @Override
    public int scrollVerticallyBy(int dy, RecyclerView.Recycler recycler, RecyclerView.State state) {
        if (getChildCount() == 0) {
            return 0;
        }
        int travel = dy;

        final View topView = getChildAt(0);
        final View bottomView = getChildAt(getChildCount()-1);
        Log.i("scrollVerticallyBy", "bottomView"+bottomView);

        Log.i("scrollVerticallyBy", "********************\n");
        Log.e("scrollVerticallyBy", "getChildCount:"+getChildCount());
        Log.e("scrollVerticallyBy", "getItemCount:"+getItemCount());

        final int bottomViewBottom = getDecoratedBottom(bottomView);
        final int topViewTop = getDecoratedTop(topView);

        Log.e("scrollVerticallyBy", "dy:"+dy);
        Log.e("scrollVerticallyBy", "bottomViewBottom:"+bottomViewBottom);
        Log.e("scrollVerticallyBy", "topViewTop:"+topViewTop);

        Log.e("scrollVerticallyBy", "getWidth:"+getWidth());
        Log.e("scrollVerticallyBy", "getHeight:"+getHeight());


        if (mVerticalScrollOffset + dy < 0) {
            travel = -mVerticalScrollOffset;
        } else if (mVerticalScrollOffset + dy > mTotalHeight - getHeight()) {//如果滑动到最底部
            travel = mTotalHeight - getHeight() - mVerticalScrollOffset;
        }

        offsetChildrenVertical(-travel);
        mVerticalScrollOffset += travel;

        Log.i("scrollVerticallyBy", "mVerticalScrollOffset:"+mVerticalScrollOffset);
        detachAndScrapAttachedViews(recycler);
        fill(recycler, state);
        return travel;
    }

    @Override
    public boolean canScrollHorizontally() {
        return super.canScrollHorizontally();
    }

    @Override
    public boolean canScrollVertically() {
        return true;
    }

    @Override
    public RecyclerView.LayoutParams generateDefaultLayoutParams() {
        return new RecyclerView.LayoutParams(RecyclerView.LayoutParams.WRAP_CONTENT,
                RecyclerView.LayoutParams.WRAP_CONTENT);
    }
}
