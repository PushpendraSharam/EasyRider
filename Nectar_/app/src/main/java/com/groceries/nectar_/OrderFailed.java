package com.groceries.nectar_;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.groceries.nectar_.Fragments.Favourite;

public class OrderFailed extends AppCompatActivity {
ImageView backButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_failed);
        backButton=findViewById(R.id.closeButtonOrderFailed);
        DisplayMetrics dm=new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width=dm.widthPixels;
        int height=dm.heightPixels;
        getWindow().setLayout((int)(width*.85),(int)(height*.75));
        WindowManager.LayoutParams params=getWindow().getAttributes();
        params.gravity=Gravity.CENTER;
        params.x=0;
        params.y=-20;
        getWindow().setAttributes(params);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

    }


    @Override
    public void onBackPressed() {
         super.onBackPressed();
      startActivity(new Intent(OrderFailed.this,MainActivity.class));
      finish();
    }
}