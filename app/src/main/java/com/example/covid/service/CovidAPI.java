package com.example.covid.service;

import com.example.covid.model.covid.ContentItem;
import com.example.covid.model.covid.CovidResponse;
import com.example.covid.model.rs.DataItem;
import com.example.covid.model.rs.RsResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CovidAPI {

    private Retrofit retrofit;

    private static final String URL_BASE = "https://covid19-public.digitalservice.id";

    public CovidInterface getAPI(){
        if (retrofit == null){
            retrofit = new Retrofit
                    .Builder()
                    .baseUrl(URL_BASE)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit.create(CovidInterface.class);
    }

    public void getCovid(final CovidListener<List<ContentItem>> listener){
        getAPI().getKasus().enqueue(new Callback<CovidResponse>() {
            @Override
            public void onResponse(Call<CovidResponse> call, Response<CovidResponse> response) {
                CovidResponse data = response.body();
                if(data != null && data.getData() != null){
                    listener.onSuccess(data.getData().getContent());
                }
            }
            @Override
            public void onFailure(Call<CovidResponse> call, Throwable t) {
                listener.onFailed(t.getMessage());
            }
        });
    }

    public void getRs(final CovidListener<List<DataItem>> listener){
        getAPI().getRujukan().enqueue(new Callback<RsResponse>() {
            @Override
            public void onResponse(Call<RsResponse> call, Response<RsResponse> response) {
                RsResponse data = response.body();
                if(data != null && data.getData() != null){
                    listener.onSuccess(data.getData());
                }
            }
            @Override
            public void onFailure(Call<RsResponse> call, Throwable t) {
                listener.onFailed(t.getMessage());
            }
        });
    }



}
