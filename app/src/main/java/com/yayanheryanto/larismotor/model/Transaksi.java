package com.yayanheryanto.larismotor.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Transaksi {

    @SerializedName("tanggal")
    @Expose
    private String tanggal;
    @SerializedName("nosin")
    @Expose
    private String nosin;
    @SerializedName("nopol")
    @Expose
    private String nopol;
    @SerializedName("pembeli")
    @Expose
    private String pembeli;
    @SerializedName("sales")
    @Expose
    private String sales;
    @SerializedName("kondisi")
    @Expose
    private String kondisi;
    @SerializedName("dp")
    @Expose
    private String dp;

    private int nomor;
    private boolean sama;

    public boolean isSama() {
        return sama;
    }

    public void setSama(boolean sama) {
        this.sama = sama;
    }

    public int getNomor() {
        return nomor;
    }

    public void setNomor(int nomor) {
        this.nomor = nomor;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getNosin() {
        return nosin;
    }

    public void setNosin(String nosin) {
        this.nosin = nosin;
    }

    public String getNopol() {
        return nopol;
    }

    public void setNopol(String nopol) {
        this.nopol = nopol;
    }

    public String getPembeli() {
        return pembeli;
    }

    public void setPembeli(String pembeli) {
        this.pembeli = pembeli;
    }

    public String getSales() {
        return sales;
    }

    public void setSales(String sales) {
        this.sales = sales;
    }

    public String getKondisi() {
        return kondisi;
    }

    public void setKondisi(String kondisi) {
        this.kondisi = kondisi;
    }

    public String getDp() {
        return dp;
    }

    public void setDp(String dp) {
        this.dp = dp;
    }

}


