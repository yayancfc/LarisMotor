package com.yayanheryanto.larismotor.view.owner;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.yayanheryanto.larismotor.R;
import com.yayanheryanto.larismotor.model.Sales;

import static com.yayanheryanto.larismotor.config.config.DATA_SALES;

public class DetailSalesActivity extends AppCompatActivity {

    private TextView txtNoKtp, txtNama, txtAlamat, txtTelepon, txtUsername, txtPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_sales);

        txtNoKtp = findViewById(R.id.no_ktp);
        txtNama = findViewById(R.id.txt_nama);
        txtTelepon = findViewById(R.id.txt_telepon);
        txtAlamat = findViewById(R.id.alamat);
        txtUsername = findViewById(R.id.username);
        txtPassword = findViewById(R.id.password);

        getFromIntent();
    }

    private void getFromIntent() {

        Bundle data = getIntent().getExtras();
        Sales sales = data.getParcelable(DATA_SALES);

        txtNama.setText(sales.getNama());
        txtAlamat.setText(sales.getAlamat());
        txtTelepon.setText(sales.getNoWa());
        txtNoKtp.setText(sales.getNoKtpSales());
        txtUsername.setText(sales.getUsername());
        txtPassword.setText(sales.getPassword());

    }
}
