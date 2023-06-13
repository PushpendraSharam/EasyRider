package com.example.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class CustomService extends Service {
    int i;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        i = 0;
        for (int i = 0; i < 10100000; i++)
            Toast.makeText(this, "This is a message " + String.valueOf(i), Toast.LENGTH_SHORT).show();

        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        i = 0;
        super.onDestroy();
    }
}
