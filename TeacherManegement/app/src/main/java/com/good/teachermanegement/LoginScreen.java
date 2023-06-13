package com.good.teachermanegement;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.good.teachermanegement.databinding.ActivityLoginScreenBinding;

public class LoginScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_login_screen);
        getSupportActionBar().hide();
        ActivityLoginScreenBinding binding=DataBindingUtil.setContentView(this,R.layout.activity_login_screen);
        binding.loginWithGmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginScreen.this,LoginWithGmail.class));
            }

        });
        binding.loginWithMobile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginScreen.this,LoginWithMobileNumber.class));
            }
        });
//        binding.register.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//            startActivity(new Intent(LoginScreen.this,Register.class));
//            }
//        });
    }
}