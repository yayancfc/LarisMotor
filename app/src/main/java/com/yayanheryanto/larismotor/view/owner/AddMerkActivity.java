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
import com.yayanheryanto.larismotor.view.LoginActivity;
import com.yayanheryanto.larismotor.model.Merk;
import com.yayanheryanto.larismotor.retrofit.ApiClient;
import com.yayanheryanto.larismotor.retrofit.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.yayanheryanto.larismotor.config.config.ACCESTOKEN;
import static com.yayanheryanto.larismotor.config.config.ID_USER;
import static com.yayanheryanto.larismotor.config.config.MY_PREFERENCES;

public class AddMerkActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText txtNamaMerk;
    private Button btnSave;
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_merk);

        txtNamaMerk = findViewById(R.id.namaMerk);
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
                saveMerk();
                break;
        }
    }

    private void saveMerk() {
        dialog.show();
        SharedPreferences pref = getSharedPreferences(MY_PREFERENCES, Context.MODE_PRIVATE);
        String id = pref.getString(ID_USER, "");
        String token = pref.getString(ACCESTOKEN, "");

        String namaMerk = txtNamaMerk.getText().toString();

        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<Merk> call = apiInterface.addMerk(token, namaMerk);
        call.enqueue(new Callback<Merk>() {
            @Override
            public void onResponse(Call<Merk> call, Response<Merk> response) {
                dialog.dismiss();
                if (response.body().getMessage().equals("success")){
                    Toast.makeText(AddMerkActivity.this, "Merk Berhasil Ditambah", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(AddMerkActivity.this, MasterActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    finish();
                }else{
                    Toast.makeText(AddMerkActivity.this, "Token Tidak Valid, Silahkan Login", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(AddMerkActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
            }

            @Override
            public void onFailure(Call<Merk> call, Throwable t) {
                dialog.dismiss();
                t.printStackTrace();
                Toast.makeText(AddMerkActivity.this, "Terjadi Kesalahan Tidak Terduga", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
