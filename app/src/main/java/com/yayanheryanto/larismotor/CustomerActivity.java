package com.yayanheryanto.larismotor;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.yayanheryanto.larismotor.adapter.CustomerAdapter;
import com.yayanheryanto.larismotor.model.Customer;

import java.util.ArrayList;
import java.util.List;

public class CustomerActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<Customer> list;
    private CustomerAdapter adapter;
    private LinearLayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer);


        recyclerView = findViewById(R.id.recyclerview);
        list = new ArrayList<Customer>();

        list.add(new Customer("Yayan Heryanto", "08221122121"));
        list.add(new Customer("Ahmad Muklis", "087461231"));
        list.add(new Customer("Iqbal Hasan", "08221122121"));
        list.add(new Customer("Asep Saa", "083414532"));
        list.add(new Customer("Evan Dimas", "08377263221"));

        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        adapter = new CustomerAdapter(this,list);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }
}
