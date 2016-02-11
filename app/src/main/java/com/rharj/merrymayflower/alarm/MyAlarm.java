package com.rharj.merrymayflower.alarm;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import com.rharj.merrymayflower.activities.ContentActivity;

import java.util.Calendar;

/**
 * Created by Raji Oladayo on 11/02/16.
 */
public class MyAlarm {

    public void setAlarm(Context context){
        Intent intent = new Intent(context, ContentActivity.class);
        PendingIntent pIntent = PendingIntent.getBroadcast(context,0,intent,0);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

        // Set the alarm to start at 10:00 AM
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, 10);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);

        // for repeating in every 24 hours
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(), 86400000,pIntent);

    }
}
