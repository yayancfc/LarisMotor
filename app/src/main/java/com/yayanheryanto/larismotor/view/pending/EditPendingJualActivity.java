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
import com.yayanheryanto.larismotor.model.PendingJual;
import com.yayanheryanto.larismotor.retrofit.ApiClient;
import com.yayanheryanto.larismotor.retrofit.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.yayanheryanto.larismotor.config.config.ACCESTOKEN;
import static com.yayanheryanto.larismotor.config.config.DATA_PENDING;
import static com.yayanheryanto.larismotor.config.config.ID_USER;
import static com.yayanheryanto.larismotor.config.config.MY_PREFERENCES;

public class EditPendingJualActivity extends AppCompatActivity implements View.OnClickListener{


    private EditText txtNama, txtAlamat, txtNoTelepon, txtMerk, txtTipe, txtNoMesin, txtNoPolisi, txtTahun, txtHarga;
    private Button btnSave;
    private ProgressDialog dialog;
    private PendingJual pending;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_pending_jual);

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
        getFromIntent();
    }

    private void getFromIntent() {

        Bundle data = getIntent().getExtras();
        pending = data.getParcelable(DATA_PENDING);

        txtNama.setText(pending.getNama());
        txtAlamat.setText(pending.getAlamat());
        txtNoTelepon.setText(pending.getNoTelp());
        txtMerk.setText(pending.getMerk());
        txtTipe.setText(pending.getTipe());
        txtNoPolisi.setText(pending.getNoPolisi());
        txtNoMesin.setText(pending.getNoMesin());
        txtTahun.setText(""+pending.getTahun());
        txtHarga.setText(""+pending.getHarga());
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
                UpdatePendingJual();
                break;
        }
    }

    private void UpdatePendingJual() {

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
        int id_pending = pending.getIdPending();

        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<Pending> call = apiInterface.updatePendingJual(token, id_pending, nama, alamat, telepon, merk, tipe, no_mesin, no_polisi, tahun, harga);
        call.enqueue(new Callback<Pending>() {
            @Override
            public void onResponse(Call<Pending> call, Response<Pending> response) {
                dialog.dismiss();
                if (response.body().getMessage().equals("success")){
                    Toast.makeText(EditPendingJualActivity.this, "Pending Jual Berhasil Diubah", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(EditPendingJualActivity.this, PendingTransaksiActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    finish();
                }else{
                    Toast.makeText(EditPendingJualActivity.this, "Token Tidak Valid, Silahkan Login", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(EditPendingJualActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
            }

            @Override
            public void onFailure(Call<Pending> call, Throwable t) {
                dialog.dismiss();
                t.printStackTrace();
                Toast.makeText(EditPendingJualActivity.this, "Terjadi Kesalahan Tidak Terduga", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
