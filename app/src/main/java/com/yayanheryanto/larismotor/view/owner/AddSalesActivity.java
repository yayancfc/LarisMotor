package com.yayanheryanto.larismotor.view.owner;

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
import com.yayanheryanto.larismotor.model.Sales;
import com.yayanheryanto.larismotor.retrofit.ApiClient;
import com.yayanheryanto.larismotor.retrofit.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.yayanheryanto.larismotor.config.config.ACCESTOKEN;
import static com.yayanheryanto.larismotor.config.config.ID_USER;
import static com.yayanheryanto.larismotor.config.config.MY_PREFERENCES;

public class AddSalesActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText etUsername, etPassword, etNama, etAlamat, etNoWa, etKtp;
    private Button btnSave;
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_sales);

        etNama = findViewById(R.id.namaSales);
        etAlamat = findViewById(R.id.alamat);
        etKtp = findViewById(R.id.noKtp);
        etNoWa = findViewById(R.id.noWa);
        etUsername = findViewById(R.id.username);
        etPassword = findViewById(R.id.password);
        btnSave = findViewById(R.id.btnSave);
        btnSave.setOnClickListener(this);

        initProgressDialog();
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnSave :
                saveSales();
                break;
        }
    }

    private void initProgressDialog() {
        dialog = new ProgressDialog(this);
        dialog.setTitle("Loading");
        dialog.setMessage("Sedang Memproses..");
        dialog.setCancelable(false);
    }

    private void saveSales() {
        dialog.show();
        SharedPreferences pref = getSharedPreferences(MY_PREFERENCES, Context.MODE_PRIVATE);
        String id = pref.getString(ID_USER, "");
        String token = pref.getString(ACCESTOKEN, "");

        String namaSales = etNama.getText().toString();
        String usernameSales = etUsername.getText().toString();
        String passwordSales = etPassword.getText().toString();
        String alamatSales = etAlamat.getText().toString();
        String noWaSales = etNoWa.getText().toString();
        String noKtpSales = etKtp.getText().toString();

        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<Sales> call = apiInterface.addSales(token, usernameSales, passwordSales, namaSales , alamatSales, noWaSales, noKtpSales);
        call.enqueue(new Callback<Sales>() {
            @Override
            public void onResponse(Call<Sales> call, Response<Sales> response) {
                dialog.dismiss();
                if (response.body().getMessage().equals("success")){
                    Toast.makeText(AddSalesActivity.this, "Sales Berhasil Ditambah", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(AddSalesActivity.this, SalesActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    finish();
                }else{
                    Toast.makeText(AddSalesActivity.this, "Token Tidak Valid, Silahkan Login", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(AddSalesActivity.this, SalesActivity.class);
                    startActivity(intent);
                    finish();
                }
            }

            @Override
            public void onFailure(Call<Sales> call, Throwable t) {
                dialog.dismiss();
                t.printStackTrace();
                Toast.makeText(AddSalesActivity.this, "Terjadi Kesalahan Tidak Terduga", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
