package com.yayanheryanto.larismotor.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.yayanheryanto.larismotor.R;
import com.yayanheryanto.larismotor.activity.EditMotorActivity;
import com.yayanheryanto.larismotor.activity.LoginActivity;
import com.yayanheryanto.larismotor.model.Motor;
import com.yayanheryanto.larismotor.retrofit.ApiClient;
import com.yayanheryanto.larismotor.retrofit.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.yayanheryanto.larismotor.config.config.ACCESTOKEN;
import static com.yayanheryanto.larismotor.config.config.BASE_URL;
import static com.yayanheryanto.larismotor.config.config.DATA_MOTOR;
import static com.yayanheryanto.larismotor.config.config.ID_USER;
import static com.yayanheryanto.larismotor.config.config.MY_PREFERENCES;


public class MotorAdapter extends RecyclerView.Adapter<MotorAdapter.MotorViewHolder> {


    private Context mContext;
    private List<Motor> mList;
    private Activity parentActivity;
    private MotorAdapter adapter;

    public MotorAdapter(Context mContext, List<Motor> mList, Activity parentActivity) {
        this.mContext = mContext;
        this.mList = mList;
        this.parentActivity = parentActivity;
        this.adapter = this;
    }

    @Override
    public MotorAdapter.MotorViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.motor_card, null, false);
        MotorAdapter.MotorViewHolder adapter = new MotorAdapter.MotorViewHolder(view);

        return adapter;
    }

    @Override
    public void onBindViewHolder(MotorAdapter.MotorViewHolder motorViewHolder, int i) {
        final Motor motor = mList.get(i);
        Picasso.get().load(BASE_URL+"storage/motor/"+motor.getGambar()).into(motorViewHolder.imageMotor);
        motorViewHolder.textHjm.setText(""+motor.getHjm());
        motorViewHolder.textNopol.setText(motor.getNoPolisi());

        motorViewHolder.imgEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, EditMotorActivity.class);
                intent.putExtra(DATA_MOTOR, motor);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(intent);
            }
        });

        motorViewHolder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext, "Detail", Toast.LENGTH_SHORT).show();
            }
        });

        motorViewHolder.imgDeelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog dialog = new AlertDialog.Builder(parentActivity)
                        .setTitle("Konfirmasi Hapus")
                        .setMessage("Hapus Data Motor?")
                        .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                SharedPreferences pref = mContext.getSharedPreferences(MY_PREFERENCES, Context.MODE_PRIVATE);
                                final SharedPreferences.Editor editor = pref.edit();
                                String id = pref.getString(ID_USER, "");
                                String token = pref.getString(ACCESTOKEN, "");

                                ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
                                Call<Motor> call = apiInterface.delete(token, motor.getNoMesin(), motor.getGambar(), motor.getGambar1(), motor.getGambar2());
                                call.enqueue(new Callback<Motor>() {
                                    @Override
                                    public void onResponse(Call<Motor> call, Response<Motor> response) {
                                        if (response.body().getMessage().equals("success")){
                                            mList.remove(motor);
                                            adapter.notifyDataSetChanged();
                                            Toast.makeText(parentActivity, "Data Motor Berhasil Dihapus", Toast.LENGTH_SHORT).show();
                                        }else {
                                            editor.putString(ID_USER,"");
                                            editor.putString(ACCESTOKEN, "");
                                            editor.commit();
                                            Toast.makeText(parentActivity, "Token Tidak Valid", Toast.LENGTH_SHORT).show();
                                            Intent intent = new Intent(parentActivity, LoginActivity.class);
                                            parentActivity.startActivity(intent);
                                            parentActivity.finish();
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<Motor> call, Throwable t) {
                                        t.printStackTrace();
                                        Toast.makeText(parentActivity, "Terjadi Kesalahan Tidak Terduga", Toast.LENGTH_SHORT).show();
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

    public class MotorViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageMotor, imgDeelete, imgEdit;
        private TextView textNopol, textHjm;
        private View view;

        public MotorViewHolder(View itemView) {
            super(itemView);

            this.view = itemView;
            imageMotor = itemView.findViewById(R.id.image);
            imgDeelete = itemView.findViewById(R.id.imgDelete);
            imgEdit = itemView.findViewById(R.id.imgEdit);
            textNopol = itemView.findViewById(R.id.txt_plat);
            textHjm = itemView.findViewById(R.id.txt_hjm);
        }
    }
}
