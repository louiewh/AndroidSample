package com.example.louiewh.aidlapplication;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by louiewh on 16/7/31.
 */
public class PidInfo implements Parcelable {
    private int mPid;
    PidInfo(int pid ){
        mPid = pid;
    }

    PidInfo(Parcel in ) {
        mPid = in.readInt();
    }

    public int getPid(){
        return mPid;
    }

    public void setPid(int pid ){
        mPid = pid;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
         dest.writeInt(mPid);
    }

    public static final Creator<PidInfo> CREATOR = new Creator<PidInfo>(){

        @Override
        public PidInfo createFromParcel(Parcel source) {
            return new PidInfo(source);
        }

        @Override
        public PidInfo[] newArray(int size) {
            return new PidInfo[0];
        }
    };
}
