// IHelloWorldInterface.aidl
package com.example.louiewh.aidlapplication;
import com.example.louiewh.aidlapplication.IAidlListernerInterface;
// Declare any non-default types here with import statements

interface IHelloWorldInterface {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
            double aDouble, String aString);

    String printHelloWorld();

    void registerListerner(IAidlListernerInterface listerner);

    void unregisterListerner(IAidlListernerInterface listerner);

}
