package com.yayanheryanto.larismotor.view.transaksi;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.yayanheryanto.larismotor.R;
import com.yayanheryanto.larismotor.model.Merk;
import com.yayanheryanto.larismotor.model.MerkTipe;
import com.yayanheryanto.larismotor.model.Motor;
import com.yayanheryanto.larismotor.model.Tipe;
import com.yayanheryanto.larismotor.retrofit.ApiClient;
import com.yayanheryanto.larismotor.retrofit.ApiInterface;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.view.View.GONE;
import static com.yayanheryanto.larismotor.config.config.DATA_MOTOR;

public class TransaksiActivity extends AppCompatActivity {


    Spinner spinnerCaraBayar;
    EditText cicilan;
    EditText tenor;
    EditText dp;
    EditText harga;
    EditText nomorMesin;
    EditText nomorRangka;
    EditText nomorPolisi;
    EditText subsidi;
    EditText pencairanLeasing;
    Spinner spinnerMerk;
    Spinner spinnerTipe;
    EditText tahun;
    EditText hargaJualMinimum;
    Button checklist;
    TextView tanggal;
    ArrayAdapter<String> merkAdapter;
    ArrayAdapter<String> tipeAdapter;
    int kondisi;
    private ProgressDialog dialog;
    String statusMotor;
    int idTipe, idMerk ;
    List<Tipe> tipes;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaksi);

        harga = findViewById(R.id.harga);
        dp = findViewById(R.id.dp);
        tenor = findViewById(R.id.tenor);
        cicilan = findViewById(R.id.cicilan);
        subsidi = findViewById(R.id.subsidi);
        pencairanLeasing = findViewById(R.id.pencairan_leasing);
        nomorMesin = findViewById(R.id.nomor_mesin_trans);
        nomorRangka = findViewById(R.id.nomor_rangka_trans);
        nomorPolisi = findViewById(R.id.nomor_polisi_trans);
        tahun = findViewById(R.id.tahun_trans);
        hargaJualMinimum = findViewById(R.id.harga_jual_minimum_trans);
        checklist = findViewById(R.id.cheklist);
        tanggal = findViewById(R.id.tanggal);
        spinnerMerk = findViewById(R.id.spinnerMerk);
        spinnerTipe = findViewById(R.id.spinnerTipe);
        initProgressDialog();


        getMerk();

        SimpleDateFormat df = new SimpleDateFormat("EEEE, dd MMMM yyyy", new Locale("ID"));

        tanggal.setText(df.format(Calendar.getInstance().getTime()));

        final String[] mobarArray = {"mobar", "mokas"};
        final Spinner spinnerMobar = findViewById(R.id.mobar);
        ArrayAdapter<String> mobar = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, mobarArray);
        spinnerMobar.setAdapter(mobar);
        spinnerMobar.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                statusMotor = mobarArray[position];
                clearAll();

                if (position == 0) {

                    nomorRangka.setEnabled(true);
                    nomorPolisi.setVisibility(GONE);
                    tahun.setEnabled(true);
                    hargaJualMinimum.setEnabled(true);
                    checklist.setVisibility(GONE);
                    kondisi = 1;

                    if (spinnerCaraBayar.getSelectedItemPosition() == 1) {

                        subsidi.setVisibility(View.VISIBLE);
                        pencairanLeasing.setVisibility(GONE);

                    }
                    else {

                        subsidi.setVisibility(View.GONE);
                        pencairanLeasing.setVisibility(GONE);


                    }



                } else {
                    nomorPolisi.setVisibility(View.VISIBLE);
                    spinnerMerk.setEnabled(false);
                    spinnerTipe.setEnabled(false);
                    nomorRangka.setEnabled(false);
                    tahun.setEnabled(false);
                    hargaJualMinimum.setHint(R.string.lbl_hjm);
                    hargaJualMinimum.setEnabled(false);
                    checklist.setVisibility(View.VISIBLE);
                    kondisi = 0;

                    if (spinnerCaraBayar.getSelectedItemPosition() == 1) {

                        subsidi.setVisibility(View.GONE);
                        pencairanLeasing.setVisibility(View.VISIBLE);

                    }
                    else {

                        subsidi.setVisibility(View.GONE);
                        pencairanLeasing.setVisibility(GONE);


                    }

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinnerCaraBayar = findViewById(R.id.cara_bayar);
        ArrayAdapter<CharSequence> caraBayar = ArrayAdapter.createFromResource(this,
                R.array.caraBayar, android.R.layout.simple_spinner_item);


        spinnerCaraBayar.setAdapter(caraBayar);
        spinnerCaraBayar.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (position == 0) {
                    harga.setVisibility(View.VISIBLE);
                    dp.setVisibility(GONE);
                    tenor.setVisibility(GONE);
                    cicilan.setVisibility(GONE);
                    subsidi.setVisibility(View.GONE);
                    pencairanLeasing.setVisibility(View.GONE);
                } else {
                    harga.setVisibility(GONE);
                    dp.setVisibility(View.VISIBLE);
                    tenor.setVisibility(View.VISIBLE);
                    cicilan.setVisibility(View.VISIBLE);

                    if (spinnerMobar.getSelectedItemPosition() == 0)
                    {

                        subsidi.setVisibility(View.VISIBLE);
                    }
                    else {

                        pencairanLeasing.setVisibility(View.VISIBLE);
                    }


                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        checklist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.show();

                String stringNomorMesin = nomorMesin.getText().toString();
                final ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
                Call<List<Motor>> call = apiInterface.getMotorById(stringNomorMesin);
                call.enqueue(new Callback<List<Motor>>() {
                    @Override
                    public void onResponse(Call<List<Motor>> call, Response<List<Motor>> response) {

                        if (response.body().isEmpty()) {

                            dialog.dismiss();
                            Toast.makeText(getApplicationContext(), "Data motor tidak ditemukan", Toast.LENGTH_SHORT).show();
                            clearAll();


                        } else {
                            dialog.dismiss();
                            Motor motor = response.body().get(0);

                            nomorRangka.setText("No. Rangka : " + motor.getNoRangka());
                            nomorRangka.setEnabled(false);
                            nomorRangka.setTextColor(Color.BLACK);

                            nomorPolisi.setText("No. Polisi : " + motor.getNoPolisi());
                            nomorPolisi.setEnabled(false);
                            nomorPolisi.setTextColor(Color.BLACK);


                            dp.setText("DP : Rp. " + motor.getDp());
                            dp.setEnabled(false);
                            dp.setTextColor(Color.BLACK);


                            tenor.setText("Tenor : " + motor.getTenor() + " Bulan");
                            tenor.setEnabled(false);
                            tenor.setTextColor(Color.BLACK);


                            cicilan.setText("Cicilan : Rp. " + motor.getCicilan());
                            cicilan.setEnabled(false);
                            cicilan.setTextColor(Color.BLACK);

                            Call<List<MerkTipe>> call2 = apiInterface.getMerkById(String.valueOf(motor.getIdMerk()), String.valueOf(motor.getIdTipe()));
                            call2.enqueue(new Callback<List<MerkTipe>>() {
                                @Override
                                public void onResponse(Call<List<MerkTipe>> call, Response<List<MerkTipe>> response) {

                                    MerkTipe merkTipe = response.body().get(0);
                                    spinnerMerk.setSelection(merkAdapter.getPosition(merkTipe.getNamaMerk()));
                                    spinnerTipe.setSelection(tipeAdapter.getPosition(merkTipe.getNamaTipe()));

                                }

                                @Override
                                public void onFailure(Call<List<MerkTipe>> call, Throwable t) {
                                    dialog.dismiss();
                                }
                            });

                            tahun.setText("Tahun : " + motor.getTahun());
                            tahun.setEnabled(false);
                            tahun.setTextColor(Color.BLACK);

                            hargaJualMinimum.setText("Rp. " + motor.getHjm());
                            hargaJualMinimum.setEnabled(false);
                            hargaJualMinimum.setTextColor(Color.BLACK);

                        }


                    }

                    @Override
                    public void onFailure(Call<List<Motor>> call, Throwable t) {
                        dialog.dismiss();
                        Log.v("cik", t.getMessage());

                    }
                });

            }
        });

        TextView textView = findViewById(R.id.next_trans);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getData();
            }
        });

    }

    private void getData() {

        Motor motor = new Motor();
        String noMesin, noRangka, tahunMotor, hjm, dpMotor, cicilanMotor, tenorMotor, hargaTerjual;

        if (kondisi == 0) {
            noMesin = nomorMesin.getText().toString();
            hargaTerjual = harga.getText().toString();
            motor.setNoMesin(noMesin);
            motor.setKondisi(kondisi);
            motor.setHargaTerjual(Integer.valueOf(hargaTerjual));
            Intent intent = new Intent(TransaksiActivity.this, IsiDataActivity.class);
            intent.putExtra(DATA_MOTOR, motor);
            startActivity(intent);

        } else {
            noMesin = nomorMesin.getText().toString();
            noRangka = nomorRangka.getText().toString();
            tahunMotor = tahun.getText().toString();
            hjm = hargaJualMinimum.getText().toString();
            dpMotor = dp.getText().toString();
            cicilanMotor = cicilan.getText().toString();
            tenorMotor = tenor.getText().toString();
            hargaTerjual = harga.getText().toString();


            motor.setNoMesin(noMesin);
            motor.setNoRangka(noRangka);
            motor.setTahun(Integer.valueOf(tahunMotor));
            motor.setHjm(Integer.valueOf(hjm));
            motor.setKondisi(kondisi);
            motor.setIdTipe(idTipe);
            motor.setIdMerk(idMerk);
            if (spinnerCaraBayar.getSelectedItemPosition() == 0) {
                motor.setHargaTerjual(Integer.valueOf(hargaTerjual));
            } else {
                motor.setDp(Integer.valueOf(dpMotor));
                motor.setCicilan(Integer.valueOf(cicilanMotor));
                motor.setTenor(Integer.valueOf(tenorMotor));
            }
            Intent intent = new Intent(TransaksiActivity.this, IsiDataActivity.class);
            intent.putExtra(DATA_MOTOR, motor);
            startActivity(intent);


        }
    }

    private void clearAll() {
        nomorMesin.setText("");
        nomorRangka.setText("");
        spinnerMerk.setSelection(0);
        spinnerTipe.setSelection(0);
        tahun.setText("");
        hargaJualMinimum.setText("");
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
                String[] merkArray = new String[merks.size()];

                int i = 0;

                for (Merk merkNow : merks) {

                    merkArray[i] = merkNow.getNamaMerk();
                    i++;
                }

                merkAdapter = new ArrayAdapter<>(TransaksiActivity.this, android.R.layout.simple_spinner_item, merkArray);
                spinnerMerk.setAdapter(merkAdapter);


            }

            @Override
            public void onFailure(Call<List<Merk>> call, Throwable t) {

            }
        });

        spinnerMerk.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, final int position, long id) {

                Call<List<Tipe>> call2 = apiInterface.getTipe(String.valueOf(merks.get(position).getIdMerk()));
                call2.enqueue(new Callback<List<Tipe>>() {
                    @Override
                    public void onResponse(Call<List<Tipe>> call, Response<List<Tipe>> response) {

                        tipes = new ArrayList<>();
                        for (Tipe tipeNow : response.body()) {

                            tipes.add(tipeNow);

                        }

                        int i = 0;

                        String[] tipeArray;

                        if (tipes.isEmpty()) {
                            tipeArray = new String[1];
                            tipeArray[i] = "Tipe tidak tersedia";

                        } else {
                            tipeArray = new String[tipes.size()];
                            for (Tipe tipeNow : tipes) {

                                tipeArray[i] = tipeNow.getNamaTipe();
                                i++;
                            }
                        }
                        tipeAdapter = new ArrayAdapter<>(TransaksiActivity.this, android.R.layout.simple_spinner_item, tipeArray);
                        spinnerTipe.setAdapter(tipeAdapter);
                        idMerk = merks.get(position).getIdMerk();

                    }

                    @Override
                    public void onFailure(Call<List<Tipe>> call, Throwable t) {

                    }
                });


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinnerTipe.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                idTipe = tipes.get(i).getIdTipe();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });



    }





    private void initProgressDialog() {
        dialog = new ProgressDialog(this);
        dialog.setTitle("Loading");
        dialog.setMessage("Sedang Memproses..");
        dialog.setCancelable(false);
    }


}
