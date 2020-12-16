package com.putrandabgs.docto.model;

import com.google.gson.annotations.SerializedName;

public class Booking {
    @SerializedName("id_booking")
    private Integer idBooking;

    @SerializedName("id_user")
    private Integer idUser;

    @SerializedName("id_dokter")
    private Integer idDokter;

    @SerializedName("tanggal")
    private String tanggal;

    public Booking() {
    }

    public Booking(Integer idBooking, Integer idUser, Integer idDokter, String tanggal) {
        this.idBooking = idBooking;
        this.idUser = idUser;
        this.idDokter = idDokter;
        this.tanggal = tanggal;
    }

    public Booking(Integer idUser, Integer idDokter, String tanggal) {
        this.idUser = idUser;
        this.idDokter = idDokter;
        this.tanggal = tanggal;
    }

    public Integer getIdBooking() {
        return idBooking;
    }

    public void setIdBooking(Integer idBooking) {
        this.idBooking = idBooking;
    }

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public Integer getIdDokter() {
        return idDokter;
    }

    public void setIdDokter(Integer idDokter) {
        this.idDokter = idDokter;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }
}
