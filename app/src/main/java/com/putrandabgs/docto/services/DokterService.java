package com.putrandabgs.docto.services;

import com.putrandabgs.docto.Dokter;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface DokterService {
    @GET("dokter")
    Call<List<Dokter>> getAllDokter();

    @GET("dokter/{id}")
    Call<List<Dokter>> getDokterById(@Path("iddokter") String idDokter);
}
