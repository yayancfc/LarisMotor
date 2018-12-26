package com.yayanheryanto.larismotor.view.pending;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.yayanheryanto.larismotor.R;
import com.yayanheryanto.larismotor.model.Merk;
import com.yayanheryanto.larismotor.model.Pending;
import com.yayanheryanto.larismotor.model.Tipe;
import com.yayanheryanto.larismotor.retrofit.ApiClient;
import com.yayanheryanto.larismotor.retrofit.ApiInterface;
import com.yayanheryanto.larismotor.view.LoginActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.yayanheryanto.larismotor.config.config.ACCESTOKEN;
import static com.yayanheryanto.larismotor.config.config.DEBUG;
import static com.yayanheryanto.larismotor.config.config.ID_USER;
import static com.yayanheryanto.larismotor.config.config.MY_PREFERENCES;

public class AddpendingJualActivity extends AppCompatActivity implements View.OnClickListener{


    private EditText txtNama, txtAlamat, txtNoTelepon, txtNoMesin, txtNoPolisi, txtTahun, txtHarga;
    private Button btnSave;
    private ProgressDialog dialog;
    private int merkMotor, tipeMotor;
    private List<Merk> merk;
    private List<Tipe> tipe;
    private ArrayAdapter<String> adapter, adapter2;
    private Spinner spinnerMerk, spinnerTipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_pending_jual);


        txtNama = findViewById(R.id.nama);
        txtAlamat = findViewById(R.id.alamat);
        txtNoTelepon = findViewById(R.id.telepon);
        txtNoMesin = findViewById(R.id.no_mesin);
        txtNoPolisi = findViewById(R.id.no_polisi);
        txtTahun = findViewById(R.id.tahun);
        txtHarga = findViewById(R.id.harga);
        btnSave = findViewById(R.id.btnSave);

        spinnerMerk = findViewById(R.id.spinner1);
        spinnerTipe = findViewById(R.id.spinner2);

        initProgressDialog();
        getMerk();

        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line);
        adapter2 = new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line);
        spinnerMerk.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                getTipe(String.valueOf(merk.get(i).getIdMerk()));
                merkMotor = merk.get(i).getIdMerk();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        spinnerTipe.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                tipeMotor = tipe.get(i).getIdTipe();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        btnSave.setOnClickListener(this);
    }

    private void getMerk() {
        dialog.show();
        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<List<Merk>> call = apiInterface.getMerk();
        call.enqueue(new Callback<List<Merk>>() {
            @Override
            public void onResponse(Call<List<Merk>> call, Response<List<Merk>> response) {
                dialog.dismiss();
                Log.d(DEBUG, String.valueOf(response.body().size()));
                merk = response.body();
                if (merk != null) {
                    for (Merk merkMotor : merk){
                        Log.d(DEBUG, merkMotor.getNamaMerk());
                        adapter.add(merkMotor.getNamaMerk()) ;
                    }

                    spinnerMerk.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<List<Merk>> call, Throwable t) {
                dialog.dismiss();
                t.printStackTrace();
                Toast.makeText(AddpendingJualActivity.this, "Terjadi Kesalahan Tidak Terduga", Toast.LENGTH_SHORT).show();
            }


        });
    }

    private void getTipe(String idMerk) {
        dialog.show();
        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<List<Tipe>> call = apiInterface.getTipe(idMerk);
        call.enqueue(new Callback<List<Tipe>>() {
            @Override
            public void onResponse(Call<List<Tipe>> call, Response<List<Tipe>> response) {
                dialog.dismiss();
                tipe = response.body();
                if (tipe != null) {
                    adapter2.clear();
                    for (Tipe tipeMotor : tipe){
                        Log.d(DEBUG, tipeMotor.getNamaTipe());
                        adapter2.add(tipeMotor.getNamaTipe());
                    }

                    spinnerTipe.setAdapter(adapter2);
                }
            }

            @Override
            public void onFailure(Call<List<Tipe>> call, Throwable t) {
                dialog.dismiss();
                t.printStackTrace();
                Toast.makeText(AddpendingJualActivity.this, "Terjadi Kesalahan Tidak Terduga", Toast.LENGTH_SHORT).show();
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
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnSave:
                savePendingJual();
                break;
        }
    }

    private void savePendingJual() {

        dialog.show();
        SharedPreferences pref = getSharedPreferences(MY_PREFERENCES, Context.MODE_PRIVATE);
        String id = pref.getString(ID_USER, "");
        String token = pref.getString(ACCESTOKEN, "");

        String nama = txtNama.getText().toString();
        String alamat = txtAlamat.getText().toString();
        String telepon = txtNoTelepon.getText().toString();
        String no_mesin = txtNoMesin.getText().toString();
        String no_polisi = txtNoPolisi.getText().toString();
        String tahun = txtTahun.getText().toString();
        String harga = txtHarga.getText().toString();

        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<Pending> call = apiInterface.addPendingJual(token, nama, alamat, telepon, merkMotor, tipeMotor, no_mesin, no_polisi, tahun, harga);
        call.enqueue(new Callback<Pending>() {
            @Override
            public void onResponse(Call<Pending> call, Response<Pending> response) {
                dialog.dismiss();
                if (response.body().getMessage().equals("success")){
                    Toast.makeText(AddpendingJualActivity.this, "Pending Jual Berhasil Ditambah", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(AddpendingJualActivity.this, PendingTransaksiActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    finish();
                }else{
                    Toast.makeText(AddpendingJualActivity.this, "Token Tidak Valid, Silahkan Login", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(AddpendingJualActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
            }

            @Override
            public void onFailure(Call<Pending> call, Throwable t) {
                dialog.dismiss();
                t.printStackTrace();
                Toast.makeText(AddpendingJualActivity.this, "Terjadi Kesalahan Tidak Terduga", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
