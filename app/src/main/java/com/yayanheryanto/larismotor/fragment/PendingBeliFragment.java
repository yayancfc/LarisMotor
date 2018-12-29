package com.yayanheryanto.larismotor.fragment;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.yayanheryanto.larismotor.R;
import com.yayanheryanto.larismotor.adapter.PendingBeliAdapter;
import com.yayanheryanto.larismotor.model.PendingBeli;
import com.yayanheryanto.larismotor.retrofit.ApiClient;
import com.yayanheryanto.larismotor.retrofit.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.yayanheryanto.larismotor.config.config.ID_USER;
import static com.yayanheryanto.larismotor.config.config.MY_PREFERENCES;

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
        SharedPreferences pref = this.getActivity().getSharedPreferences(MY_PREFERENCES, Context.MODE_PRIVATE);
        String id = pref.getString(ID_USER, "");
        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<List<PendingBeli>> call = apiInterface.getPendingBeli(id);
        call.enqueue(new Callback<List<PendingBeli>>() {
            @Override
            public void onResponse(Call<List<PendingBeli>> call, Response<List<PendingBeli>> response) {
                dialog.dismiss();
                List<PendingBeli> list = response.body();

                adapter = new PendingBeliAdapter(getContext(), list, getFragmentManager());
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<PendingBeli>> call, Throwable t) {
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