package com.yayanheryanto.larismotor.view.laporan;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.yayanheryanto.larismotor.R;
import com.yayanheryanto.larismotor.adapter.LaporanAdapter;
import com.yayanheryanto.larismotor.model.Transaksi;
import com.yayanheryanto.larismotor.retrofit.ApiClient;
import com.yayanheryanto.larismotor.retrofit.ApiInterface;

import java.util.List;

import de.codecrafters.tableview.TableView;
import de.codecrafters.tableview.model.TableColumnDpWidthModel;
import de.codecrafters.tableview.toolkit.SimpleTableHeaderAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LaporanActivity extends AppCompatActivity {

    private static final String[] TABLE_HEADERS = {"No.", "Tanggal", "Nopol/Nosin", "Pembeli", "Sales", "Kondisi", "Bayar"
    };
    private TableView tableView;
    private LaporanAdapter adapter;
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laporan);


        initProgressDialog();


        tableView = findViewById(R.id.tableView);
        tableView.setHeaderAdapter(new SimpleTableHeaderAdapter(this, TABLE_HEADERS));
        TableColumnDpWidthModel columnModel = new TableColumnDpWidthModel(this, 7, 100);
        columnModel.setColumnWidth(0, 60);
        columnModel.setColumnWidth(1, 120);
        columnModel.setColumnWidth(2, 150);
        columnModel.setColumnWidth(3, 250);
        tableView.setColumnModel(columnModel);

        getTransaksi();

    }

    private void initProgressDialog() {
        dialog = new ProgressDialog(this);
        dialog.setTitle("Sedang Memeriksa");
        dialog.setMessage("Loading..");
        dialog.setCancelable(false);
    }


    private void getTransaksi() {

        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);


        Call<List<Transaksi>> call = apiInterface.getTransaksi(FilterActivity.tanggalDari,FilterActivity.tanggalKe);
        call.enqueue(new Callback<List<Transaksi>>() {
            @Override
            public void onResponse(Call<List<Transaksi>> call, Response<List<Transaksi>> response) {
                dialog.dismiss();
                if (response.body().isEmpty()) {
                    Toast.makeText(LaporanActivity.this, "Data Penjualan Tidak Tersedia", Toast.LENGTH_SHORT).show();
                } else {
                    List<Transaksi> transaksi;
                    transaksi = response.body();

                    int nomor = 1;
                    String pembanding = transaksi.get(0).getTanggal();
                    for (Transaksi transaksiNow : transaksi) {

                        if (nomor != 1) {

                            transaksiNow.setSama(transaksiNow.getTanggal().equals(pembanding));

                        }

                        if (transaksiNow.isSama()) {

                            transaksiNow.setTanggal("");

                        } else {

                            pembanding = transaksiNow.getTanggal();
                        }

                        transaksiNow.setNomor(nomor);
                        nomor++;


                    }

                    adapter = new LaporanAdapter(getApplicationContext(), transaksi);
                    tableView.setDataAdapter(adapter);
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<List<Transaksi>> call, Throwable t) {
                dialog.dismiss();
                Log.e("Error", t.getLocalizedMessage());
                Toast.makeText(LaporanActivity.this, "Terjadi Kesalahan Tidak Terduga", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(LaporanActivity.this, FilterActivity.class));
    }
}
