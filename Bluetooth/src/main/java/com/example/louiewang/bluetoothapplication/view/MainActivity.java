package com.example.louiewang.bluetoothapplication.view;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.louiewang.bluetoothapplication.R;
import com.example.louiewang.bluetoothapplication.presenter.BtPresenter;

public class MainActivity extends AppCompatActivity implements IBtView{

    private TextView mDiscoverText;
    private TextView mPairedText;

    private ListView mListView;
    private BtPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initData();
        initViews();
        loadViews();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mPresenter.ensureDiscoverable();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.onDestroy();
    }

    void initData(){
        mPresenter = new BtPresenter(getApplicationContext(), this);
    }
    void initViews(){
        mDiscoverText = (TextView) findViewById(R.id.hello);
        mPairedText   = (TextView) findViewById(R.id.pair);
        mListView = (ListView) findViewById(R.id.listview);
    }

    void loadViews(){
        mPresenter.setAdapter();
        mDiscoverText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 100);
                mPresenter.startDiscovery();
            }
        });

        mPairedText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.getPairedDevice();
            }
        });

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mPresenter.removePairedDevice(position);
            }
        });
    }

    @Override
    public ListView getListView() {
        return mListView;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == mPresenter.REQUEST_ENABLE_BT){
            Log.e("Bt", "onActivityResult 蓝牙已经开启");
        }
    }
}
