package com.example.louiewang.bluetoothapplication.view;

import android.content.Intent;
import android.widget.ListView;

/**
 * Created by louie.wang on 2017/4/17.
 */

public interface IBtView {
    ListView getListView();

    void startActivityForResult(Intent enableBtIntent, int requestEnableBt);
}
