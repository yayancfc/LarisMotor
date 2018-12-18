package com.yayanheryanto.larismotor.view.owner;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.yayanheryanto.larismotor.R;
import com.yayanheryanto.larismotor.model.Pending;

import static com.yayanheryanto.larismotor.config.config.DATA_PENDING;

public class DetailPendingBeliActivity extends AppCompatActivity {

    private TextView txtNama, txtAlamat, txtNoTelepon, txtNamaMotor, txtTahun, txtHarga;
    private Pending pending;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_pending_beli);


        txtNama = findViewById(R.id.nama);
        txtAlamat = findViewById(R.id.alamat);
        txtNoTelepon = findViewById(R.id.telepon);
        txtNamaMotor = findViewById(R.id.nama_motor);
        txtTahun = findViewById(R.id.tahun);
        txtHarga = findViewById(R.id.harga);

        getFromIntent();

    }

    private void getFromIntent() {

        Bundle data = getIntent().getExtras();
        pending = data.getParcelable(DATA_PENDING);

        txtNama.setText(pending.getNama());
        txtAlamat.setText(pending.getAlamat());
        txtNoTelepon.setText(pending.getNoTelp());
        txtNamaMotor.setText(pending.getNamaMotor());
        txtTahun.setText(""+pending.getTahun());
        txtHarga.setText("Rp. "+pending.getHarga());
    }


}
