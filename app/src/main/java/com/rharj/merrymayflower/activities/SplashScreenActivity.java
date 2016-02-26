package com.rharj.merrymayflower.activities;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.content.Intent;
import com.rharj.merrymayflower.R;
import com.rharj.merrymayflower.alarm.MyAlarmReceiver;
import com.rharj.merrymayflower.util.AppUtils;

import java.util.Calendar;


public class SplashScreenActivity extends Activity {

    //Splash screen timer
    private static int SPLASH_TIME_OUT = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        new Handler().postDelayed(new Runnable() {
            //This method will be executed once the timer is over
            //Start your app main activity
            @Override
            public void run() {
                //Start the alarm service
                AppUtils.setAlarm(getApplicationContext());

                Intent intent = new Intent(SplashScreenActivity.this, MenuActivity.class);
                startActivity(intent);

                //close this activity
                finish();
            }

        }, SPLASH_TIME_OUT);
    }
}
