package com.dawang.androidexample.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Window;

import com.dawang.androidexample.R;

import java.util.ArrayList;

/**
 * Created by DaWang on 2017/4/13.
 */

public class ViewActivity extends FragmentActivity {

    private ViewPager mPager;
    private ArrayList<Fragment> mFragment = new ArrayList<>();
    private FragmentManager mFragmentManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.view_activity_layout);

        mPager = (ViewPager) findViewById(R.id.pager);
        mFragment.add(new MyViewFragment());
        mFragment.add(new RainViewFragment());
        mFragment.add(new VvvFragment());
        mFragment.add(new FlowLayoutFragment());
        mFragment.add(new ClockFragment());

        mFragmentManager = getSupportFragmentManager();
        mPager.setAdapter(new FragmentPagerAdapter(mFragmentManager) {
            @Override
            public Fragment getItem(int position) {
                return mFragment.get(position);
            }

            @Override
            public int getCount() {
                return mFragment.size();
            }
        });
    }
}
