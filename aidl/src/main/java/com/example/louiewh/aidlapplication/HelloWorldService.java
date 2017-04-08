package com.example.louiewh.aidlapplication;

import android.os.RemoteCallbackList;
import android.os.RemoteException;
import android.os.SystemClock;

import java.util.ArrayList;

/**
 * Created by louiewh on 16/7/21.
 */
public class HelloWorldService  extends IHelloWorldInterface.Stub {
//    ArrayList<IAidlListernerInterface> mListerner = new ArrayList<IAidlListernerInterface>();
    RemoteCallbackList<IAidlListernerInterface> mListerner = new RemoteCallbackList<IAidlListernerInterface>();
    @Override
    public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

    }

    @Override
    public String printHelloWorld() throws android.os.RemoteException {
        if(mListerner != null) {
            final int begin = mListerner.beginBroadcast();
            for (int i = 0; i < begin; i++) {
                mListerner.getBroadcastItem(i).onAidlListerner(
                        new StringBuilder().
                        append("Pid:").append(android.os.Process.myPid()).
                        append(" Threadtime:").append(SystemClock.uptimeMillis()).
                        toString()
                );
            }
            mListerner.finishBroadcast();
        }
        return "Hello AIDL!";
    }

    @Override
    public void registerListerner(IAidlListernerInterface listerner) throws RemoteException {
        mListerner.register(listerner);
    }

    @Override
    public void unregisterListerner(IAidlListernerInterface listerner) throws RemoteException {
        mListerner.unregister(listerner);
    }
}
