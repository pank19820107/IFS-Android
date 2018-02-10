package com.indofantasysports.indofantasysports;

import android.app.ListFragment;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.support.v4.app.Fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Pankaj on 30/10/2017 AD.
 */

public class T20Fragment extends Fragment {

    ExpandableListView expandableListView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_main, container, false);
        List<String> pointsyscate = new ArrayList<String>();
        List<String> psc1 = new ArrayList<String>();
        List<String> psc2 = new ArrayList<String>();
        List<String> psc3 = new ArrayList<String>();
        List<String> psc4 = new ArrayList<String>();
        List<String> psc5 = new ArrayList<String>();
        List<String> psc6 = new ArrayList<String>();
        HashMap<String, List<String>> psc_dec = new HashMap<String, List<String>>();
        String psc[] = getResources().getStringArray(R.array.pointsyscat);
        String batting[] = getResources().getStringArray(R.array.batting);
        String bowling[] = getResources().getStringArray(R.array.bowling);
        String fielding[] = getResources().getStringArray(R.array.fielding);
        String eco_rate[] = getResources().getStringArray(R.array.economyrate);
        String others[] = getResources().getStringArray(R.array.others);
        String strike_rate[] = getResources().getStringArray(R.array.strikerate);
        for (String cat_str : psc){
            pointsyscate.add(cat_str);
        }
        for (String psc_desc_str : batting){
            psc1.add(psc_desc_str);
        }
        for (String psc_desc_str : bowling){
            psc2.add(psc_desc_str);
        }
        for (String psc_desc_str : fielding){
            psc3.add(psc_desc_str);
        }
        for (String psc_desc_str : others){
            psc4.add(psc_desc_str);
        }
        for (String psc_desc_str : eco_rate){
            psc5.add(psc_desc_str);
        }
        for (String psc_desc_str : strike_rate){
            psc6.add(psc_desc_str);
        }
        psc_dec.put(pointsyscate.get(0), psc1);
        psc_dec.put(pointsyscate.get(1), psc2);
        psc_dec.put(pointsyscate.get(2), psc3);
        psc_dec.put(pointsyscate.get(3), psc4);
        psc_dec.put(pointsyscate.get(4), psc5);
        psc_dec.put(pointsyscate.get(5), psc6);
        expandableListView = (ExpandableListView) view.findViewById(R.id.t20expandablelistview);
        ExpandableListViewAdapter expandableListViewAdapter = new ExpandableListViewAdapter(getActivity(), pointsyscate, psc_dec);
        expandableListView.setAdapter(expandableListViewAdapter);
        return view;
    }
}
