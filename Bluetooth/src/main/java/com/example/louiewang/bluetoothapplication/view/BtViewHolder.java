package com.example.louiewang.bluetoothapplication.view;

import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.example.louiewang.bluetoothapplication.R;
import com.example.louiewang.bluetoothapplication.mode.BluetoothTools;

/**
 * Created by DaWang on 2017/4/19.
 */

public class BtViewHolder {
    View mConvertView;
    TextView mNameText;
    TextView mMacText;

    CheckBox mPairBox;
    CheckBox mConnectBox;

    public static BtViewHolder getBtViewHolder(Context context, View convertView){
        BtViewHolder holder;
        if(convertView == null){
            convertView = View.inflate(context, R.layout.list_item, null);

            holder = new BtViewHolder();
            holder.mNameText = (TextView) convertView.findViewById(android.R.id.text1);
            holder.mMacText  = (TextView) convertView.findViewById(android.R.id.text2);

            holder.mPairBox = (CheckBox) convertView.findViewById(R.id.pair_box);
            holder.mConnectBox = (CheckBox) convertView.findViewById(R.id.connect_box);
            convertView.setTag(holder);
            holder.mConvertView = convertView;
        } else {
            holder = (BtViewHolder) convertView.getTag();
        }

        return holder;
    }

    public View getConvertView(){
        return mConvertView;
    }

    public void setData(final BluetoothDevice device){
        mNameText.setText(device.getName());
        mMacText.setText(device.getAddress());

        if(device.getBondState() == BluetoothDevice.BOND_BONDED){
            mPairBox.setChecked(true);
        } else {
            mPairBox.setChecked(false);
        }

        mPairBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mPairBox.setChecked(isChecked);
                Log.e("Bt", "pair");
                BluetoothTools.pair(device);
            }
        });
    }
}
