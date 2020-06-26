package com.example.pushnotification;


import android.graphics.Color;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyFirebaseInstanceIDService extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage){
        super.onMessageReceived(remoteMessage);
        shownotification(remoteMessage.getNotification().getTitle(),remoteMessage.getNotification().getBody());

   }

    private void shownotification(String title, String message) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "MyNotifications-Raj")
                .setContentTitle(title)
                .setSmallIcon(R.drawable.common_google_signin_btn_icon_dark_normal_background)
                .setAutoCancel(true)
                .setColor(Color.RED)
                .setContentText(message);
        NotificationManagerCompat manager = NotificationManagerCompat.from(this);
        manager.notify(999,builder.build());


    }
}































