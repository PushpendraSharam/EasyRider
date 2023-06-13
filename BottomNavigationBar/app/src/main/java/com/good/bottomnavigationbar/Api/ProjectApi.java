package com.good.bottomnavigationbar.Api;

import com.good.bottomnavigationbar.ModalClass.ModalClassForBatches;
import com.good.bottomnavigationbar.ModalClass.UpdateModalClass;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ProjectApi {
    @FormUrlEncoded
    @POST("batchesusingteacherid.php")
    Call<ModalClassForBatches>getBatches(
            @Field("tid") String tid
    );
    @FormUrlEncoded
    @POST("updateprofile.php")
    Call<UpdateModalClass> setUpdate(
            @Field("tid") String tid,
            @Field("tlocation") String tlocation,
            @Field("tnumber") String tnumber,
            @Field("tname") String tname
    );
}
