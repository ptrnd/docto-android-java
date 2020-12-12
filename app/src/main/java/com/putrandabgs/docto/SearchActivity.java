package com.putrandabgs.docto;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Adapter;

import com.putrandabgs.docto.services.DokterService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SearchActivity extends AppCompatActivity {

    List<Dokter> listDokter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://5e6677082aea440016afbf3b.mockapi.io/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RecyclerView recyclerView = findViewById(R.id.DataDokterRV);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        DokterService service = retrofit.create(DokterService.class);

        service.getAllDokter().enqueue(new Callback<List<Dokter>>() {
            @Override
            public void onResponse(Call<List<Dokter>> call, Response<List<Dokter>> response) {
                listDokter = response.body();
                DokterAdapter dokterAdapter = new DokterAdapter(listDokter);
                recyclerView.setAdapter(dokterAdapter);
            }

            @Override
            public void onFailure(Call<List<Dokter>> call, Throwable t) {
                Log.e("Error", t.getMessage());
            }
        });
    }


}