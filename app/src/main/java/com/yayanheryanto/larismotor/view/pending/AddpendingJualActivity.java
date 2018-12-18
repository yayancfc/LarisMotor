package com.yayanheryanto.larismotor.view.pending;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.yayanheryanto.larismotor.R;
import com.yayanheryanto.larismotor.view.LoginActivity;
import com.yayanheryanto.larismotor.model.Pending;
import com.yayanheryanto.larismotor.retrofit.ApiClient;
import com.yayanheryanto.larismotor.retrofit.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.yayanheryanto.larismotor.config.config.ACCESTOKEN;
import static com.yayanheryanto.larismotor.config.config.ID_USER;
import static com.yayanheryanto.larismotor.config.config.MY_PREFERENCES;

public class AddpendingJualActivity extends AppCompatActivity implements View.OnClickListener{


    private EditText txtNama, txtAlamat, txtNoTelepon, txtMerk, txtTipe, txtNoMesin, txtNoPolisi, txtTahun, txtHarga;
    private Button btnSave;
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_pending_jual);


        txtNama = findViewById(R.id.nama);
        txtAlamat = findViewById(R.id.alamat);
        txtNoTelepon = findViewById(R.id.telepon);
        txtMerk = findViewById(R.id.merk);
        txtTipe = findViewById(R.id.tipe);
        txtNoMesin = findViewById(R.id.no_mesin);
        txtNoPolisi = findViewById(R.id.no_polisi);
        txtTahun = findViewById(R.id.tahun);
        txtHarga = findViewById(R.id.harga);
        btnSave = findViewById(R.id.btnSave);


        btnSave.setOnClickListener(this);
        initProgressDialog();
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
        String merk = txtMerk.getText().toString();
        String tipe = txtTipe.getText().toString();
        String no_mesin = txtNoMesin.getText().toString();
        String no_polisi = txtNoPolisi.getText().toString();
        String tahun = txtTahun.getText().toString();
        String harga = txtHarga.getText().toString();

        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<Pending> call = apiInterface.addPendingJual(token, nama, alamat, telepon, merk, tipe, no_mesin, no_polisi, tahun, harga);
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
