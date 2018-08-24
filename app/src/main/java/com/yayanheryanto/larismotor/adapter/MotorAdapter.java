package com.yayanheryanto.larismotor.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.yayanheryanto.larismotor.R;
import com.yayanheryanto.larismotor.model.MotorModel;

import java.util.List;

public class MotorAdapter extends RecyclerView.Adapter<MotorAdapter.MotorViewHolder> {


    private Context mContext;
    private List<MotorModel> mList;

    public MotorAdapter(Context mContext, List<MotorModel> mList) {
        this.mContext = mContext;
        this.mList = mList;
    }

    @Override
    public MotorAdapter.MotorViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.motor_card, null, false);
        MotorAdapter.MotorViewHolder adapter = new MotorAdapter.MotorViewHolder(view);

        return adapter;
    }

    @Override
    public void onBindViewHolder(@NonNull MotorAdapter.MotorViewHolder motorViewHolder, int i) {
        final MotorModel motor = mList.get(i);
        motorViewHolder.imageMotor.setImageResource(motor.getImage());
        motorViewHolder.textHjm.setText(motor.getHjm());
        motorViewHolder.textNopol.setText(motor.getNopol());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class MotorViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageMotor;
        private TextView textNopol, textHjm;
        private View view;

        public MotorViewHolder(@NonNull View itemView) {
            super(itemView);

            this.view = itemView;
            imageMotor = itemView.findViewById(R.id.image);
            textNopol = itemView.findViewById(R.id.txt_plat);
            textHjm = itemView.findViewById(R.id.txt_hjm);
        }
    }
}
