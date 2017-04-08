package com.example.louiewh.aidlapplication;

import android.os.IBinder;
import android.os.RemoteException;

/**
 * Created by louiewh on 16/7/31.
 */
public class HelloAidlProxy {
    static final String TAG = "HelloAidlProxy";
    public AidlBinderServiceProxy  mAidlBinderService;
    private IHelloAidlInterface mRemoteService;


    public HelloAidlProxy(AidlBinderServiceProxy proxy) {

        mAidlBinderService = proxy;
        IBinder binder = mAidlBinderService.getService(AidlService.HELLOAIDLSERVICE);

        mRemoteService = IHelloAidlInterface.Stub.asInterface(binder);
    }

    public PidInfo getPidInfo() {
        if(mRemoteService != null){
            try {
                return mRemoteService.getPidInfo();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        return null;
    }
}
