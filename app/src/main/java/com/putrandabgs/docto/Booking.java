package com.putrandabgs.docto;

import com.google.gson.annotations.SerializedName;

public class Booking {
    @SerializedName("idbooking")
    private int idBooking;

    @SerializedName("iduser")
    private int idUser;

    @SerializedName("iddokter")
    private int idDokter;

    @SerializedName("tanggal")
    private String tanggal;

    public Booking() {
    }

    public Booking(int idBooking, int idUser, int idDokter, String tanggal) {
        this.idBooking = idBooking;
        this.idUser = idUser;
        this.idDokter = idDokter;
        this.tanggal = tanggal;
    }

    public int getIdBooking() {
        return idBooking;
    }

    public void setIdBooking(int idBooking) {
        this.idBooking = idBooking;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getIdDokter() {
        return idDokter;
    }

    public void setIdDokter(int idDokter) {
        this.idDokter = idDokter;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }
}
