package com.indofantasysports.indofantasysports;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class DashboardActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    public ArrayList<MatchList> match_list_fix = new ArrayList<MatchList>();
    public ArrayList<MatchList> match_list_live = new ArrayList<MatchList>();
    public ArrayList<MatchList> match_list_result = new ArrayList<MatchList>();
    private ListView listView;
    private Button fixtureBtn;
    private Button liveBtn;
    private Button resultBtn;
    private MatchListAdapter matchListAdapter;
    private View mProgressView;
    private static final String HTTP_RESOURCE_URI = "http://logicalmind-demo.com/ifs/public/api/get/recent/matches";
    private TimeCalculator mTimeCalculator;
    private Thread mTimeCalculatorThread;
    private String timecountdown;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        APiMatchList aPiMatchList = new APiMatchList();
        aPiMatchList.execute(HTTP_RESOURCE_URI);
        listView = (ListView) findViewById(R.id.matchesListView);
        //if we click on fixtures btn now
        fixtureBtn = (Button) findViewById(R.id.fixtureBtn);
        fixtureBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                showProgress(true);
                fixtureBtn.setBackgroundResource(R.color.royalblue);
                fixtureBtn.setTextColor(getResources().getColor(R.color.whiteText));
                liveBtn.setBackgroundResource(R.color.whiteText);
                liveBtn.setTextColor(getResources().getColor(R.color.royalblue));
                resultBtn.setBackgroundResource(R.color.whiteText);
                resultBtn.setTextColor(getResources().getColor(R.color.royalblue));
                matchListAdapter = new MatchListAdapter(DashboardActivity.this, R.layout.match_listview_container, match_list_fix);
                listView.setAdapter(matchListAdapter);
                showProgress(false);
            }
        });

        //if we click on live btn now
        liveBtn = (Button) findViewById(R.id.liveBtn);
        liveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showProgress(true);
                liveBtn.setBackgroundResource(R.color.royalblue);
                liveBtn.setTextColor(getResources().getColor(R.color.whiteText));
                fixtureBtn.setBackgroundResource(R.color.whiteText);
                fixtureBtn.setTextColor(getResources().getColor(R.color.royalblue));
                resultBtn.setBackgroundResource(R.color.whiteText);
                resultBtn.setTextColor(getResources().getColor(R.color.royalblue));
                matchListAdapter = new MatchListAdapter(DashboardActivity.this, R.layout.match_listview_container, match_list_live);
                listView.setAdapter(matchListAdapter);
                showProgress(false);
            }
        });

        //if we click results btn now
        resultBtn = (Button) findViewById(R.id.resultBtn);
        resultBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showProgress(true);
                liveBtn.setBackgroundResource(R.color.whiteText);
                liveBtn.setTextColor(getResources().getColor(R.color.royalblue));
                fixtureBtn.setBackgroundResource(R.color.whiteText);
                fixtureBtn.setTextColor(getResources().getColor(R.color.royalblue));
                resultBtn.setBackgroundResource(R.color.royalblue);
                resultBtn.setTextColor(getResources().getColor(R.color.whiteText));
                matchListAdapter = new MatchListAdapter(DashboardActivity.this, R.layout.match_listview_container, match_list_result);
                listView.setAdapter(matchListAdapter);
                showProgress(false);
            }
        });
        mProgressView = findViewById(R.id.login_progress);
        mTimeCalculator = new TimeCalculator(DashboardActivity.this);
        mTimeCalculatorThread = new Thread(mTimeCalculator);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigation_drawer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            // Handle the camera action
        } else if (id == R.id.nav_my_profile) {

        } else if (id == R.id.nav_my_contests) {

        } else if (id == R.id.nav_my_account) {

        } else if (id == R.id.nav_leaderboard) {

        } else if (id == R.id.nav_invite_friend) {

        } else if (id == R.id.nav_helpdesk) {
            Intent helpdeskInt = new Intent(DashboardActivity.this, HelpDeskActivity.class);
            startActivity(helpdeskInt);
        } else if (id == R.id.nav_fantasy_point) {
            Intent fantasyPointSysInt = new Intent(DashboardActivity.this, FanatsyPointSystemActivity.class);
            startActivity(fantasyPointSysInt);
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private class MatchListAdapter extends ArrayAdapter<MatchList> {


        private int layoutResourseId;
        private ArrayList<MatchList> match_list;
        private Context context;

        public MatchListAdapter(@NonNull Context context, @LayoutRes int layoutResourseId, ArrayList<MatchList> match_list) {
            super(context, layoutResourseId, match_list);
            this.context = context;
            this.layoutResourseId = layoutResourseId;
            this.match_list = match_list;
            //Log.d("Match Length :", String.valueOf(match_list.size()));
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

            Log.d("data array element:", String.valueOf(position));
            convertView = getLayoutInflater().inflate(layoutResourseId, null);
            MatchList matchList = match_list.get(position);
            TextView team_one = (TextView) convertView.findViewById(R.id.teamOneTxtV);
            TextView team_two = (TextView) convertView.findViewById(R.id.teamTwoTxtV);
            TextView league_name = (TextView) convertView.findViewById(R.id.leagueNameTxtV);
            TextView match_start_time = (TextView) convertView.findViewById(R.id.timeTextV);

            //Log.d("Array List", dataArray[0] );
            team_one.setText(matchList.team_one);
            team_two.setText(matchList.team_two);
            league_name.setText(matchList.league_name);
            match_start_time.setText(matchList.time);


            return convertView;
        }
    }
    public class MatchList{


        public String team_one;
        public String team_two;
        public String league_name;
        public String time;

        public MatchList(String team_one, String team_two, String league_name, String time){

            this.team_one = team_one;
            this.team_two = team_two;
            this.league_name = league_name;
            this.time = time;

        }



    }
    /**
     * Shows the progress UI and hides the login form.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            listView.setVisibility(show ? View.GONE : View.VISIBLE);
            listView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    listView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            listView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }
    private class APiMatchList extends AsyncTask<String, Integer, String> {

        public String user_id;
        public ProgressDialog progressDialog;
        public APiMatchList(){

        }
        @Override
        protected void onPreExecute() {

            progressDialog = new ProgressDialog(DashboardActivity.this);
            progressDialog.setMessage("...Loading details...");
            progressDialog.setCancelable(false);
            progressDialog.show();
            super.onPreExecute();

        }

        @Override
        protected String doInBackground(String... params) {

            String apiuserdetailurl = params[0];
            try{

                URL url = new URL(apiuserdetailurl);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setDoInput(true);
                urlConnection.setConnectTimeout(30*1000);
                urlConnection.setRequestMethod("POST");
                urlConnection.setReadTimeout(30 * 1000);
                if(urlConnection.getResponseCode() == HttpURLConnection.HTTP_OK){

                    InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                    BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                    String line ;
                    StringBuilder result = new StringBuilder();
                    while((line = reader.readLine()) != null){
                        result.append(line);
                    }
                    return result.toString();

                }


            }catch(Exception e){
                e.printStackTrace();
            }


            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            Log.d(" My Match List :", result);
            JSONObject payhistory_reader = null;
            try {
                payhistory_reader = new JSONObject(result);
                JSONObject getMatch = payhistory_reader.getJSONObject("getMatch");
                JSONArray getMatchList = getMatch.getJSONArray("cards");
                int counter = 0;
                for(int i = 0; i < getMatchList.length(); i++){

                    //Log.d("Team Name", getMatchList.getJSONObject(i).getJSONObject("teams").getJSONObject("a").getString("name"));
                    if(getMatchList.getJSONObject(i).getString("status").equals("notstarted") ) {

                        //mTimeCalculatorThread.start();
                        //mTimeCalculator.setStartTime(getMatchList.getJSONObject(i).getJSONObject("start_date").getString("iso"));
                        //mTimeCalculator.start();
                        //Log.d("Start timer", timecountdown);
                        match_list_fix.add(new MatchList(getMatchList.getJSONObject(i).getJSONObject("teams").getJSONObject("a").getString("name"), getMatchList.getJSONObject(i).getJSONObject("teams").getJSONObject("b").getString("name"), getMatchList.getJSONObject(i).getJSONObject("season").getString("name"), getMatchList.getJSONObject(i).getJSONObject("start_date").getString("iso")));
                    }else if(getMatchList.getJSONObject(i).getString("status").equals("started")){
                        match_list_live.add(new MatchList(getMatchList.getJSONObject(i).getJSONObject("teams").getJSONObject("a").getString("name"), getMatchList.getJSONObject(i).getJSONObject("teams").getJSONObject("b").getString("name"), getMatchList.getJSONObject(i).getJSONObject("season").getString("name"), "In Progress"));
                    }else {
                        match_list_result.add(new MatchList(getMatchList.getJSONObject(i).getJSONObject("teams").getJSONObject("a").getString("name"), getMatchList.getJSONObject(i).getJSONObject("teams").getJSONObject("b").getString("name"), getMatchList.getJSONObject(i).getJSONObject("season").getString("name"), "Completed"));
                    }

                }

                listView = (ListView) findViewById(R.id.matchesListView);
                matchListAdapter = new MatchListAdapter(DashboardActivity.this, R.layout.match_listview_container, match_list_fix);
                listView.setAdapter(matchListAdapter);

            } catch (JSONException e) {
                e.printStackTrace();
            }
            progressDialog.dismiss();

        }
    }

    public void updateMatchStartTime(final String time){

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Log.d("Updated Time", time);
                timecountdown = time;
            }
        });



    }

}