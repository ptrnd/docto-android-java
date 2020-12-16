package com.putrandabgs.docto.services;

import com.putrandabgs.docto.model.Dokter;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface DokterService {
    @GET("dokter")
    Call<List<Dokter>> getAllDokter();

    @GET("dokter/{id_dokter}")
    Call<List<Dokter>> getDokterById(@Path("id_dokter") String id_dokter);
}
