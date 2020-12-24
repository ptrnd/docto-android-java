package com.putrandabgs.docto;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.putrandabgs.docto.adapter.SearchAdapter;
import com.putrandabgs.docto.model.Dokter;
import com.putrandabgs.docto.services.DokterService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchActivity extends AppCompatActivity {

    private RecyclerView DataDokterRV;
    private SearchAdapter searchAdapter;
    private Call<List<Dokter>> listDokter;
    private Call<List<Dokter>> cariDokter;
    private BottomNavigationView bottomNavigation;

    EditText cariEdit;
    Button cariBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        cariBtn = findViewById(R.id.bt_cari);
        cariEdit = findViewById(R.id.et_cari);
        bottomNavigation = findViewById(R.id.bottomNavView);
        bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_listDokter:
                        Intent intent = new Intent(getApplicationContext(), SearchActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                        finish();
                        return true;
                    case R.id.navigation_profile:
                        Intent intent2 = new Intent(getApplicationContext(), ProfileActivity.class);
                        intent2.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent2);
                        finish();
                        return true;
                }
                return false;
            }
        });

        DokterService dokterService = ApiClient.getClient().create(DokterService.class);
        listDokter = dokterService.getAllDokter();
        listDokter.enqueue(new Callback<List<Dokter>>() {
            @Override
            public void onResponse(Call<List<Dokter>> call, Response<List<Dokter>> response) {
                generateDataList(response.body());
            }

            @Override
            public void onFailure(Call<List<Dokter>> call, Throwable t) {
                Log.e("Error", t.getMessage());
            }
        });

        cariBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cari = cariEdit.getText().toString();
                cariDokter = dokterService.getDokterByKey(cari);
                cariDokter.enqueue(new Callback<List<Dokter>>() {
                    @Override
                    public void onResponse(Call<List<Dokter>> call, Response<List<Dokter>> response) {
                        if (response.isSuccessful()){
                            refreshDataList(response.body());
                        } else {
                            Toast.makeText(SearchActivity.this, "Pencarian tidak ditemukan", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Dokter>> call, Throwable t) {
                        Log.e("Error", t.getMessage());
                        Toast.makeText(SearchActivity.this, "Pencarian tidak ditemukan", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    //dibuat di awal2
    private void generateDataList(List<Dokter> listDokter){
        searchAdapter = new SearchAdapter(listDokter, this);
        DataDokterRV = findViewById(R.id.DataDokterRV);
        DataDokterRV.setLayoutManager(new LinearLayoutManager(this));
        DataDokterRV.setAdapter(searchAdapter);
    }

    //dibuat saat mencari data
    private void refreshDataList(List<Dokter> listDokter){
        searchAdapter.clear();
        searchAdapter.notifyDataSetChanged();
        searchAdapter = new SearchAdapter(listDokter, this);
        DataDokterRV = findViewById(R.id.DataDokterRV);
        DataDokterRV.setLayoutManager(new LinearLayoutManager(this));
        DataDokterRV.setAdapter(searchAdapter);
    }
}