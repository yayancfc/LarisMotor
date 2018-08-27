package com.yayanheryanto.larismotor;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.yayanheryanto.larismotor.adapter.MenuAdapter;
import com.yayanheryanto.larismotor.model.MenuModel;

import java.util.ArrayList;
import java.util.List;

import static com.yayanheryanto.larismotor.config.config.ACCESTOKEN;
import static com.yayanheryanto.larismotor.config.config.ID_USER;
import static com.yayanheryanto.larismotor.config.config.MY_PREFERENCES;

public class SalesMenuActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<MenuModel> list;
    private MenuAdapter adapter;
    private LinearLayoutManager layoutManager;
    private String id, token;
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales_menu);

        pref = PreferenceManager.getDefaultSharedPreferences(this);
        id = pref.getString(ID_USER, "");
        token = pref.getString(ACCESTOKEN, "");

        recyclerView = findViewById(R.id.recyclerview);
        list = new ArrayList<MenuModel>();
        list.add(new MenuModel(R.drawable.user, "Customer",MotorActivity.class));
        list.add(new MenuModel(R.drawable.motorbike, "Motor",MotorActivity.class));
        list.add(new MenuModel(R.drawable.transaction, "Transaksi",MotorActivity.class));
        list.add(new MenuModel(R.drawable.money, "Intensif",MotorActivity.class));

        recyclerView.setHasFixedSize(true);
        layoutManager = new GridLayoutManager(this,2);
        adapter = new MenuAdapter(this,list);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_sales, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.logout:
                pref = getSharedPreferences(MY_PREFERENCES, Context.MODE_PRIVATE);
                editor = pref.edit();
                editor.putString(ACCESTOKEN,"");
                editor.putString(ID_USER, "");
                editor.commit();
                Intent intent = new Intent(SalesMenuActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
