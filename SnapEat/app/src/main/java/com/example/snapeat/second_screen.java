package com.example.snapeat;

import static com.example.snapeat.StatusBarColor.toggleColorStatusBar;
import static com.example.snapeat.StatusBarColor.transparentStatusBar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

public class second_screen extends AppCompatActivity {
TextView tv_Eat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_screen);
        toggleColorStatusBar(second_screen.this);
        transparentStatusBar(getWindow());

        tv_Eat=findViewById(R.id.tv_Eat);
        Shader shader = new LinearGradient(0,0,0,tv_Eat.getLineHeight(),
                getResources().getColor(R.color.eat_start_color), getResources().getColor(R.color.eat_end_color), Shader.TileMode.REPEAT);
        tv_Eat.getPaint().setShader(shader);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(second_screen.this,Intro_3.class));
                overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                finishAffinity();
            }
        },1200);
    }
}