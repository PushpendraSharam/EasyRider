package com.groceries.nectar_;

import static com.groceries.nectar_.StatusBarColor.toggleColorStatusBar;
import static com.groceries.nectar_.StatusBarColor.transparentStatusBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.groceries.nectar_.RecycleViewAdapter.ExclusiveOffers;
import com.groceries.nectar_.RecycleViewAdapter.ExploreSubPartAdapter;

public class ExploreSubPart extends AppCompatActivity {
RecyclerView recyclerView;
ImageView filterProducts,backButton;
int images[]={R.drawable.coka,R.drawable.sprite,R.drawable.orange,R.drawable.pepse,R.drawable.coka,R.drawable.sprite,R.drawable.orange,R.drawable.pepse};
String name[]={"Coca Cola","Sprite Can","Orange juice","Pepsi Can","Coca Cola","Sprite Can","Orange juice","Pepsi Can"};
String price[]={"$1.32","$0.92","$1.54","$1.21","$1.32","$0.92","$1.54","$1.21"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explore_sub_part);
        toggleColorStatusBar(ExploreSubPart.this);
        transparentStatusBar(getWindow());
        recyclerView=findViewById(R.id.exploreSubPartRecycleView);
        filterProducts=findViewById(R.id.filterProducts);
        backButton=findViewById(R.id.subPartBackButton);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        recyclerView.setAdapter(new ExploreSubPartAdapter(images,name,price,getApplicationContext()));
        filterProducts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ExploreSubPart.this,FilterActivity.class));
            }
        });
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }
}