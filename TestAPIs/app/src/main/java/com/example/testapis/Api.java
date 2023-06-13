package com.example.testapis;

import android.graphics.Bitmap;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface Api {
    @Multipart
    @POST("testAPI.php")
    Call<Modal> setData(
           @Part MultipartBody.Part image,
            @Part("name") RequestBody name,
            @Part("email") RequestBody email

    );

}
