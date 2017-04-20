package com.example.louiewang.bluetoothapplication.presenter;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;

import com.example.louiewang.bluetoothapplication.mode.BluetoothModel;
import com.example.louiewang.bluetoothapplication.mode.BluetoothTools;
import com.example.louiewang.bluetoothapplication.view.IBtView;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Set;

/**
 * Created by louie.wang on 2017/4/17.
 */

public class BtPresenter {

    public  static final int REQUEST_ENABLE_BT = 10;
    private IBtView mBtView;
    private Context mContext;
    private BtAdapter mAdapter;
    private BluetoothModel mBtModel;

    public BtPresenter(Context context, IBtView view){
        mContext = context;
        mBtView = view;
        mBtModel = new BluetoothModel();
    }

    public void startDiscovery(){
        if (!mBtModel.getBluetoothAdapter().isEnabled()) {
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            mBtView.startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
        }

        registerReceiver();
        mBtModel.startDiscovery();
    }

    public void ensureDiscoverable(){
//        Intent discoverableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
//        discoverableIntent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 300);
//        mContext.startActivity(discoverableIntent);
    }

    public void getPairedDevice(){
         mBtModel.getPairedDevice();
         mAdapter.notifyDataSetChanged();
    }

    public void removePairedDevice(int position){
        BluetoothDevice device = (BluetoothDevice) mAdapter.getItem(position);
        mBtModel.removePairedDevice(device);
        mAdapter.mList.remove(device);
        mAdapter.notifyDataSetChanged();
    }

    private final BroadcastReceiver mReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {

            Log.e("Bt", "BluetoothDevice:" + intent.toString());
            String action = intent.getAction();
            if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                if(mAdapter != null){
                    mAdapter.mList.add(device);
                    mAdapter.notifyDataSetChanged();
                }
            } else if(BluetoothAdapter.ACTION_DISCOVERY_FINISHED.equals(action)){
                Log.e("Bt", "BluetoothDevice:" + action.toString());
            } else if(BluetoothDevice.ACTION_PAIRING_REQUEST.equals(action)) {
                Log.e("Bt", "BluetoothDevice:" + action.toString());
                int type = intent.getIntExtra(BluetoothDevice.EXTRA_PAIRING_VARIANT, BluetoothDevice.ERROR);
                Log.e("Bt", "BluetoothDevice type:" + type);
                int pairingKey = intent.getIntExtra(BluetoothDevice.EXTRA_PAIRING_KEY, BluetoothDevice.ERROR);
                Log.e("Bt", "BluetoothDevice pairingKey:" + pairingKey);
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                abortBroadcast();
                BluetoothTools.setPairingConfirmation(device);
//                BluetoothTools.setPin(device, Integer.toString(pairingKey));
            }
        }
    };

    public void setAdapter() {
        mAdapter = new BtAdapter(mContext, mBtModel.mList);
        mBtView.getListView().setAdapter(mAdapter);
    }

    public void registerReceiver(){
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(BluetoothDevice.ACTION_FOUND);
        intentFilter.addAction(BluetoothDevice.ACTION_PAIRING_REQUEST);
        intentFilter.addAction(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
        intentFilter.setPriority(1000);
        mContext.registerReceiver(mReceiver, intentFilter);
    }

    public void unregisterReceiver(){
        mContext.unregisterReceiver(mReceiver);
    }

    public void onDestroy(){
        unregisterReceiver();
    }

}
