package com.dawang.androidexample.widget.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.dawang.androidexample.R;

/**
 * Created by louie.wang on 2017/5/10.
 */

public class RecyclerViewHolder extends RecyclerView.ViewHolder {
    public ImageView mImageView;
    public TextView mTextView;

    public RecyclerViewHolder(View itemView) {
        super(itemView);
        mImageView = (ImageView) itemView.findViewById(R.id.iv_icon);
        mTextView = (TextView) itemView.findViewById(R.id.tv_name);
    }
}
