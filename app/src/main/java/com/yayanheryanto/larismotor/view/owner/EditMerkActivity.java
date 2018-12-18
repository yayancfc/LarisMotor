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
import static com.yayanheryanto.larismotor.config.config.DATA_MERK;
import static com.yayanheryanto.larismotor.config.config.ID_USER;
import static com.yayanheryanto.larismotor.config.config.MY_PREFERENCES;

public class EditMerkActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText txtNamaMerk;
    private Button btnSave;
    private ProgressDialog dialog;
    private Merk merk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_merk);

        txtNamaMerk = findViewById(R.id.namaMerk);
        btnSave = findViewById(R.id.btnSave);


        getFromIntent();

        btnSave.setOnClickListener(this);
        initProgressDialog();

    }


    private void getFromIntent() {
        Bundle data = getIntent().getExtras();
        merk = data.getParcelable(DATA_MERK);

        txtNamaMerk.setText(merk.getNamaMerk());

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
                updateMerk();
                break;
        }
    }

    private void updateMerk() {
        dialog.show();
        SharedPreferences pref = getSharedPreferences(MY_PREFERENCES, Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = pref.edit();
        String id = pref.getString(ID_USER, "");
        String token = pref.getString(ACCESTOKEN, "");

        int idMerkLama = merk.getIdMerk();

        String namaMerk = txtNamaMerk.getText().toString();

        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<Merk> call = apiInterface.updatMerk(token, idMerkLama, namaMerk);
        call.enqueue(new Callback<Merk>() {
            @Override
            public void onResponse(Call<Merk> call, Response<Merk> response) {
                dialog.dismiss();
                if (response.body().getMessage().equals("success")){
                    Toast.makeText(EditMerkActivity.this, "Merk Berhasil Diubah", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(EditMerkActivity.this, MasterActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);

                }else{
                    Toast.makeText(EditMerkActivity.this, "Token Tidak Valid, Silahkan Login", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(EditMerkActivity.this, LoginActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);

                }
            }

            @Override
            public void onFailure(Call<Merk> call, Throwable t) {
                dialog.dismiss();
                t.printStackTrace();
                Toast.makeText(EditMerkActivity.this, "Terjadi Kesalahan Tidak Terduga", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
