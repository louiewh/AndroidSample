// IHelloAidlInterface.aidl
package com.example.louiewh.aidlapplication;
import  com.example.louiewh.aidlapplication.PidInfo;
// Declare any non-default types here with import statements

interface IHelloAidlInterface {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
            double aDouble, String aString);

    PidInfo getPidInfo();
}
