package com.yayanheryanto.larismotor;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.yayanheryanto.larismotor.adapter.MenuAdapter;
import com.yayanheryanto.larismotor.model.MenuModel;

import java.util.ArrayList;
import java.util.List;

public class SalesMenuActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<MenuModel> list;
    private MenuAdapter adapter;
    private LinearLayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales_menu);

        recyclerView = findViewById(R.id.recyclerview);
        list = new ArrayList<MenuModel>();
        list.add(new MenuModel(R.drawable.user, "Customer",MotorActivity.class));
        list.add(new MenuModel(R.drawable.motorbike, "Motor",MotorActivity.class));
        list.add(new MenuModel(R.drawable.transaction, "Transaksi",MotorActivity.class));
        list.add(new MenuModel(R.drawable.money, "Intensif",MotorActivity.class));
        list.add(new MenuModel(R.drawable.user, "Logout",MotorActivity.class));

        recyclerView.setHasFixedSize(true);
        layoutManager = new GridLayoutManager(this,2);
        adapter = new MenuAdapter(this,list);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }
}
