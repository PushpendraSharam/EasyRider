package com.example.audiocall;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.ArrayList;
import java.util.List;

public class Home extends AppCompatActivity {
    RecyclerView recyclerView;
    List<UserModal> list;
    private static final int NOTIFICATION_PERMISSION_CODE = 123;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        NotificationManager manager = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);
        manager.cancelAll();
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        Intent intent = new Intent(Home.this, CallReceiver.class);

        PendingIntent pi = PendingIntent.getBroadcast(Home.this, 100, intent, PendingIntent.FLAG_IMMUTABLE);
        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), 1000, pi);

        requestNotificationPermission();
        list = new ArrayList<>();
        Log.d("CurrentUID", FirebaseAuth.getInstance().getUid());

        recyclerView = findViewById(R.id.homeRecycleView);
        recyclerView.setLayoutManager(new LinearLayoutManager(Home.this));
        RecycleAdapter adapter = new RecycleAdapter(list, this);
        recyclerView.setAdapter(adapter);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = database.getReference("Users");
        DatabaseReference voiceCall = database.getReference("VoiceCall");
        DatabaseReference videoCall = database.getReference("VideoCall");
        videoCall.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for(DataSnapshot dataSnapshot:snapshot.getChildren())
                {
                    CallModalClass modalClass=dataSnapshot.getValue(CallModalClass.class);
                    if(modalClass.getReciver().equals(FirebaseAuth.getInstance().getUid()))
                    {
                        Intent intent = new Intent(Home.this, Video_Call_Activity.class);
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
        voiceCall.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    CallModalClass modalClass = dataSnapshot.getValue(CallModalClass.class);
                    if (modalClass.getReciver().equals(FirebaseAuth.getInstance().getUid())) {
                        Intent intent = new Intent(Home.this, MainActivity.class);
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
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    UserModal userModal = dataSnapshot.getValue(UserModal.class);
                    if (userModal.getId().equals(FirebaseAuth.getInstance().getUid())) {

                        SharedPreferences sharedPreferences = getSharedPreferences("UserDetails", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("currentUserName", userModal.getName());
                        editor.putString("currentUserImage", userModal.getImage());
                        editor.apply();

                    } else {
                        list.add(userModal);
                    }

                }
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.addUser) {
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(Home.this, Form.class));
            finish();
        }
        if (item.getItemId() == R.id.myProfileMenu) {
            startActivity(new Intent(Home.this, UserProfile.class));
        }


        return false;
    }

    private void requestNotificationPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_NOTIFICATION_POLICY) == PackageManager.PERMISSION_GRANTED)
            return;

        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_NOTIFICATION_POLICY)) {

        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.POST_NOTIFICATIONS}, NOTIFICATION_PERMISSION_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        // Checking the request code of our request
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == NOTIFICATION_PERMISSION_CODE) {

            // If permission is granted
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Displaying a toast
                //  Toast.makeText(this, "Permission granted now you can read the storage", Toast.LENGTH_LONG).show();
            } else {
                // Displaying another toast if permission is not granted
                requestNotificationPermission();
                Toast.makeText(this, "Oops you just denied the permission", Toast.LENGTH_LONG).show();
            }
        }
    }

}