package com.example.firebasepushnotification;

import static android.content.Context.NOTIFICATION_SERVICE;
import static androidx.core.content.ContextCompat.getSystemService;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Build;
import android.provider.Settings;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.res.ResourcesCompat;

public class MyToastReciver extends BroadcastReceiver {
    MediaPlayer mp;
    public static String NOTIFICATION_ID = "notification-id";
    public static String NOTIFICATION = "notification";

    @Override
    public void onReceive(Context context, Intent intent) {

        Toast.makeText(context, "Running ", Toast.LENGTH_SHORT).show();
        Log.d("My Reciver", "I am Running");
        mp = MediaPlayer.create(context, Settings.System.DEFAULT_NOTIFICATION_URI);
        // mp.setLooping(true);
        mp.start();
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);
        Intent iNotify=new Intent(context,MainActivity.class);

        iNotify.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        RemoteViews contentView = new RemoteViews("com.example.firebasepushnotification", R.layout.custom_push);
        //contentView.setImageViewResource(R.id.image, R.drawable.user);
        contentView.setTextViewText(R.id.title, "Incoming Call");
        contentView.setTextViewText(R.id.text, "Rajesh Sharma");
        contentView.setTextViewText(R.id.accept,"Accept");
        contentView.setTextViewText(R.id.decline,"Decline");
        PendingIntent pi=PendingIntent.getActivity(context,111,iNotify,PendingIntent.FLAG_MUTABLE);
        Notification notification = null;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notification = new Notification.Builder(context)
                    .setSmallIcon(R.drawable.notification_vector_assets)
                    .setContentIntent(pi)
                    .setAutoCancel(true)
                    .setCustomContentView(contentView)
                    .setChannelId(NOTIFICATION_ID)
                    .build();
            notificationManager.createNotificationChannel(new NotificationChannel(NOTIFICATION_ID, "New Notification", NotificationManager.IMPORTANCE_DEFAULT));

        } else {
            Toast.makeText(context, "Error in Notification", Toast.LENGTH_SHORT).show();
        }

        notificationManager.notify(111, notification);



//        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context)
//                .setSmallIcon(R.drawable.notification_vector_assets)
//                .setContent(contentView);
//
//        Notification notification = mBuilder.build();
//        notification.flags |= Notification.FLAG_AUTO_CANCEL;
//        notification.defaults |= Notification.DEFAULT_SOUND;
//        notification.defaults |= Notification.DEFAULT_VIBRATE;
//        notificationManager.notify(1, notification);


    }


}
