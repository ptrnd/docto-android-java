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

public class LoginActivity extends AppCompatActivity {

    private Call<List<User>> loginUser;

    EditText inputUsername;
    EditText inputPassword;
    public String usernametxt;
    public String passwordtxt;
    Button loginBtn;
    Button registerBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        inputUsername = (EditText) findViewById(R.id.inputUsername);
        inputPassword = (EditText) findViewById(R.id.inputPassword);
        loginBtn = (Button) findViewById(R.id.loginBtn);
        registerBtn = (Button) findViewById(R.id.bt_RegisterFromLogin);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                usernametxt = inputUsername.getText().toString();
                passwordtxt = inputPassword.getText().toString();
                if (validate(usernametxt, passwordtxt)){
                    Log.d("username", usernametxt);
                    Log.d("password", passwordtxt);
                    doLogin(usernametxt, passwordtxt);
                }
            }
        });

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    public boolean validate(String username, String password){
        if (TextUtils.isEmpty(username)){
            Toast.makeText(LoginActivity.this, "Username Belum Terisi.", Toast.LENGTH_SHORT).show();
            return false;
        } else if (TextUtils.isEmpty(password)){
            Toast.makeText(LoginActivity.this, "Password Belum Terisi.", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            return true;
        }
    }

    public void doLogin(String username, String password){
        UserService userService = ApiClient.getClient().create(UserService.class);
        loginUser = userService.login(username, password);
        loginUser.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                if (response.isSuccessful()){
                    List<User> user = response.body();

                    SharedPreferences sp = getSharedPreferences("Login", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putString("ID_USER", user.get(0).getIdUser());
                    editor.putString("NAMA_USER", user.get(0).getNama());
                    editor.putString("USERNAME", user.get(0).getUsername());
                    editor.putString("EMAIL", user.get(0).getEmail());
                    editor.apply();

                    Toast.makeText(LoginActivity.this, "Login Success.", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), SearchActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(LoginActivity.this, "Error! Please try again.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Toast.makeText(LoginActivity.this, "Password atau username salah.", Toast.LENGTH_SHORT).show();
//                Toast.makeText(LoginActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}