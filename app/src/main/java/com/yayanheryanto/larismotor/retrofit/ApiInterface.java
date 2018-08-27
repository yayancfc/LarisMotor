package com.yayanheryanto.larismotor.retrofit;

import com.yayanheryanto.larismotor.model.IdUser;
import com.yayanheryanto.larismotor.model.MotorModel;
import com.yayanheryanto.larismotor.model.UserModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ApiInterface {

    @GET("api/getMotor")
    Call<List<MotorModel>> getMotor();

    @GET("api/getId")
    Call<List<UserModel>> getId();

    @FormUrlEncoded
    @POST("api/login")
    Call<UserModel> getUser(@Field("username") String username,
                            @Field("password") String password);
}
