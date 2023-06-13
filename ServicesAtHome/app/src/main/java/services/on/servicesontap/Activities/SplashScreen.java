package services.on.servicesontap.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import services.on.servicesontap.R;

public class SplashScreen extends AppCompatActivity {

    boolean isUserLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        SharedPreferences sharedPreferences=getSharedPreferences("isUserLOGOUT",MODE_PRIVATE);
        isUserLogin=sharedPreferences.getBoolean("isUserLOGOUT_",true);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(isUserLogin)
                {
                    startActivity(new Intent(SplashScreen.this,ChooseOptions.class));
                    finish();

                }
                else
                {
                    startActivity(new Intent(SplashScreen.this,MainActivity.class));
                    finish();

                }
            }
        },2000);

    }
}