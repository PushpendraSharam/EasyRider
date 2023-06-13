package com.groceries.nectar_;

import static com.groceries.nectar_.StatusBarColor.toggleColorStatusBar;
import static com.groceries.nectar_.StatusBarColor.transparentStatusBar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class OrderSuccessful extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_successful);
        toggleColorStatusBar(OrderSuccessful.this);
        transparentStatusBar(getWindow());

    }
}