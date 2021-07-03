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
import com.example.covid.adapter.CovidAdapter;
import com.example.covid.model.covid.ContentItem;
import com.example.covid.service.CovidAPI;
import com.example.covid.service.CovidListener;

import java.util.ArrayList;
import java.util.List;

public class CovidActivity extends Fragment {

    RecyclerView rvCovid;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.activity_covid, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rvCovid = view.findViewById(R.id.rv_covid);

        new CovidAPI().getCovid(covidListener);
    }

    CovidListener<List<ContentItem>> covidListener = new CovidListener<List<ContentItem>>(){

        @Override
        public void onSuccess(List<ContentItem> items) {

            ArrayList<ContentItem> data = new ArrayList<>();

            for(int i = 0; i < items.size(); i++){
                if(!items.get(i).getTanggal().contains("Qualifying")){
                    data.add(items.get(i));
                }
                Log.d("Hasil : ", items.get(i).getTanggal());

            }

            final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
            linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            rvCovid.setLayoutManager(linearLayoutManager);
            CovidAdapter rvAdapter = new CovidAdapter(data);
            rvCovid.setAdapter(rvAdapter);
        }

        @Override
        public void onFailed(String msg) {
            Log.d("Maad ada kesalahan", msg);
        }
    };
}