package com.good.teachermanegement;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import com.good.teachermanegement.databinding.ActivitySplashScreenBinding;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      //  setContentView(R.layout.activity_splash_screen);

        ActivitySplashScreenBinding binding= DataBindingUtil.setContentView(SplashScreen.this,R.layout.activity_splash_screen);
        getSupportActionBar().hide();
        Handler splash=new Handler();
          splash.postDelayed(new Runnable() {
              @Override
              public void run() {
//                  SharedPreferences sp=getSharedPreferences("login",MODE_PRIVATE);
//                  Boolean check=sp.getBoolean("key",false);
//                  if(check)
//                  {
//                   startActivity(new Intent(SplashScreen.this,MainActivity.class));
//                  }
//                  else
//                  {
//                      startActivity(new Intent(SplashScreen.this,LoginScreen.class));
//                  }


                  startActivity(new Intent(SplashScreen.this,LoginScreen.class));
              }
          },2000);
    }
}