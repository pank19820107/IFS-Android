package com.indofantasysports.indofantasysports;

import android.content.Intent;
import android.icu.util.TimeUnit;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class LoadingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);
        ImageView jumpingfootballview = (ImageView) findViewById(R.id.jumpingfootbalimageview);
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotating_football_layout);
        jumpingfootballview.setAnimation(animation);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                finish();
                startActivity(new Intent(LoadingActivity.this, LaunchActivity.class));
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    protected void goToLaunchScreen(){

        Intent launchInt = new Intent(LoadingActivity.this, LaunchActivity.class);
        startActivity(launchInt);


    }
}
