package com.putrandabgs.docto.services;

import com.putrandabgs.docto.model.Booking;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface BookingService {
    @GET("booking/{id_booking}")
    Call<List<Booking>> getBookingById(@Path("id_booking") Integer idBooking);

//    @Headers({
//        "Content-Type: application/x-www-form-urlencoded",
//        "Accept: */*",
//        "Connection: keep-alive"})

    @FormUrlEncoded
    @POST("booking/tambah")
    Call<Booking> tambahBooking(
            @Field(value = "id_user") Integer id_user,
            @Field(value = "id_dokter") Integer id_dokter,
            @Field(value = "tanggal") String tanggal
    );

//    @POST("booking/tambah")
//    Call<Booking> tambahBooking(@Body Booking body);
}
