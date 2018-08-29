package com.yayanheryanto.larismotor.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
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

import com.squareup.picasso.Picasso;
import com.yayanheryanto.larismotor.R;
import com.yayanheryanto.larismotor.model.Merk;
import com.yayanheryanto.larismotor.model.MerkTipe;
import com.yayanheryanto.larismotor.model.Motor;
import com.yayanheryanto.larismotor.model.Tipe;
import com.yayanheryanto.larismotor.retrofit.ApiClient;
import com.yayanheryanto.larismotor.retrofit.ApiInterface;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

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
import static com.yayanheryanto.larismotor.config.config.BASE_URL;
import static com.yayanheryanto.larismotor.config.config.DATA_MOTOR;
import static com.yayanheryanto.larismotor.config.config.DEBUG;
import static com.yayanheryanto.larismotor.config.config.MY_PREFERENCES;

public class EditMotorActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnUpload, btnSave;
    private List<Merk> merk;
    private List<Tipe> tipe;
    private Spinner spinnerMerk, spinnerTipe;
    private ArrayAdapter<String> adapter, adapter2;
    private ImageView image1, image2, image3;
    private ArrayList<Image> images;
    private EditText no_mesin, no_polisi, no_rangka, tahun, hjm;
    private RadioGroup status;
    private String merkMotor, tipeMotor;
    private ProgressDialog dialog;
    private Motor motor;
    private String s1,s2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_motor);

        btnUpload = findViewById(R.id.btnImage);
        btnSave = findViewById(R.id.btnSave);
        spinnerMerk = findViewById(R.id.spinner1);
        spinnerTipe = findViewById(R.id.spinner2);
        no_mesin = findViewById(R.id.no_mesin);
        no_polisi = findViewById(R.id.no_polisi);
        no_rangka = findViewById(R.id.no_rangka);
        tahun = findViewById(R.id.tahun);
        hjm = findViewById(R.id.hjm);
        status = findViewById(R.id.status);

        image1 = findViewById(R.id.image1);
        image2 = findViewById(R.id.image2);
        image3 = findViewById(R.id.image3);

        initProgressDialog();

        getDataFromIntent();

        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line);
        adapter2 = new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line);


        getMerkById(String.valueOf(motor.getIdMerk()),String.valueOf(motor.getIdTipe()));
        getMerk();

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

        btnUpload.setOnClickListener(this);
        btnSave.setOnClickListener(this);
    }

    private void getDataFromIntent() {

        Bundle data = getIntent().getExtras();
        motor = (Motor) data.getParcelable(DATA_MOTOR);
        no_mesin.setText(motor.getNoMesin());
        no_polisi.setText(""+motor.getNoPolisi());
        no_rangka.setText(motor.getNoRangka());
        hjm.setText(""+motor.getHjm());
        tahun.setText(motor.getTahun());
        if (motor.getStatus().equals(1)){
            status.check(R.id.radio_available);
        }else {
            status.check(R.id.radio_not_available);
        }

        if (motor.getGambar()!=null){
            Picasso.get().load(BASE_URL+"storage/motor/"+motor.getGambar()).into(image1);
        }
        if (motor.getGambar1()!=null){
            Picasso.get().load(BASE_URL+"storage/motor/"+motor.getGambar1()).into(image2);
        }
        if (motor.getGambar2()!=null){
            Picasso.get().load(BASE_URL+"storage/motor/"+motor.getGambar2()).into(image3);
        }
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
                        adapter.add(merkMotor.getNamaMerk());

                    }
                    Log.d(DEBUG, s1 + " " + s2);
                    spinnerMerk.setAdapter(adapter);
                    spinnerMerk.setSelection(adapter.getPosition(s1));
                }
            }

            @Override
            public void onFailure(Call<List<Merk>> call, Throwable t) {
                dialog.dismiss();
                t.printStackTrace();
                Toast.makeText(EditMotorActivity.this, "Terjadi Kesalahan Tidak Terduga", Toast.LENGTH_SHORT).show();
            }


        });
    }

    private void getMerkById(String id_merk, String id_tipe) {
        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<List<MerkTipe>> call = apiInterface.getMerkById(id_merk, id_tipe);
        call.enqueue(new Callback<List<MerkTipe>>() {
            @Override
            public void onResponse(Call<List<MerkTipe>> call, Response<List<MerkTipe>> response) {
                s2 = response.body().get(0).getNamaTipe();
                s1 = response.body().get(0).getNamaMerk();
            }

            @Override
            public void onFailure(Call<List<MerkTipe>> call, Throwable t) {
                t.printStackTrace();
                Toast.makeText(EditMotorActivity.this, "Terjadi Kesalahan Tidak Terduga", Toast.LENGTH_SHORT).show();
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
                        adapter2.add(tipeMotor.getNamaTipe());
                    }

                    spinnerTipe.setAdapter(adapter2);
                    spinnerTipe.setSelection(adapter2.getPosition(s2));
                }
            }

            @Override
            public void onFailure(Call<List<Tipe>> call, Throwable t) {
                dialog.dismiss();
                t.printStackTrace();
                Toast.makeText(EditMotorActivity.this, "Terjadi Kesalahan Tidak Terduga", Toast.LENGTH_SHORT).show();
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

    private void uploadImage() {
        dialog.show();
        SharedPreferences pref = getSharedPreferences(MY_PREFERENCES, Context.MODE_PRIVATE);
        String token = pref.getString(ACCESTOKEN, "");

        int selectedId = status.getCheckedRadioButtonId();
        RadioButton radioButton = (RadioButton) findViewById(selectedId);
        String tersedia = radioButton.getText().toString();
        String statusMotor = "0";
        if (tersedia.equalsIgnoreCase("tersedia")){
            statusMotor = "1";
        }
        String mesin = no_mesin.getText().toString();
        String polisi = no_polisi.getText().toString();
        String rangka = no_rangka.getText().toString();
        String hjmMotor = hjm.getText().toString();
        String tahunMotor = tahun.getText().toString();


        MultipartBody.Builder builder = new MultipartBody.Builder();
        builder.setType(MultipartBody.FORM);
        builder.addFormDataPart("no_mesin_awal",motor.getNoMesin());
        builder.addFormDataPart("no_polisi",polisi);
        builder.addFormDataPart("no_mesin",mesin);
        builder.addFormDataPart("no_rangka",rangka);
        builder.addFormDataPart("hjm",hjmMotor);
        builder.addFormDataPart("tahun",tahunMotor);
        builder.addFormDataPart("status",statusMotor);
        builder.addFormDataPart("tipe",tipeMotor);
        builder.addFormDataPart("merk",merkMotor);
        if (motor.getGambar()!=null){
            builder.addFormDataPart("gambar",motor.getGambar());
        }
        if (motor.getGambar1()!=null){
            builder.addFormDataPart("gambar1",motor.getGambar1());
        }
        if (motor.getGambar2()!=null){
            builder.addFormDataPart("gambar2",motor.getGambar2());
        }

        if (images!=null) {
            for (int i = 0; i < images.size(); i++) {
                File file = new File(images.get(i).path);
                builder.addFormDataPart("file[]", file.getName(), RequestBody.create(MediaType.parse("multipart/form-data"), file));
                Log.d(DEBUG, file.getName());
            }
        }

        MultipartBody requestBody = builder.build();
        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<Motor> call = apiInterface.updateMotor(token, requestBody);
        call.enqueue(new Callback<Motor>() {
            @Override
            public void onResponse(Call<Motor> call, Response<Motor> response) {
                dialog.dismiss();
                Log.d(DEBUG, String.valueOf(response.body().getMessage()));
                if (response.body().getMessage().equals("success")){
                    Toast.makeText(EditMotorActivity.this, "Data Motor Berhasil Diubah", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(EditMotorActivity.this, MotorActivity.class);
                    startActivity(intent);
                    finish();
                }else{
                    Toast.makeText(EditMotorActivity.this, "Token Tidak Valid, Silahkan Login", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(EditMotorActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
            }

            @Override
            public void onFailure(Call<Motor> call, Throwable t) {
                dialog.dismiss();
                t.printStackTrace();
                Toast.makeText(EditMotorActivity.this, "Terjadi kesalahan Tidak Terduga", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
