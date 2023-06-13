package com.mvvm.practice_mvvp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.widget.Toast;

import com.mvvm.practice_mvvp.APIs.RetrofitApi;
import com.mvvm.practice_mvvp.modalclass.DataModal;
import com.mvvm.practice_mvvp.databinding.ActivityMainBinding;
import com.mvvm.practice_mvvp.modalclass.ResponseClass;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    String url="https://www.boredapi.com/api/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_main);
        binding.recycleView.setLayoutManager(new LinearLayoutManager(this));
        List<ResponseClass> dataModalList=new ArrayList<>();
//        dataModalList.add(new DataModal("Marshmallow","6.0"));
//        dataModalList.add(new DataModal("Nougat","7.0"));
//        dataModalList.add(new DataModal("Oreo","8.0"));
//        dataModalList.add(new DataModal("Pie","9.0"));
//        dataModalList.add(new DataModal("Quince Tart","10.0"));
//        dataModalList.add(new DataModal("Red Velvet Cake","11.0"));

        Adapter adapter=new Adapter(MainActivity.this,dataModalList);

      //  binding.recycleView.setAdapter(adapter);
        Retrofit retrofit=new Retrofit.Builder().baseUrl(url).addConverterFactory(GsonConverterFactory.create()).build();
       // Call<ResponseClass> call= (Call<ResponseClass>) retrofit.create(RetrofitApi.class);
        RetrofitApi retrofitApi=retrofit.create(RetrofitApi.class);
        Call<ResponseClass> call1 =retrofitApi.getData();
        call1.enqueue(new Callback<ResponseClass>() {
            @Override
            public void onResponse(Call<ResponseClass> call, Response<ResponseClass> response) {
                ResponseClass obj=response.body();
                List<ResponseClass> dataModalList=new ArrayList<>();
                assert obj != null;
                dataModalList.add(new ResponseClass(obj.getActivity(),obj.getType(),obj.getKey(),obj.getLink()));
                Adapter adapter=new Adapter(MainActivity.this,dataModalList);
                  binding.recycleView.setAdapter(adapter);


            }

            @Override
            public void onFailure(Call<ResponseClass> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Failed", Toast.LENGTH_SHORT).show();

            }
        });


    }
}