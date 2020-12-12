package com.putrandabgs.docto;

import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("iduser")
    private int idUser;

    @SerializedName("namaUser")
    private String nama;

    @SerializedName("username")
    private String username;

    @SerializedName("passsword")
    private String password;

    @SerializedName("alamat")
    private String alamat;

    @SerializedName("telp")
    private String telp;

    public User() {
    }

    public User(int idUser, String nama, String username, String password, String alamat, String telp) {
        this.idUser = idUser;
        this.nama = nama;
        this.username = username;
        this.password = password;
        this.alamat = alamat;
        this.telp = telp;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
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
