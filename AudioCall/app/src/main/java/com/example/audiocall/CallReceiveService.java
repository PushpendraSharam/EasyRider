package com.example.audiocall;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class CallReceiveService  extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference_2 = database.getReference("VoiceCall");
        databaseReference_2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Toast.makeText(CallReceiveService.this, "I am in Service", Toast.LENGTH_SHORT).show();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    CallModalClass modalClass = dataSnapshot.getValue(CallModalClass.class);
                    if (modalClass.getReciver().equals(FirebaseAuth.getInstance().getUid())) {
                        Intent intent = new Intent(CallReceiveService.this, MainActivity.class);
                        intent.putExtra("senderName", modalClass.getSenderName());
                        intent.putExtra("senderImage", modalClass.getSenderImage());
                        intent.putExtra("GoingWay", "direct");
                        startActivity(intent);

                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



        return START_STICKY;
    }

}
