package com.example.testapis;




import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Controller {
    public  static String url="https://servicesontap.co.in/APIs/";
    public static Retrofit retrofit;
    public static Controller client;
    public Controller()
    {
        retrofit=new Retrofit.Builder().baseUrl(url).addConverterFactory(GsonConverterFactory.create()).build();

    }
    public static synchronized Controller getInstance()
    {
        if(client==null)
            client=new Controller();
        return client;
    }

    public   Api getApi()
    {
        return  retrofit.create(Api.class);
    }

}
