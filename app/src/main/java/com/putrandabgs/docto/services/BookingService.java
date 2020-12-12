package com.putrandabgs.docto.services;

import com.putrandabgs.docto.Booking;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface BookingService {
    @GET("booking/{id}")
    Call<List<Booking>> getBookingBtId(@Path("idBooking") String idBooking);

    @POST
}
