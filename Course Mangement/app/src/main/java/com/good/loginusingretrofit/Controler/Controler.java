package com.good.loginusingretrofit.Controler;

import com.good.loginusingretrofit.api.RetrofitApi;
import com.good.loginusingretrofit.models.ModalClass;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Controler {
    public  static String url="https://cafe.itcodehub.com/api/";
    public static Retrofit retrofit;
    public static Controler client;
    public Controler()
    {
        retrofit=new Retrofit.Builder().baseUrl(url).addConverterFactory(GsonConverterFactory.create()).build();

    }
    public static synchronized Controler getInstance()
    {
        if(client==null)
            client=new Controler();
        return client;
    }

   public   RetrofitApi getApi()
     {
         return  retrofit.create(RetrofitApi.class);
     }

}
