package com.example.louiewh.aidlapplication;

import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by louiewh on 16/7/24.
 */
public class HelloWorldProxy {
    static final String TAG = "HelloWorldProxy";
    public AidlBinderServiceProxy  mAidlBinderService;
    private IHelloWorldInterface mRemoteService;
    ArrayList<IAidlListernerInterface> mListener = new ArrayList<>();

    public HelloWorldProxy(AidlBinderServiceProxy proxy) {

        mAidlBinderService = proxy;
        IBinder binder = mAidlBinderService.getService(AidlService.HELLOWORLDSERVICE);

        mRemoteService = IHelloWorldInterface.Stub.asInterface(binder);
    }

    public String printHelloWorld() {
        try {
            return mRemoteService.printHelloWorld();
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void registerListener(IAidlListernerInterface listerner) {

        mListener.add(listerner);
        if(mRemoteService == null) {
            Log.i(TAG, "Service is not ready!");
            return;
        }

        try {
            mRemoteService.registerListerner(listerner);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void unregisterListener(IAidlListernerInterface listener) {
        mListener.remove(listener);

        try {
            mRemoteService.unregisterListerner(listener);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}