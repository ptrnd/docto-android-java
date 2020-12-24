package com.putrandabgs.docto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.putrandabgs.docto.model.Dokter;
import com.putrandabgs.docto.model.History;
import com.putrandabgs.docto.model.User;
import com.putrandabgs.docto.services.UserService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailUserActivity extends AppCompatActivity {

    TextView tv_namaLengkap;
    TextView tv_email;
    TextView tv_username;
    TextView tv_alamat;
    TextView tv_telp;
    Button bt_kembali;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_user);

        tv_namaLengkap = (TextView) findViewById(R.id.namaLengkapDetail);
        tv_username = (TextView) findViewById(R.id.userNameDetail);
        tv_email = (TextView) findViewById(R.id.emailDetail);
        tv_alamat = (TextView) findViewById(R.id.alamatUserDetail);
        tv_telp = (TextView) findViewById(R.id.telpUserDetail);
        bt_kembali = (Button) findViewById(R.id.kembaliDetailBtn);

        SharedPreferences sp = this.getSharedPreferences("Login", MODE_PRIVATE);

        String idUserSP = sp.getString("ID_USER", null);
        String namaLengkapSP = sp.getString("NAMA_USER", null);
        String usernameSP = sp.getString("USERNAME", null);
        String emailUserSP = sp.getString("EMAIL", null);

        tv_namaLengkap.setText(namaLengkapSP);
        tv_username.setText(usernameSP);
        tv_email.setText(emailUserSP);

        UserService userService = ApiClient.getClient().create(UserService.class);
        Call<List<User>> userDetail = userService.getUserById(idUserSP);
        userDetail.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                List<User> user = response.body();
                tv_alamat.setText(user.get(0).getAlamat());
                tv_telp.setText(user.get(0).getTelp());
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                Log.e("Error", t.getMessage());
            }
        });

        bt_kembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), ProfileActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
            }
        });
    }
}