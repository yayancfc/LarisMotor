package com.yayanheryanto.larismotor.view.owner;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.yayanheryanto.larismotor.R;
import com.yayanheryanto.larismotor.view.LoginActivity;
import com.yayanheryanto.larismotor.model.Merk;
import com.yayanheryanto.larismotor.model.MerkTipe;
import com.yayanheryanto.larismotor.model.Tipe;
import com.yayanheryanto.larismotor.retrofit.ApiClient;
import com.yayanheryanto.larismotor.retrofit.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.yayanheryanto.larismotor.config.config.ACCESTOKEN;
import static com.yayanheryanto.larismotor.config.config.DATA_TIPE;
import static com.yayanheryanto.larismotor.config.config.DEBUG;
import static com.yayanheryanto.larismotor.config.config.ID_USER;
import static com.yayanheryanto.larismotor.config.config.MY_PREFERENCES;

public class EditTipeActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText txtNamaTipe;
    private Button btnSave;
    private ProgressDialog dialog;
    private Spinner spinnerMerk;
    private ArrayAdapter<String> adapter;
    private MerkTipe tipe;
    private String merkMotor;
    private List<Merk> merk;
    int id_merk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_tipe);

        txtNamaTipe = findViewById(R.id.namaTipe);
        spinnerMerk = findViewById(R.id.spinner1);
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line);
        btnSave = findViewById(R.id.btnSave);

        initProgressDialog();
        getMerk();
        getFromIntent();

        spinnerMerk.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                id_merk = merk.get(i).getIdMerk();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        btnSave.setOnClickListener(this);
    }


    private void initProgressDialog() {
        dialog = new ProgressDialog(this);
        dialog.setTitle("Loading");
        dialog.setMessage("Sedang Memproses..");
        dialog.setCancelable(false);
    }

    private void getFromIntent() {
        Bundle data = getIntent().getExtras();
        tipe = data.getParcelable(DATA_TIPE);

        txtNamaTipe.setText(tipe.getNamaTipe());
        merkMotor = tipe.getNamaMerk();

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnSave:
                updateTipe();
                break;
        }
    }

    private void updateTipe() {
        dialog.show();
        SharedPreferences pref = getSharedPreferences(MY_PREFERENCES, Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = pref.edit();
        String id = pref.getString(ID_USER, "");
        String token = pref.getString(ACCESTOKEN, "");

        int idTipeLama = tipe.getIdTipe();
        String namaTipe = txtNamaTipe.getText().toString();

        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<Tipe> call = apiInterface.updatTipeMotor(token, idTipeLama, namaTipe, id_merk);
        call.enqueue(new Callback<Tipe>() {
            @Override
            public void onResponse(Call<Tipe> call, Response<Tipe> response) {
                dialog.dismiss();
                if (response.body().getMessage().equals("success")){
                    Toast.makeText(EditTipeActivity.this, "Tipe Berhasil Diubah", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(EditTipeActivity.this, MasterActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }else{
                    Toast.makeText(EditTipeActivity.this, "Token Tidak Valid, Silahkan Login", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(EditTipeActivity.this, LoginActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }
            }

            @Override
            public void onFailure(Call<Tipe> call, Throwable t) {
                dialog.dismiss();
                t.printStackTrace();
                Toast.makeText(EditTipeActivity .this, "Terjadi Kesalahan Tidak Terduga", Toast.LENGTH_SHORT).show();
            }
        });
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
                            adapter.add(merkMotor.getNamaMerk());

                        }
                        spinnerMerk.setAdapter(adapter);
                        spinnerMerk.setSelection(adapter.getPosition(merkMotor));
                    }
                }

                @Override
                public void onFailure(Call<List<Merk>> call, Throwable t) {
                    dialog.dismiss();
                    t.printStackTrace();
                    Toast.makeText(EditTipeActivity.this, "Terjadi Kesalahan Tidak Terduga", Toast.LENGTH_SHORT).show();
                }


            });
        }

}
