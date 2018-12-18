package com.yayanheryanto.larismotor.view.owner;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.yayanheryanto.larismotor.R;
import com.yayanheryanto.larismotor.model.Customer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

import static com.yayanheryanto.larismotor.config.config.DATA_CUSTOMER;

public class DetailCustomerActivity extends AppCompatActivity {

    private TextView noKTP, nama, alamat, ttl, noHp, pekerjaan, agama, whatsapp, instagram, facebook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_customer);


        noKTP = findViewById(R.id.no_ktp_customer);
        nama = findViewById(R.id.nama);
        alamat = findViewById(R.id.alamat);
        ttl = findViewById(R.id.ttl);
        noHp = findViewById(R.id.no_hp);
        pekerjaan = findViewById(R.id.pekerjaan);
        agama = findViewById(R.id.agama);
        whatsapp = findViewById(R.id.whatsapp);
        instagram = findViewById(R.id.instagram);
        facebook = findViewById(R.id.facebook);


        fillDetail();
    }

    private void fillDetail() {

        Bundle data = getIntent().getExtras();
        Customer customer = data.getParcelable(DATA_CUSTOMER);
        String coba = data.getString("coba");


        noKTP.setText(customer.getNoKtp());
        nama.setText(customer.getNama());
        alamat.setText(customer.getAlamat());

        SimpleDateFormat df = new SimpleDateFormat("dd MMMM yyyy", new Locale("ID"));
        SimpleDateFormat sqlformat = new SimpleDateFormat("yyyy-MM-dd", new Locale("EN"));
        try {
            ttl.setText(df.format(sqlformat.parse(customer.getTtl())));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        noHp.setText(customer.getNoTelp());
        pekerjaan.setText(customer.getPekerjaan());
        agama.setText(customer.getAgama());
        whatsapp.setText(customer.getWhatsapp());
        instagram.setText(customer.getInstagram());
        facebook.setText(customer.getFacebook());

        final String fb = "http://facebook.com/" + facebook.getText().toString();
        final String ig = "http://instagram.com/" + instagram.getText().toString();
        final String wa = "http://whatsapp.com/" + whatsapp.getText().toString();


        whatsapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, wa);
                sendIntent.setType("text/plain");
                startActivity(sendIntent);

            }
        });

        facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(fb)));


            }
        });


        instagram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(ig));
                startActivity(i);


            }
        });

    }


}
