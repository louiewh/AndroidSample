package com.dawang.androidexample.widget.recyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dawang.androidexample.R;


/**
 * Created by louie.wang on 2017/5/10.
 */

public class LoopRecyclerViewAdapter extends RecyclerViewAdapter {
    private Context mContext;
    public final int VIEW_TYPE_COMMON = 0;
    public final int VIEW_TYPE_CHOOSE  = 1;

    public LoopRecyclerViewAdapter(Context context){
        super(context);
        mContext = context;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if(viewType == VIEW_TYPE_CHOOSE) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.recyclerview_item_choose, parent, false);

            return new RecyclerViewHolder(view);
        } else{
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.recyclerview_item, parent, false);

            return new RecyclerViewHolder(view);
        }
    }

    @Override
    public int getItemViewType(int position) {

        if(Math.abs(getInitPosition() - position) % mAppList.size() == (mAppList.size() + 1) /2 ){
            return VIEW_TYPE_CHOOSE;
        } else {
            return VIEW_TYPE_COMMON;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        int pos = position % mAppList.size();
        holder.mImageView.setImageDrawable(mAppList.get(pos).loadIcon(mContext.getPackageManager()));
        holder.mTextView.setText(mAppList.get(pos).loadLabel(mContext.getPackageManager())+" :"+pos);
    }

    @Override
    public int getItemCount() {
        return Integer.MAX_VALUE;
    }

    public int getInitPosition(){
        return Integer.MAX_VALUE / 2 - Integer.MAX_VALUE / 2 % mAppList.size();
    }
}
