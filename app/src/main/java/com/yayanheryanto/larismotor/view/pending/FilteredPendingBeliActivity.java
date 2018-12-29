package com.yayanheryanto.larismotor.view.pending;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.yayanheryanto.larismotor.R;
import com.yayanheryanto.larismotor.adapter.SearchPendingBeliAdapter;
import com.yayanheryanto.larismotor.model.Merk;
import com.yayanheryanto.larismotor.model.PendingBeli;
import com.yayanheryanto.larismotor.model.Tipe;
import com.yayanheryanto.larismotor.retrofit.ApiClient;
import com.yayanheryanto.larismotor.retrofit.ApiInterface;
import com.yayanheryanto.larismotor.view.owner.MotorActivity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.yayanheryanto.larismotor.config.config.ID_USER;
import static com.yayanheryanto.larismotor.config.config.MY_PREFERENCES;

public class FilteredPendingBeliActivity extends AppCompatActivity {


    private RecyclerView recyclerView;
    private SearchPendingBeliAdapter adapter;
    private LinearLayoutManager layoutManager;
    private ProgressDialog dialog;

    private Spinner spinnerMerk;
    private Spinner spinnerTipe;

    private ArrayAdapter<String> merkAdapter;
    private ArrayAdapter<String> tipeAdapter;
    private List<Tipe> tipes;
    private int idTipe, idMerk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filtered_pending_beli);


        recyclerView = findViewById(R.id.rvPendingBeli);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        dialogueFilterPending();


    }

    private void dialogueFilterPending() {

        AlertDialog.Builder builder = new AlertDialog.Builder(FilteredPendingBeliActivity.this);
        View rootDialog = LayoutInflater.from(FilteredPendingBeliActivity.this).inflate(R.layout.dialogue_filter_pending, null);


        spinnerMerk = rootDialog.findViewById(R.id.spinner_merk);
        spinnerTipe = rootDialog.findViewById(R.id.spinner_tipe);

        getMerk();
        builder.setView(rootDialog);
        final AlertDialog dialog = builder.create();
        dialog.show();

        Button ok = rootDialog.findViewById(R.id.ok);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                setFilter();
            }
        });


    }

    private void setFilter() {


        String merk, tipe;


        if (spinnerMerk.getSelectedItemPosition() != 0) {
            merk = idMerk + "";
        } else {
            merk = "-1";
        }

        if (spinnerTipe.getSelectedItemPosition() != 0) {
            tipe = idTipe + "";
        } else {
            tipe = "-1";
        }


        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        SharedPreferences pref = getSharedPreferences(MY_PREFERENCES, Context.MODE_PRIVATE);
        String id = pref.getString(ID_USER, "");
        Call<List<PendingBeli>> call = apiInterface.getFilteredPendingBeli(id, merk, tipe);
        call.enqueue(new Callback<List<PendingBeli>>() {
            @Override
            public void onResponse(Call<List<PendingBeli>> call, Response<List<PendingBeli>> response) {

                List<PendingBeli> list = response.body();
                adapter = new SearchPendingBeliAdapter(getApplicationContext(), list);
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<List<PendingBeli>> call, Throwable t) {


            }
        });


    }

    private void getMerk() {

        final List<Merk> merks = new ArrayList<>();


        final ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<List<Merk>> call = apiInterface.getMerk();
        call.enqueue(new Callback<List<Merk>>() {
            @Override
            public void onResponse(Call<List<Merk>> call, Response<List<Merk>> response) {

                for (Merk merkNow : response.body()) {

                    merks.add(merkNow);

                }
                String[] merkArray = new String[merks.size() + 1];

                int i = 1;

                merkArray[0] = "Pilih Merk";

                for (Merk merkNow : merks) {

                    merkArray[i] = merkNow.getNamaMerk();
                    i++;
                }

                merkAdapter = new ArrayAdapter<>(FilteredPendingBeliActivity.this, android.R.layout.simple_spinner_item, merkArray);
                spinnerMerk.setAdapter(merkAdapter);


            }

            @Override
            public void onFailure(Call<List<Merk>> call, Throwable t) {

            }
        });

        spinnerMerk.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, final int position, long id) {

                tipes = new ArrayList<>();

                if (position == 0) {

                    String[] tipeArray = tipeArray = new String[1];

                    tipeArray[0] = "Silakan Pilih Merk Terlebih Dahulu";
                    tipeAdapter = new ArrayAdapter<>(FilteredPendingBeliActivity.this, android.R.layout.simple_spinner_item, tipeArray);
                    spinnerTipe.setAdapter(tipeAdapter);


                } else {
                    Call<List<Tipe>> call2 = apiInterface.getTipe(String.valueOf(merks.get(position - 1).getIdMerk()));
                    call2.enqueue(new Callback<List<Tipe>>() {
                        @Override
                        public void onResponse(Call<List<Tipe>> call, Response<List<Tipe>> response) {

                            for (Tipe tipeNow : response.body()) {

                                tipes.add(tipeNow);

                            }

                            int i = 1;

                            String[] tipeArray;

                            if (tipes.isEmpty()) {
                                tipeArray = new String[1];
                                tipeArray[0] = "Tipe tidak tersedia";

                            } else {


                                tipeArray = new String[tipes.size() + 1];
                                tipeArray[0] = "Pilih Tipe";

                                for (Tipe tipeNow : tipes) {

                                    tipeArray[i] = tipeNow.getNamaTipe();
                                    i++;
                                }
                            }
                            tipeAdapter = new ArrayAdapter<>(FilteredPendingBeliActivity.this, android.R.layout.simple_spinner_item, tipeArray);
                            spinnerTipe.setAdapter(tipeAdapter);
                            idMerk = merks.get(position - 1).getIdMerk();

                        }

                        @Override
                        public void onFailure(Call<List<Tipe>> call, Throwable t) {

                        }
                    });
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        spinnerTipe.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                if (tipes.size() != 0 && i != 0) {
                    idTipe = tipes.get(i - 1).getIdTipe();
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


    }


}
