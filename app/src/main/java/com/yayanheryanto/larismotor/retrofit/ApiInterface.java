package com.yayanheryanto.larismotor.retrofit;

import com.yayanheryanto.larismotor.model.Merk;
import com.yayanheryanto.larismotor.model.Motor;
import com.yayanheryanto.larismotor.model.Tipe;
import com.yayanheryanto.larismotor.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("api/getMotor")
    Call<List<Motor>> getMotor();

    @GET("api/getId")
    Call<List<User>> getId();


    @GET("api/getMerk")
    Call<List<Merk>> getMerk();

    @GET("api/getTipe/{id_merk}")
    Call<List<Tipe>> getTipe(@Query("id_merk") String id_merk);

    @FormUrlEncoded
    @POST("api/login")
    Call<User> getUser(@Field("username") String username,
                       @Field("password") String password);
}
