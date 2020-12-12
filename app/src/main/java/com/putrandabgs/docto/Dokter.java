package com.putrandabgs.docto;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Dokter {
    @SerializedName("iddokter")
    private Integer idDokter;

    @SerializedName("nama")
    private String namaDokter;

    @SerializedName("spesialis")
    private String spesialis;

    @SerializedName("alamat")
    private String alamat;

    @SerializedName("telpon")
    private String telp;

    @SerializedName("keterangan")
    private String keterangan;

    public Dokter() {
    }

    public Dokter(Integer idDokter, String namaDokter, String spesialis, String alamat, String telp, String keterangan) {
        this.idDokter = idDokter;
        this.namaDokter = namaDokter;
        this.spesialis = spesialis;
        this.alamat = alamat;
        this.telp = telp;
        this.keterangan = keterangan;
    }

    public Integer getIdDokter() {
        return idDokter;
    }

    public void setIdDokter(Integer idDokter) {
        this.idDokter = idDokter;
    }

    public String getNamaDokter() {
        return namaDokter;
    }

    public void setNamaDokter(String namaDokter) {
        this.namaDokter = namaDokter;
    }

    public String getSpesialis() {
        return spesialis;
    }

    public void setSpesialis(String spesialis) {
        this.spesialis = spesialis;
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
