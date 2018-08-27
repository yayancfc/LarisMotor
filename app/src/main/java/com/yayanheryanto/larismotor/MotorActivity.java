package com.yayanheryanto.larismotor;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.yayanheryanto.larismotor.adapter.MotorAdapter;
import com.yayanheryanto.larismotor.model.MotorModel;
import com.yayanheryanto.larismotor.retrofit.ApiClient;
import com.yayanheryanto.larismotor.retrofit.ApiInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MotorActivity extends AppCompatActivity {


    private RecyclerView recyclerView;
    private MotorAdapter adapter;
    private LinearLayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_motor);

        recyclerView = findViewById(R.id.rvMotor);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        getMotor();
    }

    private void getMotor(){
        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<List<MotorModel>> call = apiInterface.getMotor();
        call.enqueue(new Callback<List<MotorModel>>() {
            @Override
            public void onResponse(Call<List<MotorModel>> call, Response<List<MotorModel>> response) {
                List<MotorModel> list = response.body();
                Log.d("Data", String.valueOf(list.get(0).getNoPolisi()));
                adapter = new MotorAdapter(getApplicationContext(),list);
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<List<MotorModel>> call, Throwable t) {
                Log.e("Error", t.getLocalizedMessage());
            }
        });
    }
}
