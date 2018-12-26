package com.yayanheryanto.larismotor.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.yayanheryanto.larismotor.R;
import com.yayanheryanto.larismotor.view.pending.DetailPendingBeliActivity;
import com.yayanheryanto.larismotor.view.pending.EditPendingBeliActivity;
import com.yayanheryanto.larismotor.view.LoginActivity;
import com.yayanheryanto.larismotor.view.pending.PendingTransaksiActivity;
import com.yayanheryanto.larismotor.model.Pending;
import com.yayanheryanto.larismotor.retrofit.ApiClient;
import com.yayanheryanto.larismotor.retrofit.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.yayanheryanto.larismotor.config.config.ACCESTOKEN;
import static com.yayanheryanto.larismotor.config.config.DATA_PENDING;
import static com.yayanheryanto.larismotor.config.config.ID_USER;
import static com.yayanheryanto.larismotor.config.config.MY_PREFERENCES;

public class PendingBeliAdapter extends RecyclerView.Adapter<PendingBeliAdapter.PendingViewHolder>{


    private Context mContext;
    private List<Pending> mList;
    private FragmentManager parentActivity;
    private PendingBeliAdapter adapter;
    private ProgressDialog progressDialog = null;

    public PendingBeliAdapter(Context mContext, List<Pending> mList, FragmentManager parentActivity) {
        this.mContext = mContext;
        this.mList = mList;
        this.parentActivity = parentActivity;
        this.adapter = this;
    }

    @Override
    public PendingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.pending_beli, null, false);
        PendingBeliAdapter.PendingViewHolder adapter = new PendingBeliAdapter.PendingViewHolder(view);

        return adapter;
    }


    private void initProgressDialog() {
        progressDialog = new ProgressDialog((PendingTransaksiActivity)mContext);
        progressDialog.setTitle("Loading");
        progressDialog.setMessage("Sedang Memproses..");
        progressDialog.setCancelable(false);
    }


    @Override
    public void onBindViewHolder(@NonNull PendingViewHolder holder, int position) {
        initProgressDialog();
        final Pending pending = mList.get(position);
        holder.txtNama.setText(pending.getNama());
        holder.txtNamaMotor.setText(pending.getNamaMerk() + " " + pending.getNamaTipe());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(mContext, DetailPendingBeliActivity.class);
                intent.putExtra(DATA_PENDING, pending);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(intent);
            }
        });

        holder.imgEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, EditPendingBeliActivity.class);
                intent.putExtra(DATA_PENDING, pending);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(intent);
            }
        });

        holder.imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog dialog = new AlertDialog.Builder(mContext)
                        .setTitle("Konfirmasi Hapus")
                        .setMessage("Hapus Data Pending?")
                        .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                progressDialog.show();
                                SharedPreferences pref = mContext.getSharedPreferences(MY_PREFERENCES, Context.MODE_PRIVATE);
                                final SharedPreferences.Editor editor = pref.edit();
                                String id = pref.getString(ID_USER, "");
                                String token = pref.getString(ACCESTOKEN, "");

                                ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
                                Call<Pending> call = apiInterface.deletePending(token, pending.getIdPending());
                                call.enqueue(new Callback<Pending>() {
                                    @Override
                                    public void onResponse(Call<Pending> call, Response<Pending> response) {
                                        progressDialog.dismiss();
                                        if (response.body().getMessage().equals("success")){
                                            mList.remove(pending);
                                            adapter.notifyDataSetChanged();
                                            Toast.makeText(mContext, "Pending Berhasil Dihapus", Toast.LENGTH_SHORT).show();
                                        }else {
                                            editor.putString(ID_USER,"");
                                            editor.putString(ACCESTOKEN, "");
                                            editor.commit();
                                            Toast.makeText(mContext, "Token Tidak Valid", Toast.LENGTH_SHORT).show();
                                            Intent intent = new Intent(mContext, LoginActivity.class);
                                            mContext.startActivity(intent);
                                            ((Activity)mContext).finish();
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<Pending> call, Throwable t) {
                                        progressDialog.dismiss();
                                        t.printStackTrace();
                                        Toast.makeText(mContext, "Terjadi Kesalahan Tidak Terduga", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        })
                        .setNegativeButton("Batal", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        })
                        .create();
                dialog.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class PendingViewHolder extends RecyclerView.ViewHolder {

        private TextView txtNama, txtNamaMotor;
        private ImageView imgDelete, imgEdit;
        private View mView;

        public PendingViewHolder(View itemView) {
            super(itemView);

            this.mView = itemView;
            txtNama = itemView.findViewById(R.id.txt_nama);
            txtNamaMotor = itemView.findViewById(R.id.txt_nama_motor);
            imgDelete = itemView.findViewById(R.id.imgDelete);
            imgEdit = itemView.findViewById(R.id.imgEdit);
        }
    }
}
