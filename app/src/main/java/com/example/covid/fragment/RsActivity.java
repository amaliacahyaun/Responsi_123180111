package com.example.covid.fragment;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.covid.R;
import com.example.covid.adapter.RsAdapter;
import com.example.covid.model.rs.DataItem;
import com.example.covid.service.CovidAPI;
import com.example.covid.service.CovidListener;

import java.util.ArrayList;
import java.util.List;

public class RsActivity extends Fragment {

    RecyclerView rvRs;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.activity_rs, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rvRs = view.findViewById(R.id.rv_rs);

        new CovidAPI().getRs(rsListener);
    }

    CovidListener<List<DataItem>> rsListener = new CovidListener<List<DataItem>>(){

        @Override
        public void onSuccess(List<DataItem> items) {

            ArrayList<DataItem> data = new ArrayList<>();

            for(int i = 0; i < items.size(); i++){
                if(!items.get(i).getNama().contains("Qualifying")){
                    data.add(items.get(i));
                }
                Log.d("Hasil : ", items.get(i).getNama());

            }

            final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
            linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            rvRs.setLayoutManager(linearLayoutManager);
            RsAdapter rvAdapter = new RsAdapter(data);
            rvRs.setAdapter(rvAdapter);
        }

        @Override
        public void onFailed(String msg) {
            Log.d("Maaf ada kesalahan", msg);
        }
    };

}