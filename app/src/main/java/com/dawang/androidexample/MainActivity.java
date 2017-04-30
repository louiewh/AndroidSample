package com.dawang.androidexample;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.ArraySet;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.dawang.androidexample.animation.AnimationActivity;
import com.dawang.androidexample.designpattern.DesignPatternActivity;
import com.dawang.androidexample.http.HttpsActivity;
import com.dawang.androidexample.mvp.view.MvpActivity;
import com.dawang.androidexample.view.ViewActivity;
import com.dawang.androidexample.window.WindowActivity;

import java.util.HashMap;
import java.util.HashSet;
import java.util.SortedSet;

public class MainActivity extends AppCompatActivity {
    Context mContext;
    ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
        initViews();
        loadViews();

        initSharePrefences();
    }

    void initData(){
        mContext = this.getApplicationContext();
    }

    void initViews(){
        setContentView(R.layout.activity_main);
        mListView = (ListView) findViewById(R.id.listview);
    }

    void loadViews(){
        mListView.setAdapter(new ExampleBaseAdapter(mContext));
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                switch (position){
                    case 0:
                        Intent  intentHttp = new Intent();
                        intentHttp.setClass(mContext, HttpsActivity.class);
                        mContext.startActivity(intentHttp);
                        break;
                    case 1:
                        Intent  intentWindow = new Intent();
                        intentWindow.setClass(mContext, WindowActivity.class);
                        mContext.startActivity(intentWindow);
                    case 2:
                        Intent  intentAnimation = new Intent();
                        intentAnimation.setClass(mContext, AnimationActivity.class);
                        mContext.startActivity(intentAnimation);
                        break;
                    case 3:
                        Intent  intentAidl = new Intent();
                        intentAidl.setClassName("com.example.louiewh.aidlapplication", "com.example.louiewh.aidlapplication.MainActivity");
                        mContext.startActivity(intentAidl);
                        break;
                    case 4:
                        Intent  intentMvp = new Intent();
                        intentMvp.setClass(mContext, MvpActivity.class);
                        mContext.startActivity(intentMvp);
                        Log.e("louie", replaceSpace(new StringBuffer("Hello World")));
                        break;
                    case 5:
                        Intent  intentDp = new Intent();
                        intentDp.setClass(mContext, DesignPatternActivity.class);
                        mContext.startActivity(intentDp);
                        Log.e("louie", replaceSpace(new StringBuffer("Hello World")));
                        break;
                    case 6:
                        Intent  intentView = new Intent();
                        intentView.setClass(mContext, ViewActivity.class);
                        mContext.startActivity(intentView);
                        break;
                    default:
                        break;
                }
            }
        });
    }

    void initSharePrefences() {
        SharedPreferences preferences = this.getSharedPreferences("preference_sample", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("boolen", false);
        editor.putInt("int", 888);
        editor.putFloat("float", 1.0f);
        editor.putString("String", "Hello World");
        HashSet<String> set = new HashSet<>();
        set.add("Hello");
        set.add("World");
        editor.putStringSet("Set", set);
        editor.commit();
    }
        public String replaceSpace(StringBuffer str) {
            int len = str.length();
            int blank = 0;

            Log.e("louie", " " + str.toString());
            Log.e("louie", " " +  str.toString().toCharArray().toString());


            char[] charArray = str.toString().toCharArray();
            for(char c: charArray){
                if(' ' == c ){
                    blank ++;
                }
            }

            StringBuffer reStr = new StringBuffer();
            for(int i = 0;  i < len; i++) {
                if(charArray[i] == ' '){
                    reStr.append("%20");
                } else {
                    reStr.append(charArray[i]);
                }

                Log.e("louie", "for:" + charArray[i] + " StringBuffer:" + reStr.toString());
            }

            Log.e("louie", " " + str.toString());

            return reStr.toString();
        }
}
