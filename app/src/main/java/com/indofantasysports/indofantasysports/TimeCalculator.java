package com.indofantasysports.indofantasysports;

import android.content.Context;
import android.util.Log;

import java.sql.Array;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Pankaj on 21/11/2017 AD.
 */

public class TimeCalculator implements Runnable {

    private Context mContest;
    private String cStartTime;
    private long currTime;
    private boolean mIsRunnable;
    private Date date;
    public TimeCalculator(Context context){

        this.mContest = context;
    }

    public void setStartTime(String startTime){

        this.cStartTime = startTime;
    }

    public String getStartTime(){

        return this.cStartTime;
    }

    public void start(){

        currTime = System.currentTimeMillis();
        Log.d("Start Time", cStartTime);
        mIsRunnable = true;

    }

    public void stop(){

        mIsRunnable = false;

    }
    @Override
    public void run() {

        String[] cStartTimeArr = cStartTime.split("T");
        Log.d("Start Time", cStartTimeArr[0]);
        while(mIsRunnable){

            SimpleDateFormat formatter = new SimpleDateFormat( "yyyy-MM-dd", Locale.ENGLISH ) ;

            try {

                date = (Date)formatter.parse(cStartTimeArr[0]);
                long timeInterval = date.getTime() - currTime;
                int seconds = (int) ((timeInterval/1000) % 60);
                int minutes = (int) ((timeInterval/60000) % 60);
                int hours = (int) ((timeInterval/3600000) % 24);
                Log.d("Time Interval", String.valueOf(timeInterval));
                ((DashboardActivity)(mContest)).updateMatchStartTime(String.format(Locale.ENGLISH,

                        "%02d h:%02d m:%02d s", hours,minutes,seconds

                ));

            } catch (ParseException e) {
                e.printStackTrace();
            }


        }

    }
}