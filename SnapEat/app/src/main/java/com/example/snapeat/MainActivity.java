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

public class MainActivity extends AppCompatActivity {
TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toggleColorStatusBar(MainActivity.this);
        transparentStatusBar(getWindow());
        textView=findViewById(R.id.tv_snapEat);
        Shader shader = new LinearGradient(0,0,0,textView.getLineHeight(),
                getResources().getColor(R.color.startColor), getResources().getColor(R.color.endColor), Shader.TileMode.MIRROR);
        textView.getPaint().setShader(shader);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
//                startActivity(new Intent(MainActivity.this,second_screen.class));
//                overridePendingTransition(R.anim.fadein, R.anim.fadeout);
//                finishAffinity();
            }
        },1200);

    }
}