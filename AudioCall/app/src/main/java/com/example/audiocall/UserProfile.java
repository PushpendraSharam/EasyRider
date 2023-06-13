package com.example.audiocall;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

public class UserProfile extends AppCompatActivity {
    ImageView profileImageView;
    TextView tvName, tvEmail;
    //TextView textView;
    String start = "https://firebasestorage.googleapis.com/v0/b/fir-tutorial-d6331.appspot.com/o/";
    String end = "?alt=media&token=c02d3248-4c89-49f9-b7a8-4e4e3c07ec86";
    private int seconds = 0;
    private boolean running;
    private boolean wasRunning;
    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        profileImageView = findViewById(R.id.profileImageView);
        tvName = findViewById(R.id.profileUserName);
        tvEmail = findViewById(R.id.profileEmailAddress);


        getSupportActionBar().setTitle("My Profile");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference().child("Users");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    UserModal userModal = dataSnapshot.getValue(UserModal.class);
                    if (userModal.getId().equals(FirebaseAuth.getInstance().getUid())) {
                        tvName.setText(userModal.getName());
                        tvEmail.setText(userModal.getEmail());
                        Picasso.get().load((start + userModal.getImage() + end).trim()).into(profileImageView);
                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }



    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}