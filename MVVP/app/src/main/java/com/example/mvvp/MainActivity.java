package com.example.mvvp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;

import com.example.mvvp.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
List<ModalClass> list;
    ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //     setContentView(R.layout.activity_main);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.recycleViewMain.setLayoutManager(new LinearLayoutManager(this));
        list = new ArrayList<>();
        list.add(new ModalClass("Donut", "1.1"));
        list.add(new ModalClass("Eclair", "2.1"));
        list.add(new ModalClass("Froyo", "3.1"));
        list.add(new ModalClass("Gingerbread", "4.1"));
        list.add(new ModalClass("Honeycomb", "5.1"));
        binding.recycleViewMain.setAdapter(new NewAdapter(list));

    }
}