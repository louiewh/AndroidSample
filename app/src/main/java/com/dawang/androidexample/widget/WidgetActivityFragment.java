package com.dawang.androidexample.widget;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dawang.androidexample.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class WidgetActivityFragment extends Fragment {

    public WidgetActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_widget, container, false);
    }
}
