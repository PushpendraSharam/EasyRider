package com.good.loginusingretrofit;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.res.ResourcesCompat;

import com.good.loginusingretrofit.Database.DatabaseModalClass;
import com.good.loginusingretrofit.Database.NotificationDatabase;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.ArrayList;
import java.util.Map;

public class FirbaseMessageing extends FirebaseMessagingService {
    @Override
    public void onNewToken(@NonNull String s) {
        super.onNewToken(s);
        Log.d("Token is ", s);
    }

    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {

        if (remoteMessage.getNotification() != null)
            pushNotification(remoteMessage.getNotification().getTitle(), remoteMessage.getNotification().getBody());
        String title = remoteMessage.getData().get("title");
        String body = remoteMessage.getData().get("body");
        pushNotification(title, body);
    }

    private void pushNotification(String title, String body) {
        Log.d("Error", "This is a firebase");
        NotificationDatabase database = new NotificationDatabase(this);
        database.addNotification(title, body);
        Intent intent=new Intent(FirbaseMessageing.this,ShowNotification.class);
        PendingIntent pendingIntent=PendingIntent.getActivity(FirbaseMessageing.this,10101,intent,PendingIntent.FLAG_MUTABLE);
        NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            Notification notification = new Notification.Builder(this)
                    .setSmallIcon(R.drawable.main_logo)
                    .setContentText(body)
                    .setSubText(title)
                    .setContentIntent(pendingIntent)
                    .setChannelId("hello")
                    .setAutoCancel(true)
                    .build();
            nm.createNotificationChannel(new NotificationChannel("hello", "New Channel", NotificationManager.IMPORTANCE_HIGH));
            nm.notify(111, notification);
        } else {
            Notification notification = new Notification.Builder(this)
                    .setSmallIcon(R.drawable.main_logo)
                    .setContentText(body)
                    .setSubText(title)
                    .setContentIntent(pendingIntent)
                    .setAutoCancel(true)
                    .build();
            nm.notify(111, notification);
        }
    }
}




