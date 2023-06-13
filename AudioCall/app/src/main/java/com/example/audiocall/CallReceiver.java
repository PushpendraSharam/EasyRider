package com.example.audiocall;

import static android.content.Context.NOTIFICATION_SERVICE;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Handler;
import android.provider.Settings;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class CallReceiver extends BroadcastReceiver {
    public static String NOTIFICATION_ID = "notification-id";
    public static String NOTIFICATION = "notification";


    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("My Reciver", "I am Running");

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference_2 = database.getReference("VoiceCall");
        databaseReference_2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    CallModalClass modalClass = dataSnapshot.getValue(CallModalClass.class);
                    if (modalClass.getReciver().equals(FirebaseAuth.getInstance().getUid())) {
                        String senderName = modalClass.senderName;
                        RingToneCust.startMedia(context);
                        NotificationManager notificationManager = (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);
                        Intent iNotify = new Intent(context, Home.class);
                        iNotify.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        PendingIntent pi = PendingIntent.getActivity(context, 111, iNotify, PendingIntent.FLAG_MUTABLE);
                        Notification notification = null;

                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                            notification = new Notification.Builder(context)
                                    .setSmallIcon(R.drawable.notification_vector_assets)
                                    .setContentIntent(pi)
                                    .setAutoCancel(true)
                                    .setContentText(senderName)
                                    .setContentTitle("Incoming Call")
                                    .setChannelId(NOTIFICATION_ID)
                                    .build();
                            notificationManager.createNotificationChannel(new NotificationChannel(NOTIFICATION_ID, "New Notification", NotificationManager.IMPORTANCE_DEFAULT));

                        } else {
                            Toast.makeText(context, "Error in Notification", Toast.LENGTH_SHORT).show();
                        }
                        notificationManager.notify(111, notification);


                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

}
