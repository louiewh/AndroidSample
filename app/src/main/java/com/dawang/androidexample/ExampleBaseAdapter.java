package com.dawang.androidexample;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Created by DaWang on 2017/4/3.
 */

public class ExampleBaseAdapter extends BaseAdapter {
    Context mContext;
    String[] mList = {
            "Http",
            "Window",
            "Animation",
            "Aidl",
            "MVP",
            "Design pattern",
            "View",
            "Widget",
            "RxJava"
    };

    ExampleBaseAdapter(Context context){
        mContext = context;
    }

    @Override
    public int getCount() {
        return mList.length;
    }

    @Override
    public Object getItem(int position) {
        return mList[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView view;
        ViewHolder viewHolder;

        if(convertView == null){
            convertView = View.inflate(mContext, android.R.layout.simple_expandable_list_item_1, null);

            viewHolder = new ViewHolder();
            viewHolder.holderView = (TextView) convertView;
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder)convertView.getTag();
        }

        view = viewHolder.holderView;
        view.setText(mList[position]);
        return view;
    }

    class ViewHolder{
        TextView holderView;
    }
}
