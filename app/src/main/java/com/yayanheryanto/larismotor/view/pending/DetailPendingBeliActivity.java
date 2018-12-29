package com.yayanheryanto.larismotor.view.pending;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.yayanheryanto.larismotor.R;
import com.yayanheryanto.larismotor.model.PendingBeli;

import static com.yayanheryanto.larismotor.config.config.DATA_PENDING;

public class DetailPendingBeliActivity extends AppCompatActivity {

    private TextView txtNama, txtAlamat, txtNoTelepon, txtMerk, txtTipe, txtTahun, txtHarga;
    private PendingBeli pendingBeli;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_pending_beli);


        txtNama = findViewById(R.id.nama);
        txtAlamat = findViewById(R.id.alamat);
        txtNoTelepon = findViewById(R.id.telepon);
        txtMerk = findViewById(R.id.nama_merk);
        txtTipe = findViewById(R.id.nama_tipe);
        txtTahun = findViewById(R.id.tahun);
        txtHarga = findViewById(R.id.harga);

        getFromIntent();

    }

    private void getFromIntent() {

        Bundle data = getIntent().getExtras();
        pendingBeli = data.getParcelable(DATA_PENDING);

        txtNama.setText(pendingBeli.getNama());
        txtAlamat.setText(pendingBeli.getAlamat());
        txtNoTelepon.setText(pendingBeli.getNoTelp());
        txtMerk.setText(pendingBeli.getNamaMerk());
        txtTipe.setText(pendingBeli.getNamaTipe());
        txtTahun.setText(""+ pendingBeli.getTahun());
        txtHarga.setText("Rp. "+ pendingBeli.getHarga());
    }


}
