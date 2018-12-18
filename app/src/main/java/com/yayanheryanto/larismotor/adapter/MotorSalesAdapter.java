package com.yayanheryanto.larismotor.adapter;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.yayanheryanto.larismotor.R;
import com.yayanheryanto.larismotor.view.owner.DetailMotorActivity;
import com.yayanheryanto.larismotor.view.sales.EditMotorSalesActivity;
import com.yayanheryanto.larismotor.model.Motor;

import java.util.List;

import static com.yayanheryanto.larismotor.config.config.BASE_URL;
import static com.yayanheryanto.larismotor.config.config.DATA_MOTOR;

public class MotorSalesAdapter extends RecyclerView.Adapter<MotorSalesAdapter.MotorViewHolder>{


    private Context mContext;
    private List<Motor> mList;
    private Activity parentActivity;
    private MotorSalesAdapter adapter;
    private ProgressDialog progressDialog = null;

    public MotorSalesAdapter(Context mContext, List<Motor> mList, Activity parentActivity) {
        this.mContext = mContext;
        this.mList = mList;
        this.parentActivity = parentActivity;
        this.adapter = this;
    }


    @NonNull
    @Override
    public MotorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.motor_sales_card, null, false);
        MotorSalesAdapter.MotorViewHolder adapter = new MotorSalesAdapter.MotorViewHolder(view);

        return adapter;
    }


    private void initProgressDialog() {
        progressDialog = new ProgressDialog(parentActivity);
        progressDialog.setTitle("Loading");
        progressDialog.setMessage("Sedang Memproses..");
        progressDialog.setCancelable(false);
    }



    @Override
    public void onBindViewHolder(@NonNull MotorViewHolder holder, int position) {
        initProgressDialog();
        final Motor motor = mList.get(position);
        Picasso.get()
                .load(BASE_URL+"storage/motor/"+motor.getGambar())
                .into(holder.imageMotor);
        holder.textHjm.setText(""+motor.getHarga());
        holder.textNopol.setText(motor.getNoPolisi());


        holder.imgEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, EditMotorSalesActivity.class);
                intent.putExtra(DATA_MOTOR, motor);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(intent);
            }
        });

        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, DetailMotorActivity.class);
                intent.putExtra(DATA_MOTOR, motor);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(intent);
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
