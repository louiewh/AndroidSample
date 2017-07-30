package com.dawang.androidexample.view;

import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.dawang.androidexample.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DaWang on 2017/4/30.
 */

abstract public class RecyclerViewFragment extends Fragment {
    RecyclerView mRecyclerView;
    ViewAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_myview_layout, container,false);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(mAdapter = new ViewAdapter());
        mRecyclerView.addItemDecoration(new MyRecycleViewDivider());
        if(getViewList() != null){
            mAdapter.mList = getViewList();
        }

        return view;
    }


    abstract public ArrayList<View> getViewList();

    class ViewAdapter extends RecyclerView.Adapter<ViewViewHolder>{
        ArrayList<View> mList = new ArrayList<>();
        FrameLayout.LayoutParams mFrameLayoutLp;

        public ViewAdapter(){
            mFrameLayoutLp = new FrameLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT);
            mFrameLayoutLp.gravity = Gravity.CENTER;
        }

        @Override
        public ViewViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            ViewGroup view = (ViewGroup) LayoutInflater.from(getContext())
                    .inflate(R.layout.recyclerview_view_item, parent, false);

            addChildView(view, viewType);
            return new ViewViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewViewHolder holder, int position) {

        }

        @Override
        public int getItemViewType(int position) {
            return position;
        }

        @Override
        public int getItemCount() {
            return mList.size();
        }

        private void addChildView(ViewGroup viewGroup, int viewType){
            View view = mList.get(viewType);

            if (view != null) {
                viewGroup.addView(view, mFrameLayoutLp);
            }
        }
    }

    class ViewViewHolder extends RecyclerView.ViewHolder{
        public ViewViewHolder(View itemView) {
            super(itemView);
        }
    }

    public class MyRecycleViewDivider extends RecyclerView.ItemDecoration{
        private final int[] ATTRS = new int[]{android.R.attr.listDivider};
        private Drawable mDivider;
        private int mDividerHeight;

        public MyRecycleViewDivider(){
            final TypedArray a = getContext().obtainStyledAttributes(ATTRS);
            mDivider = a.getDrawable(0);
            a.recycle();

            mDividerHeight = getResources().getDimensionPixelSize(R.dimen.ItemDecoration_size);
        }

        @Override
        public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
            super.onDraw(c, parent, state);
        }

        @Override
        public void onDrawOver(Canvas canvas, RecyclerView parent, RecyclerView.State state) {
            super.onDrawOver(canvas, parent, state);

            final int left = parent.getPaddingLeft();
            final int right = parent.getMeasuredWidth() - parent.getPaddingRight();
            final int childSize = parent.getChildCount();
            for (int i = 0; i < childSize; i++) {
                final View child = parent.getChildAt(i);
                RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) child.getLayoutParams();
                final int top = child.getBottom() + layoutParams.bottomMargin;
                final int bottom = top + mDividerHeight;
                if (mDivider != null) {
                    mDivider.setBounds(left, top, right, bottom);
                    mDivider.draw(canvas);
                }
            }
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            super.getItemOffsets(outRect, view, parent, state);
            outRect.set(0, 0, 0, mDividerHeight);
        }
    }
}
