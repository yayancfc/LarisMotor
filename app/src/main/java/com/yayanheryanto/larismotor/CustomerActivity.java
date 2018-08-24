package com.yayanheryanto.larismotor;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.yayanheryanto.larismotor.adapter.CustomerAdapter;
import com.yayanheryanto.larismotor.adapter.MenuAdapter;
import com.yayanheryanto.larismotor.model.CustomerModel;
import com.yayanheryanto.larismotor.model.MenuModel;

import java.util.ArrayList;
import java.util.List;

public class CustomerActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<CustomerModel> list;
    private CustomerAdapter adapter;
    private LinearLayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer);


        recyclerView = findViewById(R.id.recyclerview);
        list = new ArrayList<CustomerModel>();

        list.add(new CustomerModel("Yayan Heryanto", "08221122121"));
        list.add(new CustomerModel("Ahmad Muklis", "087461231"));
        list.add(new CustomerModel("Iqbal Hasan", "08221122121"));
        list.add(new CustomerModel("Asep Saa", "083414532"));
        list.add(new CustomerModel("Evan Dimas", "08377263221"));

        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        adapter = new CustomerAdapter(this,list);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }
}
