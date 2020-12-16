package com.putrandabgs.docto.services;

import com.putrandabgs.docto.model.History;
import com.putrandabgs.docto.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface UserService {
    @GET("login/{username}/{password}")
    Call<List<User>> login(@Path("username") String username, @Path("password") String password);

    @GET("user/{id_user}")
    Call<List<User>> getUserById(@Path("id_user") String idUser);

    @GET("user/{id_user}/riwayat")
    Call<List<History>> getUserRiwayat(@Path("id_user") String idUser);
}
