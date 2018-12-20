package com.yayanheryanto.larismotor.view.owner;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.yayanheryanto.larismotor.R;
import com.yayanheryanto.larismotor.adapter.MainSliderAdapter;
import com.yayanheryanto.larismotor.model.Detail;
import com.yayanheryanto.larismotor.model.Motor;
import com.yayanheryanto.larismotor.retrofit.ApiClient;
import com.yayanheryanto.larismotor.retrofit.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ss.com.bannerslider.Slider;

import static com.yayanheryanto.larismotor.config.config.DATA_MOTOR;

public class DetailMotorActivity extends AppCompatActivity {

    private Slider slider;
    private TextView noMesin, noPolisi, noRangka, merk, tipe,tahun, hjm, status, kondisi, harga, dp, cicilan, tenor;
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_motor);

        slider = findViewById(R.id.banner_slider1);
        Slider.init(new com.yayanheryanto.larismotor.helper.PicassoImageLoadingService(this));

        noMesin = findViewById(R.id.no_mesin) ;
        noPolisi = findViewById(R.id.no_polisi) ;
        noRangka = findViewById(R.id.no_rangka) ;
        merk = findViewById(R.id.merk) ;
        tipe = findViewById(R.id.tipe) ;
        tahun = findViewById(R.id.tahun) ;
        hjm = findViewById(R.id.hjm) ;
        status = findViewById(R.id.status) ;
        kondisi = findViewById(R.id.kondisi) ;
        harga = findViewById(R.id.harga) ;
        dp = findViewById(R.id.dp) ;
        cicilan = findViewById(R.id.cicilan) ;
        tenor = findViewById(R.id.tenor) ;

        initProgressDialog();

        getDetail();
    }


    private void initProgressDialog() {
        dialog = new ProgressDialog(this);
        dialog.setTitle("Loading");
        dialog.setMessage("Sedang Memproses..");
        dialog.setCancelable(false);
    }

    private void getDetail() {

        dialog.show();
        Bundle data = getIntent().getExtras();
        Motor motor = data.getParcelable(DATA_MOTOR);

        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<Detail> call = apiInterface.getDetail(motor.getNoMesin().toString());
        call.enqueue(new Callback<Detail>() {
            @Override
            public void onResponse(Call<Detail> call, Response<Detail> response) {
                dialog.dismiss();
                Detail motor = response.body();
                noMesin.setText(motor.getNoMesin());
                noPolisi.setText(motor.getNoPolisi());
                noRangka.setText(motor.getNoRangka());
                merk.setText(motor.getNamaMerk());
                tipe.setText(motor.getNamaTipe());
                tahun.setText(""+motor.getTahun());
                hjm.setText("Rp. "+motor.getHjm());
                harga.setText("Rp." +motor.getHarga());
                dp.setText("Rp. "+motor.getDp());
                cicilan.setText("Rp." +motor.getCicilan());
                tenor.setText(motor.getTenor() + " Bulan");

                if (motor.getStatus().equals(0)) {
                    status.setText("Tersedia");
                }else{
                    status.setText("Sold Out");
                }

                if (motor.getKondisi().equals(1)) {
                    kondisi.setText("Baru");
                }else{
                    kondisi.setText("Bekas");
                }

                slider.setAdapter(new MainSliderAdapter(motor.getGambar(),motor.getGambar1(),motor.getGambar2()));

            }

            @Override
            public void onFailure(Call<Detail> call, Throwable t) {
                dialog.dismiss();
                t.printStackTrace();
                Toast.makeText(DetailMotorActivity.this, "Terjadi Kesalahan Tidak Terduga", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
