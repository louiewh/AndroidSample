package com.dawang.androidexample.widget.recyclerview;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.dawang.androidexample.R;

public class RecyclerViewActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private LoopRecyclerViewAdapter mRecyclerViewAdapter;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_recycler);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.androidicon);
        toolbar.setLogo(android.R.drawable.ic_dialog_email);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        initRecyclerView();
    }


    void initRecyclerView(){
        mRecyclerViewAdapter = new LoopRecyclerViewAdapter(this.getApplicationContext());
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        mRecyclerView.scrollToPosition(mRecyclerViewAdapter.getInitPosition());//开始时的偏移量
        new PagerSnapHelper().attachToRecyclerView(mRecyclerView);
        mRecyclerView.setAdapter(mRecyclerViewAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_widget, menu);
        View view = toolbar.findViewById(R.id.action_settings);

        if(view instanceof TextView){
            ((TextView) view).setTextSize(100);
            ((TextView) view).setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.colorAccent));
            invalidateOptionsMenu();
        }

        return  true;
    }
}
