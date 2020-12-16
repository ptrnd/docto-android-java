package com.putrandabgs.docto.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class History {

    @SerializedName("id_user")
    @Expose
    private Integer idUser;

    @SerializedName("tanggal")
    @Expose
    private String tanggal;

    @SerializedName("id_dokter")
    @Expose
    private Integer idDokter;

    @SerializedName("nama_dokter")
    @Expose
    private String namaDokter;

    @SerializedName("alamat")
    @Expose
    private String alamat;

    @SerializedName("spesialisasi")
    @Expose
    private String spesialisasi;

    @SerializedName("telp")
    @Expose
    private String telp;

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
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

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getSpesialisasi() {
        return spesialisasi;
    }

    public void setSpesialisasi(String spesialisasi) {
        this.spesialisasi = spesialisasi;
    }

    public String getTelp() {
        return telp;
    }

    public void setTelp(String telp) {
        this.telp = telp;
    }

}
