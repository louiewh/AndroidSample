package com.example.louiewh.aidlapplication;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.RemoteException;
import android.util.Log;


/**
 * Created by louiewh on 16/7/26.
 */
public class AidlBinderServiceProxy {
    static final String TAG = "AidlBinderServiceProxy";
    static final int AidlBinderService = 1;
    private Context mContext;
    private ServiceConnection mConnection;
    private IAidlBinderService mRemoteService;
    private Handler mHandler;
    private static AidlBinderServiceProxy instance;

    private AidlBinderServiceProxy(Context context, Handler handler) {
        mContext = context;
        mHandler = handler;
        initServiceConnection();
        Intent intent = new Intent(context, AidlService.class);
        context.bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
    }

    public static AidlBinderServiceProxy instance(Context context, Handler handler){

        synchronized (AidlBinderServiceProxy.class) {
            if(instance == null) {
                instance = new AidlBinderServiceProxy(context, handler);
            }
        }

        return instance;
    }

    public void unbindService() {

        Log.d(TAG, "unbindService");
        mContext.unbindService(mConnection);
        instance = null;
    }

    private void initServiceConnection() {
        mConnection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                mRemoteService = IAidlBinderService.Stub.asInterface(iBinder);
                Message message = Message.obtain(mHandler, AidlBinderService);
                mHandler.sendMessage(message);
                Log.d(TAG, "onServiceConnected");
            }

            @Override
            public void onServiceDisconnected(ComponentName componentName) {

            }
        };
    }

    public IBinder getService(String service) {
        if(mRemoteService == null) {
        }

        try {
            return mRemoteService.getService(service);
        } catch (RemoteException e) {
            Log.e(TAG, "getService " + "service");
            e.printStackTrace();
        }

        return null;
    }
}
