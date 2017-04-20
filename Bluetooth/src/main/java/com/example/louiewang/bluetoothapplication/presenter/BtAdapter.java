package com.example.louiewang.bluetoothapplication.presenter;

import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.louiewang.bluetoothapplication.view.BtViewHolder;

import java.util.ArrayList;

/**
 * Created by louie.wang on 2017/4/17.
 */

public class BtAdapter extends BaseAdapter {

    public ArrayList<BluetoothDevice> mList;
    private Context mContext;

    public BtAdapter(Context context, ArrayList<BluetoothDevice> list){
        mContext = context;
        mList = list;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        BtViewHolder holder= BtViewHolder.getBtViewHolder(mContext, convertView);
        holder.setData(mList.get(position));

        return holder.getConvertView();
    }
}
