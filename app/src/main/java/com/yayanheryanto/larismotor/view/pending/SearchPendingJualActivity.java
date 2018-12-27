package com.yayanheryanto.larismotor.view.pending;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.yayanheryanto.larismotor.R;
import com.yayanheryanto.larismotor.adapter.SearchPendingJualAdapter;
import com.yayanheryanto.larismotor.model.PendingJual;
import com.yayanheryanto.larismotor.retrofit.ApiClient;
import com.yayanheryanto.larismotor.retrofit.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.yayanheryanto.larismotor.config.config.ID_USER;
import static com.yayanheryanto.larismotor.config.config.MY_PREFERENCES;

public class SearchPendingJualActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private SearchPendingJualAdapter adapter;
    private LinearLayoutManager layoutManager;
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_pending_jual);
        initProgressDialog();
        recyclerView = findViewById(R.id.rvPendingJual);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
    }

    private void getPendingJual(String nama) {
        dialog.show();
        SharedPreferences pref = getSharedPreferences(MY_PREFERENCES, Context.MODE_PRIVATE);
        String id = pref.getString(ID_USER, "");
        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<List<PendingJual>> call = apiInterface.searchPendingJual(id,nama);
        call.enqueue(new Callback<List<PendingJual>>() {
            @Override
            public void onResponse(Call<List<PendingJual>> call, Response<List<PendingJual>> response) {
                dialog.dismiss();
                if (response.body().isEmpty()){
                    Toast.makeText(SearchPendingJualActivity.this, "Data Tidak Ditemukan", Toast.LENGTH_SHORT).show();
                }else {
                    List<PendingJual> list = response.body();

                    adapter = new SearchPendingJualAdapter(getApplicationContext(), list);
                    recyclerView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<List<PendingJual>> call, Throwable t) {
                dialog.dismiss();
                t.printStackTrace();
                Toast.makeText(getApplicationContext(), "Terjadi Kesalahan Tidak Terduga", Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void initProgressDialog() {
        dialog = new ProgressDialog(this);
        dialog.setTitle("Loading");
        dialog.setMessage("Sedang Memproses..");
        dialog.setCancelable(false);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_cari_act, menu);

        MenuItem searchMenu = menu.findItem(R.id.action_search_act);
        SearchView searchView = (SearchView)searchMenu.getActionView();
        searchView.setQueryHint("Masukan Nama Penjual");
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
                    getPendingJual(newText);
                }

                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

}
