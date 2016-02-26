package com.rharj.merrymayflower.util;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import com.rharj.merrymayflower.alarm.MyAlarmReceiver;

import java.util.Calendar;
import java.util.Random;

/**
 * Created by Raji Oladayo on 11/02/16.
 */
public class AppUtils {

    public static String getRandomNumber(int[] array){
        int random = new Random().nextInt(array.length);
        return String.valueOf(array[random]);
    }


    public static void setAlarm(Context context){
        Intent intent = new Intent(context, MyAlarmReceiver.class);
        PendingIntent pIntent = PendingIntent.getBroadcast(context,0,intent,0);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

        // Set the alarm to start at 10:00 AM
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, 10);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), 86400000, pIntent);

    }

}