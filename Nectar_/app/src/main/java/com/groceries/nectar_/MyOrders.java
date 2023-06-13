package com.groceries.nectar_;

import static com.groceries.nectar_.StatusBarColor.toggleColorStatusBar;
import static com.groceries.nectar_.StatusBarColor.transparentStatusBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.groceries.nectar_.RecycleViewAdapter.MyOrderAdpters;

public class MyOrders extends AppCompatActivity {
     RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_orders);
        toggleColorStatusBar(MyOrders.this);
        transparentStatusBar(getWindow());
        recyclerView=findViewById(R.id.myOrdersRecycleView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new MyOrderAdpters());
    }
}