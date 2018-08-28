package com.yayanheryanto.larismotor.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Motor {

    @SerializedName("no_mesin")
    @Expose
    private String noMesin;
    @SerializedName("no_polisi")
    @Expose
    private String noPolisi;
    @SerializedName("no_rangka")
    @Expose
    private String noRangka;
    @SerializedName("tahun")
    @Expose
    private String tahun;
    @SerializedName("hjm")
    @Expose
    private Integer hjm;
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("harga_terjual")
    @Expose
    private Object hargaTerjual;
    @SerializedName("gambar")
    @Expose
    private String gambar;
    @SerializedName("id_transaksi")
    @Expose
    private Object idTransaksi;
    @SerializedName("id_merk")
    @Expose
    private Object idMerk;
    @SerializedName("id_tipe")
    @Expose
    private Object idTipe;

    public String getNoMesin() {
        return noMesin;
    }

    public void setNoMesin(String noMesin) {
        this.noMesin = noMesin;
    }

    public String getNoPolisi() {
        return noPolisi;
    }

    public void setNoPolisi(String noPolisi) {
        this.noPolisi = noPolisi;
    }

    public String getNoRangka() {
        return noRangka;
    }

    public void setNoRangka(String noRangka) {
        this.noRangka = noRangka;
    }

    public String getTahun() {
        return tahun;
    }

    public void setTahun(String tahun) {
        this.tahun = tahun;
    }

    public Integer getHjm() {
        return hjm;
    }

    public void setHjm(Integer hjm) {
        this.hjm = hjm;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Object getHargaTerjual() {
        return hargaTerjual;
    }

    public void setHargaTerjual(Object hargaTerjual) {
        this.hargaTerjual = hargaTerjual;
    }

    public String getGambar() {
        return gambar;
    }

    public void setGambar(String gambar) {
        this.gambar = gambar;
    }

    public Object getIdTransaksi() {
        return idTransaksi;
    }

    public void setIdTransaksi(Object idTransaksi) {
        this.idTransaksi = idTransaksi;
    }

    public Object getIdMerk() {
        return idMerk;
    }

    public void setIdMerk(Object idMerk) {
        this.idMerk = idMerk;
    }

    public Object getIdTipe() {
        return idTipe;
    }

    public void setIdTipe(Object idTipe) {
        this.idTipe = idTipe;
    }

}
