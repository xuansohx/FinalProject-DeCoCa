package com.example.fcmtest2;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;
import android.webkit.WebView;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;

import com.firebase.jobdispatcher.FirebaseJobDispatcher;
import com.firebase.jobdispatcher.GooglePlayDriver;
import com.firebase.jobdispatcher.Job;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyFirebaseMessagingService extends FirebaseMessagingService {

   private static final String TAG = "MyFirebaseMsgService";

   private String title ="";
   private String body ="";
   private String color ="";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        Log.d(TAG,"From:"+ remoteMessage.getFrom());

        if(remoteMessage.getData().size()>0){
            Log.d(TAG,"Message data payload:"+remoteMessage.getData());
           title = remoteMessage.getData().get("title");
            body = remoteMessage.getData().get("body");
           // color=remoteMessage.getData().get("color");

            if(true){
                scheduleJob();
            }else {
                handleNow();
            }
        }
        if(remoteMessage.getNotification()!=null){
            Log.d(TAG,"Message Norification Body:" + remoteMessage.getNotification().getColor());
            Log.d(TAG,"Message Norification Body:" + remoteMessage.getNotification().getIcon());
            Log.d(TAG,"Message Norification Body:" + remoteMessage.getNotification().getTitle());
            Log.d(TAG,"Message Norification Body:" + remoteMessage.getNotification().getBody());
        }
        sendNotification();

    }

    @Override
    public void onNewToken(String token) {
        super.onNewToken(token);
        Log.d(TAG,"Refreshed token:"+ token);
        sendRegistrationToserver(token);
    }

    private void sendRegistrationToserver(String token) {
    }

    private void scheduleJob(){
        FirebaseJobDispatcher dispatcher = new FirebaseJobDispatcher(new GooglePlayDriver(this));
        Job myJob = dispatcher.newJobBuilder()
                .setService(MyJobService.class)
                .setTag("my-job-tag")
                .build();
        dispatcher.schedule(myJob);

    }
    private void handleNow(){
        Log.d(TAG,"Short lived task is done");
    }
    private void sendNotification(){
        Intent intent = new Intent(this,MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_ONE_SHOT);

        String channelId= "채널ID";
        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder=
                new NotificationCompat.Builder(this,channelId)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(title)
                .setContentText(body)
              //  .setColor(Color.parseColor(color))
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent)
                .setPriority(Notification.PRIORITY_HIGH);

        NotificationManager notificationManager =(NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel = new NotificationChannel(channelId,
                    "Channel human readable title",
                    NotificationManager.IMPORTANCE_DEFAULT);
            notificationManager.createNotificationChannel(channel);
        }
        notificationManager.notify(0,notificationBuilder.build());
    }

    
}
