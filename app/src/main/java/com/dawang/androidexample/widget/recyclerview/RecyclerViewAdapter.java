package com.dawang.androidexample.widget.recyclerview;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dawang.androidexample.R;

import java.util.List;

/**
 * Created by louie.wang on 2017/5/10.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewHolder> {
    private Context mContext;
    protected List<ApplicationInfo> mAppList;

    public RecyclerViewAdapter(Context context){
        mContext = context;
        mAppList = mContext.getPackageManager().getInstalledApplications(0);
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_item, parent, false);

        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        holder.mImageView.setImageDrawable(mAppList.get(position).loadIcon(mContext.getPackageManager()));
        holder.mTextView.setText(mAppList.get(position).loadLabel(mContext.getPackageManager())+" :"+position);
    }

    @Override
    public int getItemCount() {
        return mAppList.size();
    }
}
