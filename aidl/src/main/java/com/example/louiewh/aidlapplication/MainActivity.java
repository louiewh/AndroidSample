package com.example.louiewh.aidlapplication;

import android.os.Handler;
import android.os.Message;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView text;
    private TextView listenerText;
    private TextView text2;
    private Handler mMainHandler;
    public static AidlBinderServiceProxy  mAidlBinderService;
    private HelloWorldProxy helloWorldProxy;
    private HelloAidlProxy helloAidlProxy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text = (TextView) findViewById(R.id.text1);
        text2 = (TextView) findViewById(R.id.text2);
        listenerText = (TextView) findViewById(R.id.textlistener);

        mMainHandler = new AidlHandler();

        mAidlBinderService = AidlBinderServiceProxy.instance(getApplicationContext(), mMainHandler);
        text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                text.setText("Pid:" + android.os.Process.myPid() + helloWorldProxy.printHelloWorld());
            }
        });

        text2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(helloAidlProxy == null) {
                    helloAidlProxy = new HelloAidlProxy(mAidlBinderService);
                }

                text2.setText(Integer.toString(helloAidlProxy.getPidInfo().getPid()));
            }
        });
    }

    @Override
    protected void onDestroy() {
        mAidlBinderService.unbindService();
        super.onDestroy();
    }

     class AidlHandler extends Handler{
        public void handleMessage(Message msg) {

            switch(msg.what) {
                case AidlBinderServiceProxy.AidlBinderService:
                    Log.d("louie", "AidlBinderService");
                    helloWorldProxy = new HelloWorldProxy(mAidlBinderService);

                    helloWorldProxy.registerListener(new AidlListener() {
                        @Override
                        public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

                        }

                        @Override
                        public void onAidlListerner(String str) throws RemoteException {
                            listenerText.setText(str);
                        }
                    });
                    break;
                default:
                    super.handleMessage(msg);
            }
        }
    }
}



