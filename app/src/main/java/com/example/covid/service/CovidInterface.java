package com.example.covid.service;

import com.example.covid.model.covid.CovidResponse;
import com.example.covid.model.rs.RsResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CovidInterface {

    @GET("/api/v1/rekapitulasi_v2/jabar/harian")
    Call<CovidResponse> getKasus();

    @GET("/api/v1/sebaran_v2/jabar/faskes")
    Call<RsResponse> getRujukan();
}
