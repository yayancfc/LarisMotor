package com.yayanheryanto.larismotor.view.owner;

import android.app.ProgressDialog;
import android.app.SearchManager;
import android.content.Context;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.yayanheryanto.larismotor.R;
import com.yayanheryanto.larismotor.adapter.CustomerAdapter;
import com.yayanheryanto.larismotor.model.Customer;
import com.yayanheryanto.larismotor.retrofit.ApiClient;
import com.yayanheryanto.larismotor.retrofit.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CariCustomerActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private CustomerAdapter adapter;
    private LinearLayoutManager layoutManager;
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cari_customer);
        initProgressDialog();
        recyclerView = findViewById(R.id.recyclerview);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
    }

    private void initProgressDialog() {
        dialog = new ProgressDialog(this);
        dialog.setTitle("Loading");
        dialog.setMessage("Sedang Memproses..");
        dialog.setCancelable(false);
    }

    private void getCustomer(String nama){
        dialog.show();
        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<List<Customer>> call = apiInterface.searchCustomer(nama);
        call.enqueue(new Callback<List<Customer>>() {
            @Override
            public void onResponse(Call<List<Customer>> call, Response<List<Customer>> response) {
                dialog.dismiss();
                if (response.body().isEmpty()){
                    Toast.makeText(CariCustomerActivity.this, "Data Tidak Ditemukan", Toast.LENGTH_SHORT).show();
                }else {
                    List<Customer> list = response.body();

                    adapter = new CustomerAdapter(getApplicationContext(), list);
                    recyclerView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                }

            }

            @Override
            public void onFailure(Call<List<Customer>> call, Throwable t) {
                dialog.dismiss();
                t.printStackTrace();
                Toast.makeText(CariCustomerActivity.this, "Terjadi Kesalahan Tidak Terduga", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_cari_act, menu);

        MenuItem searchMenu = menu.findItem(R.id.action_search_act);
        SearchView searchView = (SearchView)searchMenu.getActionView();
        searchView.setQueryHint("Masukan Nama Customer");
        searchView.setIconified(false);
        searchMenu.expandActionView();
        searchView.setQuery("",false);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (TextUtils.getTrimmedLength(newText) > 0) {
                    getCustomer(newText);
                }

                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }
}
