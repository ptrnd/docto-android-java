package com.putrandabgs.docto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.putrandabgs.docto.model.User;
import com.putrandabgs.docto.services.UserService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    EditText etNamaLengkap;
    EditText etEmail;
    EditText etUsername;
    EditText etPassword;
    EditText etConfirmPass;
    EditText etAlamat;
    EditText etTelp;
    Button btRegister;
    Button btLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etNamaLengkap = (EditText) findViewById(R.id.et_regNama);
        etEmail = (EditText) findViewById(R.id.et_regEmail);
        etUsername = (EditText) findViewById(R.id.et_regUsername);
        etPassword = (EditText) findViewById(R.id.et_regPassword);
        etConfirmPass = (EditText) findViewById(R.id.et_regConfirmPass);
        etAlamat = (EditText) findViewById(R.id.et_regAlamat);
        etTelp = (EditText) findViewById(R.id.et_regTelp);
        btRegister = (Button) findViewById(R.id.bt_Register);
        btLogin = (Button) findViewById(R.id.bt_backToLogin);

        btRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String namaLengkap = etNamaLengkap.getText().toString();
                String email = etEmail.getText().toString();
                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();
                String confirmPass = etConfirmPass.getText().toString();
                String alamat = etAlamat.getText().toString();
                String telp = etTelp.getText().toString();

                if (validate(namaLengkap, email, username, password, confirmPass, alamat, telp)){
                    kirimDataUser(namaLengkap, email, username, password, confirmPass, alamat, telp);
                }
            }
        });
    }

    public boolean validate(String namaLengkap, String email, String username, String password, String confirmPass, String alamat, String telp){
        if (TextUtils.isEmpty(namaLengkap)){
            Toast.makeText(RegisterActivity.this, "Nama Belum Terisi.", Toast.LENGTH_SHORT).show();
            return false;
        } else if (TextUtils.isEmpty(email)){
            Toast.makeText(RegisterActivity.this, "Email Belum Terisi.", Toast.LENGTH_SHORT).show();
            return false;
        } else if (TextUtils.isEmpty(username)){
            Toast.makeText(RegisterActivity.this, "Username Belum Terisi.", Toast.LENGTH_SHORT).show();
            return false;
        } else if (TextUtils.isEmpty(password)){
            Toast.makeText(RegisterActivity.this, "Password Belum Terisi.", Toast.LENGTH_SHORT).show();
            return false;
        } else if (TextUtils.isEmpty(confirmPass)){
            Toast.makeText(RegisterActivity.this, "Confirm Password Belum Terisi.", Toast.LENGTH_SHORT).show();
            return false;
        } else if (!password.equals(confirmPass)){
            Toast.makeText(RegisterActivity.this, "Password dan Confirm Password tidak sama.", Toast.LENGTH_SHORT).show();
            return false;
        } else if (TextUtils.isEmpty(alamat)){
            Toast.makeText(RegisterActivity.this, "Alamat Belum Terisi.", Toast.LENGTH_SHORT).show();
            return false;
        } else if (TextUtils.isEmpty(telp)){
            Toast.makeText(RegisterActivity.this, "Telepon Belum Terisi.", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            return true;
        }
    }

    public void kirimDataUser(String namaLengkap, String email, String username, String password, String confirmPass, String alamat, String telp){
        UserService userService = ApiClient.getClient().create(UserService.class);
        Call<User> sendDataUser = userService.tambahUser(namaLengkap, email, username, password, telp, alamat);

        sendDataUser.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                Toast.makeText(RegisterActivity.this, "Data telah tersimpan.", Toast.LENGTH_SHORT).show();
                login(username, password);
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.e("Error", t.getMessage());
            }
        });
    }

    public void login(String username, String password){
        UserService userService = ApiClient.getClient().create(UserService.class);
        Call<List<User>> loginUser = userService.login(username, password);

        loginUser.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                SharedPreferences sp = getSharedPreferences("Login", MODE_PRIVATE);
                List<User> user = response.body();

                SharedPreferences.Editor editor = sp.edit();
                editor.putString("ID_USER", user.get(0).getIdUser());
                editor.putString("NAMA_USER", user.get(0).getNama());
                editor.putString("USERNAME", user.get(0).getUsername());
                editor.putString("EMAIL", user.get(0).getEmail());
                editor.apply();

                Intent intent = new Intent(getApplicationContext(), SearchActivity.class);
                startActivity(intent);
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                Log.e("Error", t.getMessage());
            }
        });
    }
}