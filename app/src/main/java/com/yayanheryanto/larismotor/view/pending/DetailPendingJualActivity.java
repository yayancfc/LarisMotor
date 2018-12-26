package com.yayanheryanto.larismotor.view.pending;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.yayanheryanto.larismotor.R;
import com.yayanheryanto.larismotor.model.PendingJual;

import static com.yayanheryanto.larismotor.config.config.DATA_PENDING;

public class DetailPendingJualActivity extends AppCompatActivity {

    private TextView txtNama, txtAlamat, txtNoTelepon, txtMerk, txtTipe, txtNoPolisi, txtNoMesin, txtTahun, txtHarga;
    private PendingJual pending;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_pending_jual);


        txtNama = findViewById(R.id.nama);
        txtAlamat = findViewById(R.id.alamat);
        txtNoTelepon = findViewById(R.id.telepon);
        txtMerk = findViewById(R.id.merk);
        txtTipe = findViewById(R.id.tipe);
        txtTahun = findViewById(R.id.tahun);
        txtHarga = findViewById(R.id.harga);
        txtNoMesin = findViewById(R.id.no_mesin);
        txtNoPolisi = findViewById(R.id.no_polisi);

        getFromIntent();

    }

    private void getFromIntent() {

        Bundle data = getIntent().getExtras();
        pending = data.getParcelable(DATA_PENDING);

        txtNama.setText(pending.getNama());
        txtAlamat.setText(pending.getAlamat());
        txtNoTelepon.setText(pending.getNoTelp());
        txtMerk.setText(pending.getNamaMerk());
        txtTipe.setText(pending.getNamaTipe());
        txtNoPolisi.setText(pending.getNoPolisi());
        txtNoMesin.setText(pending.getNoMesin());
        txtTahun.setText(""+pending.getTahun());
        txtHarga.setText("Rp. "+pending.getHarga());
    }
}
