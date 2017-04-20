package com.example.louiewang.bluetoothapplication.mode;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.util.Log;

import java.util.ArrayList;
import java.util.Set;

/**
 * Created by DaWang on 2017/4/19.
 */

public class BluetoothModel {
    private BluetoothAdapter mBluetoothAdapter;
    public ArrayList<BluetoothDevice> mList = new ArrayList<>();

    public BluetoothModel(){
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
    }

    public BluetoothAdapter getBluetoothAdapter(){

        return mBluetoothAdapter;
    }

    public void startDiscovery(){
        mBluetoothAdapter.startDiscovery();
    }

    public void getPairedDevice(){
        Set<BluetoothDevice> pairedDevices = mBluetoothAdapter.getBondedDevices();
        if (pairedDevices.size() > 0) {
            mList.clear();
            for (BluetoothDevice device : pairedDevices) {
                mList.add(device);
                Log.e("Bt", "PairedDevice:"+device.getName() + " || " + device.getAddress());
            }
        }
    }

    public void removePairedDevice(BluetoothDevice device){
        if(device.getBondState() == BluetoothDevice.BOND_BONDED) {
            BluetoothTools.removeBond(device);
        }
    }
}
