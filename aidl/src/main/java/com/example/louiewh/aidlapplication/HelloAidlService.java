package com.example.louiewh.aidlapplication;

import android.os.RemoteException;

/**
 * Created by louiewh on 16/7/31.
 */
public class HelloAidlService extends IHelloAidlInterface.Stub {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     *
     * @param anInt
     * @param aLong
     * @param aBoolean
     * @param aFloat
     * @param aDouble
     * @param aString
     */
    @Override
    public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

    }

    @Override
    public PidInfo getPidInfo() throws RemoteException {
        return new PidInfo(android.os.Process.myPid());
    }
}
