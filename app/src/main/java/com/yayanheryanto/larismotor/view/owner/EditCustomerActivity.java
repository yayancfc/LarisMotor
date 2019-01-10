package com.yayanheryanto.larismotor.view.owner;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.yayanheryanto.larismotor.R;
import com.yayanheryanto.larismotor.model.Customer;
import com.yayanheryanto.larismotor.retrofit.ApiClient;
import com.yayanheryanto.larismotor.retrofit.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.yayanheryanto.larismotor.config.config.DATA_CUSTOMER;

public class EditCustomerActivity extends AppCompatActivity implements View.OnClickListener {


    private TextView noKTP, nama, alamat, noTelp, whatsapp, instagram, facebook;
    private Button btnSave;

    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_customer);


        noKTP = findViewById(R.id.noKtp);
        nama = findViewById(R.id.namaCustomer);
        alamat = findViewById(R.id.alamat);
        noTelp = findViewById(R.id.no_telp);
        whatsapp = findViewById(R.id.noWa);
        instagram = findViewById(R.id.instagram);
        facebook = findViewById(R.id.fb);
        btnSave = findViewById(R.id.btnSave);
        btnSave.setOnClickListener(this);

        initProgressDialog();
        fillDetail();
    }

    private void fillDetail() {

        Bundle data = getIntent().getExtras();
        Customer customer = data.getParcelable(DATA_CUSTOMER);


        noKTP.setText(customer.getNoKtp());
        nama.setText(customer.getNama());
        alamat.setText(customer.getAlamat());

        noTelp.setText(customer.getNoTelp());

        whatsapp.setText(customer.getWhatsapp());

        instagram.setText(customer.getInstagram());

        facebook.setText(customer.getFacebook());

    }

    @Override
    public void onClick(View view) {
        if (view == btnSave) {
            updateCustomer();
        }
    }

    private void initProgressDialog() {
        dialog = new ProgressDialog(this);
        dialog.setTitle("Loading");
        dialog.setMessage("Sedang Memproses..");
        dialog.setCancelable(false);
    }

    private void updateCustomer() {

        dialog.show();
        String ktp = noKTP.getText().toString();
        String nomor = noTelp.getText().toString();
        String wa = whatsapp.getText().toString();
        String fb = facebook.getText().toString();
        String ig = instagram.getText().toString();

        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<Customer> call = apiInterface.updateCustomer(ktp, nomor, wa, fb, ig);
        call.enqueue(new Callback<Customer>() {
            @Override
            public void onResponse(Call<Customer> call, Response<Customer> response) {
                dialog.dismiss();
                if (response.body().getMessage().equalsIgnoreCase("success")) {
                    Intent intent = new Intent(EditCustomerActivity.this, CustomerActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }
            }

            @Override
            public void onFailure(Call<Customer> call, Throwable t) {
                dialog.dismiss();
                t.printStackTrace();
                Toast.makeText(EditCustomerActivity.this, "Terjadi Kesalahan Tidak Terduga", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
