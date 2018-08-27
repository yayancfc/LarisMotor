package com.yayanheryanto.larismotor.retrofit;

import com.yayanheryanto.larismotor.model.MotorModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    @GET("api/getMotor")
    Call<List<MotorModel>> getMotor();
}
