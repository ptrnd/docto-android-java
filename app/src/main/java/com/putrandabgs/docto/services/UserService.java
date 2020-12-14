package com.putrandabgs.docto.services;

import com.putrandabgs.docto.model.Dokter;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface UserService {
    @GET("user/{id}")
    Call<List<Dokter>> getUserById(@Path("iduser") String idUser);
}
