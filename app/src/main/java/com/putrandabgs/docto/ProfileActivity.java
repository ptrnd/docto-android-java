package com.putrandabgs.docto;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.putrandabgs.docto.adapter.HistoryAdapter;
import com.putrandabgs.docto.model.History;
import com.putrandabgs.docto.services.UserService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileActivity extends AppCompatActivity {

    private HistoryAdapter historyAdapter;
    private RecyclerView dataHistoryRV;
    private Call<List<History>> listHistory;
    private BottomNavigationView bottomNavigation;

    TextView namaUser;
    TextView emailUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        bottomNavigation = findViewById(R.id.bottomNavView);

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

        namaUser = findViewById(R.id.namaUserProfil);
        emailUser = findViewById(R.id.emailUserProfil);

        SharedPreferences sp1 = this.getSharedPreferences("Login", MODE_PRIVATE);

        String idUserSP = sp1.getString("ID_USER", null);
        String namaUserSP = sp1.getString("USERNAME", null);
        String emailUserSP = sp1.getString("EMAIL", null);

        namaUser.setText(namaUserSP);
        emailUser.setText(emailUserSP);

        UserService userService = ApiClient.getClient().create(UserService.class);
        listHistory = userService.getUserRiwayat(idUserSP);

        listHistory.enqueue(new Callback<List<History>>() {
            @Override
            public void onResponse(Call<List<History>> call, Response<List<History>> response) {
                generateDataList(response.body());
            }

            @Override
            public void onFailure(Call<List<History>> call, Throwable t) {
                Log.e("Error", t.getMessage());
            }
        });
    }

    private void generateDataList(List<History> listHistory){
        historyAdapter = new HistoryAdapter(listHistory);
        dataHistoryRV = findViewById(R.id.DataHistoryRV);
        dataHistoryRV.setLayoutManager(new LinearLayoutManager(this));
        dataHistoryRV.setAdapter(historyAdapter);
    }
}