package com.putrandabgs.docto.model;

import com.google.gson.annotations.SerializedName;

public class User {
    @SerializedName("id_user")
    private String idUser;

    @SerializedName("nama_user")
    private String nama;

    @SerializedName("username")
    private String username;

    @SerializedName("passsword")
    private String password;

    @SerializedName("email")
    private String email;

    @SerializedName("alamat")
    private String alamat;

    @SerializedName("telp")
    private String telp;

    public User() {
    }

    public User(String idUser, String nama, String username, String password, String email, String alamat, String telp) {
        this.idUser = idUser;
        this.nama = nama;
        this.username = username;
        this.password = password;
        this.email = email;
        this.alamat = alamat;
        this.telp = telp;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getTelp() {
        return telp;
    }

    public void setTelp(String telp) {
        this.telp = telp;
    }
}
