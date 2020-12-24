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
import android.view.View;
import android.widget.Button;
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

    TextView nama;
    TextView emailUser;
    TextView userName;
    Button logoutBtn;
    Button detailBtn;

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

        nama = findViewById(R.id.namaUserProfil);
        userName = findViewById(R.id.userNameProfil);
        emailUser = findViewById(R.id.emailUserProfil);
        logoutBtn = findViewById(R.id.logoutBtn);
        detailBtn = findViewById(R.id.detailUserBtn);

        SharedPreferences sp1 = this.getSharedPreferences("Login", MODE_PRIVATE);

        String idUserSP = sp1.getString("ID_USER", null);
        String namaSP = sp1.getString("NAMA_USER", null);
        String usernameSP = sp1.getString("USERNAME", null);
        String emailUserSP = sp1.getString("EMAIL", null);

        nama.setText(namaSP);
        userName.setText(usernameSP);
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

        detailBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), DetailUserActivity.class);
                startActivity(intent);
            }
        });

        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sp = getSharedPreferences("Login", MODE_PRIVATE);
                SharedPreferences.Editor editor = sp.edit();
                editor.remove("ID_USER");
                editor.remove("USERNAME");
                editor.remove("EMAIL");
                editor.apply();

                Intent intent = new Intent(getBaseContext(), LoginActivity.class);
                startActivity(intent);
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