package com.yayanheryanto.larismotor.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yayanheryanto.larismotor.R;
import com.yayanheryanto.larismotor.model.Customer;

import java.util.List;

public class CustomerAdapter extends RecyclerView.Adapter<CustomerAdapter.CustomerViewHolder> {


    private Context mContext;
    private List<Customer> mList;

    public CustomerAdapter(Context mContext, List<Customer> mList) {
        this.mContext = mContext;
        this.mList = mList;
    }

    @Override
    public CustomerAdapter.CustomerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.customer_card, null, false);
        CustomerAdapter.CustomerViewHolder adapter = new CustomerAdapter.CustomerViewHolder(view);

        return adapter;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomerAdapter.CustomerViewHolder customerViewHolder, int i) {
        final Customer customer = mList.get(i);
        customerViewHolder.textNama.setText(customer.getNama());
        customerViewHolder.textTelp.setText(customer.getTelepon());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class CustomerViewHolder extends RecyclerView.ViewHolder {

        private TextView textNama, textTelp;
        private View view;

        public CustomerViewHolder(@NonNull View itemView) {
            super(itemView);

            this.view = itemView;
            textNama = itemView.findViewById(R.id.txt_nama);
            textTelp = itemView.findViewById(R.id.txt_telepon);
        }
    }
}
