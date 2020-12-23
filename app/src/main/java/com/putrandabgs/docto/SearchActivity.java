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
    private BottomNavigationView bottomNavigation;

//    EditText cariEditText;
//    Button cariBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        bottomNavigation = findViewById(R.id.bottomNavView);
//        cariBtn = findViewById(R.id.bt_cari);
//        cariEditText = findViewById(R.id.et_cari);
        bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_listDokter:
                        Intent intent = new Intent(getApplicationContext(), SearchActivity.class);
                        startActivity(intent);
                        return true;
                    case R.id.navigation_profile:
                        Intent intent2 = new Intent(getApplicationContext(), ProfileActivity.class);
                        startActivity(intent2);
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

//        cariBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String cari = cariEditText.getText().toString();
//                listDokter = dokterService.getDokterByKey(cari);
//
//                listDokter.enqueue(new Callback<List<Dokter>>() {
//                    @Override
//                    public void onResponse(Call<List<Dokter>> call, Response<List<Dokter>> response) {
//                        generateDataList(response.body());
//                    }
//
//                    @Override
//                    public void onFailure(Call<List<Dokter>> call, Throwable t) {
//                        Log.e("Error", t.getMessage());
//                    }
//                });
//            }
//        });
    }

    private void generateDataList(List<Dokter> listDokter){
        searchAdapter = new SearchAdapter(listDokter, this);
        DataDokterRV = findViewById(R.id.DataDokterRV);
        DataDokterRV.setLayoutManager(new LinearLayoutManager(this));
        DataDokterRV.setAdapter(searchAdapter);
    }
}