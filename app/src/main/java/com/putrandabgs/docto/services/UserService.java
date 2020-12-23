package com.putrandabgs.docto.services;

import com.putrandabgs.docto.model.Booking;
import com.putrandabgs.docto.model.History;
import com.putrandabgs.docto.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface UserService {
    @GET("login/{username}/{password}")
    Call<List<User>> login(@Path("username") String username, @Path("password") String password);

    @GET("user/{id_user}")
    Call<List<User>> getUserById(@Path("id_user") String idUser);

    @GET("user/{id_user}/riwayat")
    Call<List<History>> getUserRiwayat(@Path("id_user") String idUser);

    @FormUrlEncoded
    @POST("user/tambah")
    Call<User> tambahUser(
            @Field(value = "nama") String nama_user,
            @Field(value = "email") String email,
            @Field(value = "username") String username,
            @Field(value = "password") String password,
            @Field(value = "telp") String telp,
            @Field(value = "alamat") String alamat
    );
}
