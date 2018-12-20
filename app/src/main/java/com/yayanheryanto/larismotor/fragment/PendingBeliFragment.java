package com.yayanheryanto.larismotor.fragment;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.yayanheryanto.larismotor.R;
import com.yayanheryanto.larismotor.adapter.PendingBeliAdapter;
import com.yayanheryanto.larismotor.model.Pending;
import com.yayanheryanto.larismotor.retrofit.ApiClient;
import com.yayanheryanto.larismotor.retrofit.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class PendingBeliFragment extends Fragment {

    private RecyclerView recyclerView;
    private PendingBeliAdapter adapter;
    private LinearLayoutManager layoutManager;
    private ProgressDialog dialog;

    public PendingBeliFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view;
        view = inflater.inflate(R.layout.fragment_pending_beli, container, false);

        initProgressDialog();
        recyclerView = view.findViewById(R.id.rvPendingBeli);

        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        getPendingBeli();

        return view;
    }

    private void getPendingBeli() {
        dialog.show();
        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<List<Pending>> call = apiInterface.getPendingBeli();
        call.enqueue(new Callback<List<Pending>>() {
            @Override
            public void onResponse(Call<List<Pending>> call, Response<List<Pending>> response) {
                dialog.dismiss();
                List<Pending> list = response.body();

                adapter = new PendingBeliAdapter(getContext(),list, getFragmentManager());
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Pending>> call, Throwable t) {
                dialog.dismiss();
                t.printStackTrace();
                Toast.makeText(getContext(), "Terjadi Kesalahan Tidak Terduga", Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void initProgressDialog() {
        dialog = new ProgressDialog(getContext());
        dialog.setTitle("Loading");
        dialog.setMessage("Sedang Memproses..");
        dialog.setCancelable(false);
    }

}