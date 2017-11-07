package com.dawang.androidexample.widget;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import com.dawang.androidexample.R;

public class WidgetActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private MenuAdapter mRecyclerViewAdapter;
    Toolbar toolbar;

    private static final String[] mMenuArray = {
            "RecyclerView"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_widget);

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
        mRecyclerViewAdapter = new MenuAdapter();
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
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

    class MenuAdapter extends RecyclerView.Adapter<MenuViewHolder>{
        private int VIEW_LAYOUT = R.layout.recyclerview_menu_item;

        @Override
        public MenuViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(VIEW_LAYOUT, parent, false);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Log.e("onCreateViewHolder", "onCreateViewHolder:");
                }
            });

            return new MenuViewHolder(view);
        }

        @Override
        public void onBindViewHolder(MenuViewHolder holder, int position) {

            holder.bindView(mMenuArray[position]);
        }

        @Override
        public int getItemCount() {
            return mMenuArray.length;
        }
    }


    class MenuViewHolder extends RecyclerView.ViewHolder{
        TextView mMenu;

        public MenuViewHolder(View itemView) {
            super(itemView);
            mMenu = itemView.findViewById(R.id.tv_name);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    switch (getLayoutPosition()){
                        case 0:
                            Intent intent = new Intent(getApplicationContext(), RecyclerViewActivity.class);
                            WidgetActivity.this.startActivity(intent);
                            break;
                        default:
                            break;
                    }
                }
            });
        }

        public void bindView(String menu){
            mMenu.setText(menu);
            mMenu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.e("onCreateViewHolder", "bindView:"+getLayoutPosition());
                }
            });
        }
    }
}
