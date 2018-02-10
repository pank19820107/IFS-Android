package com.indofantasysports.indofantasysports;

import android.app.ListFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.support.v4.app.Fragment;

/**
 * Created by Pankaj on 30/10/2017 AD.
 */

public class TestFragment extends Fragment {

    String[] maincat = {"Batting", "Bowling", "Fielding", "Others", "Economy Rate", "Strike Rate"};

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_test, container, false);

        return view;
    }
}
