package com.yayanheryanto.larismotor;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.yayanheryanto.larismotor.adapter.MotorAdapter;
import com.yayanheryanto.larismotor.model.MotorModel;

import java.util.ArrayList;
import java.util.List;

public class MotorActivity extends AppCompatActivity {


    private RecyclerView recyclerView;
    private List<MotorModel> list;
    private MotorAdapter adapter;
    private LinearLayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_motor);

        recyclerView = findViewById(R.id.recyclerview);
        list = new ArrayList<MotorModel>();
        list.add(new MotorModel(R.drawable.motorbike,"D 1232 FV", "20.000.000"));
        list.add(new MotorModel(R.drawable.motorbike,"D 1232 FV", "20.000.000"));
        list.add(new MotorModel(R.drawable.motorbike,"D 1232 FV", "20.000.000"));

        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        adapter = new MotorAdapter(this,list);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }
}
