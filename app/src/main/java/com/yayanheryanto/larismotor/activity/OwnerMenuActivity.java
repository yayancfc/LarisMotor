package com.yayanheryanto.larismotor.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TextView;

import com.yayanheryanto.larismotor.R;
import com.yayanheryanto.larismotor.adapter.MenuAdapter;
import com.yayanheryanto.larismotor.model.Menu;
import com.yayanheryanto.larismotor.model.Motor;
import com.yayanheryanto.larismotor.model.User;

import java.util.ArrayList;
import java.util.List;

import static com.yayanheryanto.larismotor.config.config.ACCESTOKEN;
import static com.yayanheryanto.larismotor.config.config.DATA_MOTOR;
import static com.yayanheryanto.larismotor.config.config.ID_USER;
import static com.yayanheryanto.larismotor.config.config.MY_PREFERENCES;
import static com.yayanheryanto.larismotor.config.config.NAMA_USER;
import static com.yayanheryanto.larismotor.config.config.USER_DATA;

public class OwnerMenuActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<Menu> list;
    private MenuAdapter adapter;
    private LinearLayoutManager layoutManager;
    private String id, token;
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private TextView txName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_menu);


        pref = getSharedPreferences(MY_PREFERENCES, Context.MODE_PRIVATE);
        String id = pref.getString(ID_USER, "");
        token = pref.getString(ACCESTOKEN, "");
        String namaUser = pref.getString(NAMA_USER, "");
        //Toast.makeText(this, token, Toast.LENGTH_SHORT).show();

        txName = findViewById(R.id.txName);

        txName.setText(namaUser);

        ScrollView scrollView = findViewById(R.id.scrollView);
        scrollView.setFocusableInTouchMode(true);
        scrollView.setDescendantFocusability(ViewGroup.FOCUS_BEFORE_DESCENDANTS);

        recyclerView = findViewById(R.id.recyclerview);
        list = new ArrayList<Menu>();
        list.add(new Menu(R.drawable.user, "Customer",CustomerActivity.class));
        list.add(new Menu(R.drawable.motorbike, "Motor", MotorActivity.class));
        list.add(new Menu(R.drawable.transaction, "Transaksi",MotorActivity.class));
        list.add(new Menu(R.drawable.money, "Atur Intensif",MotorActivity.class));
        list.add(new Menu(R.drawable.report, "Laporan Penjualan",MotorActivity.class));
        list.add(new Menu(R.drawable.chat, "SMS Gateway",MotorActivity.class));
        list.add(new Menu(R.drawable.user, "Master",MotorActivity.class));
        list.add(new Menu(R.drawable.user, "Sales",MotorActivity.class));

        recyclerView.setHasFixedSize(true);
        layoutManager = new GridLayoutManager(this,2);
        adapter = new MenuAdapter(this,list);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

    }

    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        getMenuInflater().inflate(R.menu.menu_owner, menu);

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
                editor.putString(NAMA_USER, "");
                editor.commit();
                Intent intent = new Intent(OwnerMenuActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
