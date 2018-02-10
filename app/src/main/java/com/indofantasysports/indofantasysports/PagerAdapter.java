package com.indofantasysports.indofantasysports;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

/**
 * Created by Pankaj on 30/10/2017 AD.
 */

public class PagerAdapter extends FragmentStatePagerAdapter {

    int tabcount;
    FragmentManager fm;
    public PagerAdapter(FragmentManager fm, int tabcount) {
        super(fm);
        this.tabcount = tabcount;
        //Log.d("TAB C", String.valueOf(this.tabcount));
        this.fm = fm;
    }

    @Override
    public Fragment getItem(int position) {
        switch(position){

            case 0:
                //Log.d("First", "PP");
                T20Fragment t20Fragment = new T20Fragment();
                //this.fm.beginTransaction().replace(R.id.container, t20Fragment).commit();
                return t20Fragment;
            case 1 :
                ODIFragment odiFragment = new ODIFragment();
                //this.fm.beginTransaction().replace(R.id.container, odiFragment).commit();
                return odiFragment;
            case 2 :
                TestFragment testFragment = new TestFragment();
                //this.fm.beginTransaction().replace(R.id.container, testFragment).commit();
                return testFragment;

            default:
                //Log.d("First", "PP");
                return null;

        }
    }

    @Override
    public int getCount() {
        return tabcount;
    }
}
