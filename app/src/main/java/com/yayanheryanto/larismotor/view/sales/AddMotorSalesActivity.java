package com.yayanheryanto.larismotor.view.sales;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.yayanheryanto.larismotor.R;
import com.yayanheryanto.larismotor.view.LoginActivity;
import com.yayanheryanto.larismotor.view.owner.MotorActivity;
import com.yayanheryanto.larismotor.model.Merk;
import com.yayanheryanto.larismotor.model.Motor;
import com.yayanheryanto.larismotor.model.Tipe;
import com.yayanheryanto.larismotor.retrofit.ApiClient;
import com.yayanheryanto.larismotor.retrofit.ApiInterface;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import id.zelory.compressor.Compressor;
import in.myinnos.awesomeimagepicker.activities.AlbumSelectActivity;
import in.myinnos.awesomeimagepicker.helpers.ConstantsCustomGallery;
import in.myinnos.awesomeimagepicker.models.Image;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.yayanheryanto.larismotor.config.config.ACCESTOKEN;
import static com.yayanheryanto.larismotor.config.config.DEBUG;
import static com.yayanheryanto.larismotor.config.config.ID_USER;
import static com.yayanheryanto.larismotor.config.config.MY_PREFERENCES;

public class AddMotorSalesActivity extends AppCompatActivity implements View.OnClickListener {


    private Button btnUpload, btnSave;
    private List<Merk> merk;
    private List<Tipe> tipe;
    private Spinner spinnerMerk, spinnerTipe;
    private ArrayAdapter<String> adapter, adapter2;
    private ImageView image1, image2, image3;
    private ArrayList<Image> images;
    private EditText no_mesin, no_polisi, no_rangka, tahun, harga, harga_terjual, dp, cicilan, tenor;
    private RadioGroup status;
    private int merkMotor, tipeMotor;
    private ProgressDialog dialog;
    private TextInputLayout terjual;
    private File file, file2 = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_motor_sales);

        initProgressDialog();

        btnUpload = findViewById(R.id.btnImage);
        btnSave = findViewById(R.id.btnSave);
        spinnerMerk = findViewById(R.id.spinner1);
        spinnerTipe = findViewById(R.id.spinner2);
        no_mesin = findViewById(R.id.no_mesin);
        no_polisi = findViewById(R.id.no_polisi);
        no_rangka = findViewById(R.id.no_rangka);
        tahun = findViewById(R.id.tahun);
        status = findViewById(R.id.status);
        harga = findViewById(R.id.harga);
        harga_terjual = findViewById(R.id.harga_terjual);
        cicilan = findViewById(R.id.cicilan);
        tenor = findViewById(R.id.tenor);
        dp = findViewById(R.id.dp);
        terjual = findViewById(R.id.terjual);

        image1 = findViewById(R.id.image1);
        image2 = findViewById(R.id.image2);
        image3 = findViewById(R.id.image3);

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

        status.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                if (status.getCheckedRadioButtonId() == R.id.radio_available) {
                    terjual.setVisibility(View.GONE);
                } else {
                    terjual.setVisibility(View.VISIBLE);
                }

            }
        });

        btnUpload.setOnClickListener(this);
        btnSave.setOnClickListener(this);


    }

    private void initProgressDialog() {
        dialog = new ProgressDialog(this);
        dialog.setTitle("Loading");
        dialog.setMessage("Sedang Memproses..");
        dialog.setCancelable(false);
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
                Toast.makeText(AddMotorSalesActivity.this, "Terjadi Kesalahan Tidak Terduga", Toast.LENGTH_SHORT).show();
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
                Toast.makeText(AddMotorSalesActivity.this, "Terjadi Kesalahan Tidak Terduga", Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnImage :
                Intent intent = new Intent(this, AlbumSelectActivity.class);
                intent.putExtra(ConstantsCustomGallery.INTENT_EXTRA_LIMIT, 3); // set limit for image selection
                startActivityForResult(intent, ConstantsCustomGallery.REQUEST_CODE);
                break;

            case R.id.btnSave :
                uploadImage();
                break;
        }
    }

    private void uploadImage() {
        dialog.show();


        SharedPreferences pref = getSharedPreferences(MY_PREFERENCES, Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = pref.edit();
        String id = pref.getString(ID_USER, "");
        String token = pref.getString(ACCESTOKEN, "");

        int selectedId = status.getCheckedRadioButtonId();
        RadioButton radioButton = (RadioButton) findViewById(selectedId);
        String tersedia = radioButton.getText().toString();
        String statusMotor = "1";
        if (tersedia.equalsIgnoreCase("tersedia")){
            statusMotor = "0";
        }

        String mesin = no_mesin.getText().toString();
        String polisi = no_polisi.getText().toString();
        String rangka = no_rangka.getText().toString();
        String tahunMotor = tahun.getText().toString();
        String hargaMotor = harga.getText().toString();
        String hargaTerjual = harga_terjual.getText().toString();
        String dpMotor = dp.getText().toString();
        String cicilanMotor = cicilan.getText().toString();
        String tenorMotor = tenor.getText().toString();


        MultipartBody.Builder builder = new MultipartBody.Builder();
        builder.setType(MultipartBody.FORM);
        builder.addFormDataPart("no_polisi",polisi);
        builder.addFormDataPart("no_mesin",mesin);
        builder.addFormDataPart("no_rangka",rangka);
        builder.addFormDataPart("tahun",tahunMotor);
        builder.addFormDataPart("status",statusMotor);
        builder.addFormDataPart("tipe",String.valueOf(tipeMotor));
        builder.addFormDataPart("merk",String.valueOf(merkMotor));
        builder.addFormDataPart("id_user",id);
        builder.addFormDataPart("harga",hargaMotor);
        builder.addFormDataPart("harga_terjual",hargaTerjual);
        builder.addFormDataPart("dp",dpMotor);
        builder.addFormDataPart("cicilan",cicilanMotor);
        builder.addFormDataPart("tenor",tenorMotor);

        if (images == null) {
            builder.addFormDataPart("file[]", file.getName(), RequestBody.create(MediaType.parse("multipart/form-data"), file));
        }else {
            for (int i = 0; i < images.size(); i++) {
                file2 = new File(images.get(i).path);
                try {
                    file = new Compressor(this).compressToFile(file2);

                } catch (IOException e) {
                    e.printStackTrace();
                    Log.e("Error", e.getMessage());
                }
                builder.addFormDataPart("file[]", file.getName(), RequestBody.create(MediaType.parse("multipart/form-data"), file));
                Log.d(DEBUG, file.getName());
            }
        }

        MultipartBody requestBody = builder.build();
        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<Motor> call = apiInterface.addMotor(token, requestBody);
        call.enqueue(new Callback<Motor>() {
            @Override
            public void onResponse(Call<Motor> call, Response<Motor> response) {
                dialog.dismiss();
                Log.d(DEBUG, String.valueOf(response.body().getMessage()));
                if (response.body().getMessage().equals("success")){
                    Toast.makeText(AddMotorSalesActivity.this, "Motor Berhasil Ditambah", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(AddMotorSalesActivity.this, MotorSalesActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    finish();
                }else{
                    Toast.makeText(AddMotorSalesActivity.this, "Token Tidak Valid, Silahkan Login", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(AddMotorSalesActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
            }

            @Override
            public void onFailure(Call<Motor> call, Throwable t) {
                dialog.dismiss();
                t.printStackTrace();
                Toast.makeText(AddMotorSalesActivity.this, "Terjadi kesalahan Tidak Terduga", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ConstantsCustomGallery.REQUEST_CODE && resultCode == Activity.RESULT_OK && data != null) {
            //The array list has the image paths of the selected images
            images = data.getParcelableArrayListExtra(ConstantsCustomGallery.INTENT_EXTRA_IMAGES);

            for (int i = 0; i < images.size(); i++) {
                Uri uri = Uri.fromFile(new File(images.get(i).path));
                if (i==0){
                    image1.setImageURI(uri);
                }
                if (i==1){
                    image2.setImageURI(uri);
                }
                if (i==2){
                    image3.setImageURI(uri);
                }
                Log.d(DEBUG, String.valueOf(uri));

            }
        }
    }
}
