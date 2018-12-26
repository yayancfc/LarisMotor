package com.yayanheryanto.larismotor.view.pending;

import android.app.ProgressDialog;
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
import com.yayanheryanto.larismotor.adapter.SearchPendingBeliAdapter;
import com.yayanheryanto.larismotor.model.Pending;
import com.yayanheryanto.larismotor.retrofit.ApiClient;
import com.yayanheryanto.larismotor.retrofit.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchPendingBeliActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private SearchPendingBeliAdapter adapter;
    private LinearLayoutManager layoutManager;
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_pending_beli);

        initProgressDialog();
        recyclerView = findViewById(R.id.rvPendingBeli);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_cari_act, menu);

        MenuItem searchMenu = menu.findItem(R.id.action_search_act);
        SearchView searchView = (SearchView) searchMenu.getActionView();
        searchView.setQueryHint("Masukan Nama Pembeli");
        searchView.setIconified(false);
        searchMenu.expandActionView();
        searchView.setQuery("", false);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (TextUtils.getTrimmedLength(newText) > 0) {
                    getPendingBeli(newText);
                }

                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    private void getPendingBeli(String nama) {
        dialog.show();
        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<List<Pending>> call = apiInterface.searchPendingBeli(nama);
        call.enqueue(new Callback<List<Pending>>() {
            @Override
            public void onResponse(Call<List<Pending>> call, Response<List<Pending>> response) {
                dialog.dismiss();
                if (response.body().isEmpty()) {
                    Toast.makeText(SearchPendingBeliActivity.this, "Data Tidak Ditemukan", Toast.LENGTH_SHORT).show();
                } else {
                    List<Pending> list = response.body();

                    adapter = new SearchPendingBeliAdapter(getApplicationContext(), list);
                    recyclerView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<List<Pending>> call, Throwable t) {
                dialog.dismiss();
                t.printStackTrace();
                Toast.makeText(SearchPendingBeliActivity.this, "Terjadi Kesalahan Tidak Terduga", Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void initProgressDialog() {
        dialog = new ProgressDialog(this);
        dialog.setTitle("Loading");
        dialog.setMessage("Sedang Memproses..");
        dialog.setCancelable(false);
    }
}
