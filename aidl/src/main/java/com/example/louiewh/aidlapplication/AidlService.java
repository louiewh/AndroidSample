package com.example.louiewh.aidlapplication;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by louiewh on 16/7/21.
 */
public class AidlService extends Service {

    public final static String HELLOWORLDSERVICE = "HelloWorldService";
    public final static String HELLOAIDLSERVICE = "HelloAidlService";
    public final static String TAG = "AidlService";

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy");
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new AidlBinderService();
    }

    class AidlBinderService extends IAidlBinderService.Stub{

        @Override
        public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

        }

        @Override
        public IBinder getService(String service) throws RemoteException {
            switch(service) {
                case HELLOWORLDSERVICE:
                    return new HelloWorldService();
                case HELLOAIDLSERVICE:
                    return new HelloAidlService();
                default:
                    return null;
            }
        }
    }
}
