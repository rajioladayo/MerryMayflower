package com.rharj.merrymayflower.alarm;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;

import com.rharj.merrymayflower.service.AlarmService;

/**
 * Created by Raji Oladayo on 11/02/16.
 */
public class MyAlarmReceiver extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {

        Intent myIntent = new Intent(context, AlarmService.class);
        context.startService(myIntent);
    }
}
