package com.groceries.nectar_.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.groceries.nectar_.OrderFailed;
import com.groceries.nectar_.R;
import com.groceries.nectar_.RecycleViewAdapter.favoriteAdapter;

public class Favourite extends Fragment {
    RecyclerView recyclerView;
    LinearLayout linearLayout;
    Button button;
    int images[]={R.drawable.sprite,R.drawable.coka,R.drawable.orange,R.drawable.pepse,R.drawable.sprite,R.drawable.coka,R.drawable.orange,R.drawable.pepse};
    String name[]={"Sprite Can","Coca Cola","Orange Juice","Pepsi Can","Sprite Can","Coca Cola","Orange Juice","Pepsi Can"};
    String price[]={"$3.22","$4.23","$6.23","1.34","$3.22","$4.23","$6.23","1.34"};
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_favourite, container, false);
        recyclerView=view.findViewById(R.id.favoriteRecycleView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(new favoriteAdapter(images,name,price));
        linearLayout=view.findViewById(R.id.linearLayout_);
        button=view.findViewById(R.id.addAllCart);
        button.setVisibility(View.VISIBLE);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                button.setVisibility(View.GONE);
                getActivity().startActivity(new Intent(getActivity(), OrderFailed.class));
            }
        });
        return view;
    }

}