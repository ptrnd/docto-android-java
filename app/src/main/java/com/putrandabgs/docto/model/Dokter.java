package com.putrandabgs.docto.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Dokter {
    @SerializedName("id_dokter")
    private String idDokter;

    @SerializedName("nama_dokter")
    private String namaDokter;

    @SerializedName("spesialisasi")
    private String spesialisasi;

    @SerializedName("alamat")
    private String alamat;

    @SerializedName("telp")
    private String telp;

    @SerializedName("keterangan")
    private String keterangan;

    public Dokter() {
    }

    public Dokter(String idDokter, String namaDokter, String spesialisasi, String alamat, String telp, String keterangan) {
        this.idDokter = idDokter;
        this.namaDokter = namaDokter;
        this.spesialisasi = spesialisasi;
        this.alamat = alamat;
        this.telp = telp;
        this.keterangan = keterangan;
    }

    public String  getIdDokter() {
        return idDokter;
    }

    public void setIdDokter(String idDokter) {
        this.idDokter = idDokter;
    }

    public String getNamaDokter() {
        return namaDokter;
    }

    public void setNamaDokter(String namaDokter) {
        this.namaDokter = namaDokter;
    }

    public String getSpesialisasi() {
        return spesialisasi;
    }

    public void setSpesialisasi(String spesialisasi) {
        this.spesialisasi = spesialisasi;
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

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }
}
