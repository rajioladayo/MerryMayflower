package com.rharj.merrymayflower.service;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.rharj.merrymayflower.R;
import com.rharj.merrymayflower.activities.ContentActivity;
import com.rharj.merrymayflower.model.XmlValueModels;
import com.rharj.merrymayflower.util.AppUtils;
import com.rharj.merrymayflower.xmlparser.XMLParser;


/**
 * Created by Raji Oladayo on 11/02/16.
 */
public class AlarmService extends Service {

    private NotificationManager alarmNotificationManager;

    private int[] array = new int[] {1,2};
    XmlValueModels xModels;

    @Override
    public void onCreate(){
        super.onCreate();
    }

    @SuppressWarnings("static-access")
    @Override
    public void onStart(Intent intent, int startId){
        super.onStart(intent,startId);

        String randomId = AppUtils.getRandomNumber(array);
        try {
           xModels =  new XMLParser().search(this.getAssets().open("contents.xml"), randomId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String title = xModels.getTitle();
        String contentDetails = xModels.getDetails();
        String contentAuthor = xModels.getAuthor();
        String hymmId = xModels.getId();

        sendNotification("Hymm of the day",title,contentDetails,contentAuthor,hymmId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    private void sendNotification(String msg, String title,String contentDetails,String contentAuthor,String hymmId){
        Log.d("Alarm Service", "Preparing to send notification");
        alarmNotificationManager = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);

        Intent intent = new Intent(this.getBaseContext(),ContentActivity.class);
        intent.putExtra("title", title);
        intent.putExtra("details", contentDetails);
        intent.putExtra("author",contentAuthor);
        intent.putExtra("hymmId",hymmId);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_SINGLE_TOP);

        PendingIntent contentIntent = PendingIntent.getActivity(this.getBaseContext(), 0 ,intent,PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_CANCEL_CURRENT);
        NotificationCompat.Builder alarmNotificationBuilder = new NotificationCompat.Builder(this);
        alarmNotificationBuilder.setContentTitle("Merry Mayflower");
        alarmNotificationBuilder.setSmallIcon(R.mipmap.ic_launcher);
        alarmNotificationBuilder.setStyle(new NotificationCompat.BigTextStyle().bigText(msg));
        alarmNotificationBuilder.setContentText(msg);
        alarmNotificationBuilder.setAutoCancel(true);
        alarmNotificationBuilder.setContentIntent(contentIntent);

        int NOTIFICATION_ID = (int) (System.currentTimeMillis() & 0xfffffff);
        alarmNotificationManager.notify(NOTIFICATION_ID, alarmNotificationBuilder.build());
        Log.d("Alarm Service", "Notification Sent");
    }

    @Override
    public void onDestroy()
    {
        // TODO Auto-generated method stub
        super.onDestroy();
    }
}
