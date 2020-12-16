package com.putrandabgs.docto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.putrandabgs.docto.model.Dokter;
import com.putrandabgs.docto.services.DokterService;

import java.net.URISyntaxException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailActivity extends AppCompatActivity {

    private Call<List<Dokter>> listDokter;

    public String id_dokter;
    TextView tvidDokter;
    TextView namaDokter;
    TextView spesialisasi;
    TextView alamat;
    TextView telepon;
    TextView keterangan;
    Button backBtn;
    Button bookingBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        tvidDokter = findViewById(R.id.detailIdDokter);
        namaDokter = findViewById(R.id.namaDokter);
        spesialisasi = findViewById(R.id.spesialis);
        alamat = findViewById(R.id.alamat);
        telepon = findViewById(R.id.telepon);
        keterangan = findViewById(R.id.keteranganIsi);
        backBtn = findViewById(R.id.backBtn);
        bookingBtn = findViewById(R.id.bookingBtn);

        Intent intent = getIntent();
        id_dokter = intent.getStringExtra("id_dokter");
        DokterService dokterService = ApiClient.getClient().create(DokterService.class);

            listDokter = dokterService.getDokterById(id_dokter);

            listDokter.enqueue(new Callback<List<Dokter>>() {
                @Override
                public void onResponse(Call<List<Dokter>> call, Response<List<Dokter>> response) {
                    generateData(response.body());
                }

                @Override
                public void onFailure(Call<List<Dokter>> call, Throwable t) {
                    Log.e("Error", t.getMessage());
                }
            });

            bookingBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getBaseContext(), ConfirmActivity.class);
                    String sendIdDokter = tvidDokter.getText().toString();
                    intent.putExtra("id_dokter", sendIdDokter);
                    startActivity(intent);
                }
            });

            backBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getBaseContext(), SearchActivity.class);
                    startActivity(intent);
                }
            });
        }


    public void generateData(List<Dokter> dokter){
        tvidDokter.setText(dokter.get(0).getIdDokter());
        namaDokter.setText(dokter.get(0).getNamaDokter());
        spesialisasi.setText(dokter.get(0).getSpesialisasi());
        alamat.setText(dokter.get(0).getAlamat());
        telepon.setText(dokter.get(0).getTelp());
        if (dokter.get(0).getKeterangan() != null) {
            keterangan.setText(dokter.get(0).getKeterangan());
        } else {
            keterangan.setText("Belum ada keterangan.");
        }
    }
}