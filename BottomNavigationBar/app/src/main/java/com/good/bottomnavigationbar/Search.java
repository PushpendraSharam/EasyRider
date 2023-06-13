package com.good.bottomnavigationbar;

import static android.content.Context.MODE_PRIVATE;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


public class Search extends Fragment {
Button button;
TextView name,email,pNumber,city;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.fragment_search, container, false);
        button=view.findViewById(R.id.editProfileButton);
        name=view.findViewById(R.id.name);
        email=view.findViewById(R.id.email);
        pNumber=view.findViewById(R.id.number);
        city=view.findViewById(R.id.city);
        SharedPreferences sp=this.getActivity().getSharedPreferences("Teacher Detail",MODE_PRIVATE);
        name.setText(sp.getString("T_name","No Data"));
        email.setText(sp.getString("T_email","No Data"));
        pNumber.setText(sp.getString("T_number","No Data"));
        city.setText(sp.getString("T_city","No Data"));




        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(),EditProfile.class));
            }
        });

        return  view;
    }
}