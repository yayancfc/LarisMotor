package com.yayanheryanto.larismotor.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Motor implements Parcelable
{
    @SerializedName("message")
    @Expose
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @SerializedName("no_mesin")
    @Expose
    private String noMesin;
    @SerializedName("id_merk")
    @Expose
    private Integer idMerk;
    @SerializedName("id_tipe")
    @Expose
    private Integer idTipe;
    @SerializedName("id_transaksi")
    @Expose
    private Integer idTransaksi;
    @SerializedName("id_user")
    @Expose
    private Integer idUser;
    @SerializedName("no_polisi")
    @Expose
    private String noPolisi;
    @SerializedName("no_rangka")
    @Expose
    private String noRangka;
    @SerializedName("tahun")
    @Expose
    private Integer tahun;
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("kondisi")
    @Expose
    private Integer kondisi;
    @SerializedName("harga")
    @Expose
    private Integer harga;
    @SerializedName("hjm")
    @Expose
    private Integer hjm;
    @SerializedName("harga_terjual")
    @Expose
    private Integer hargaTerjual;
    @SerializedName("gambar")
    @Expose
    private String gambar;
    @SerializedName("gambar1")
    @Expose
    private String gambar1;
    @SerializedName("gambar2")
    @Expose
    private String gambar2;
    @SerializedName("cicilan")
    @Expose
    private Integer cicilan;
    @SerializedName("tenor")
    @Expose
    private Integer tenor;
    @SerializedName("dp")
    @Expose
    private Integer dp;
    public final static Parcelable.Creator<Motor> CREATOR = new Creator<Motor>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Motor createFromParcel(Parcel in) {
            return new Motor(in);
        }

        public Motor[] newArray(int size) {
            return (new Motor[size]);
        }

    }
            ;

    protected Motor(Parcel in) {
        this.noMesin = ((String) in.readValue((String.class.getClassLoader())));
        this.idMerk = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.idTipe = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.idTransaksi = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.idUser = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.noPolisi = ((String) in.readValue((String.class.getClassLoader())));
        this.noRangka = ((String) in.readValue((String.class.getClassLoader())));
        this.tahun = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.status = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.kondisi = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.harga = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.hjm = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.hargaTerjual = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.gambar = ((String) in.readValue((String.class.getClassLoader())));
        this.gambar1 = ((String) in.readValue((String.class.getClassLoader())));
        this.gambar2 = ((String) in.readValue((String.class.getClassLoader())));
        this.cicilan = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.tenor = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.dp = ((Integer) in.readValue((Integer.class.getClassLoader())));
    }

    public Motor() {
    }

    public String getNoMesin() {
        return noMesin;
    }

    public void setNoMesin(String noMesin) {
        this.noMesin = noMesin;
    }

    public Integer getIdMerk() {
        return idMerk;
    }

    public void setIdMerk(Integer idMerk) {
        this.idMerk = idMerk;
    }

    public Integer getIdTipe() {
        return idTipe;
    }

    public void setIdTipe(Integer idTipe) {
        this.idTipe = idTipe;
    }

    public Integer getIdTransaksi() {
        return idTransaksi;
    }

    public void setIdTransaksi(Integer idTransaksi) {
        this.idTransaksi = idTransaksi;
    }

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
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

    public Integer getTahun() {
        return tahun;
    }

    public void setTahun(Integer tahun) {
        this.tahun = tahun;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getKondisi() {
        return kondisi;
    }

    public void setKondisi(Integer kondisi) {
        this.kondisi = kondisi;
    }

    public Integer getHarga() {
        return harga;
    }

    public void setHarga(Integer harga) {
        this.harga = harga;
    }

    public Integer getHjm() {
        return hjm;
    }

    public void setHjm(Integer hjm) {
        this.hjm = hjm;
    }

    public Integer getHargaTerjual() {
        return hargaTerjual;
    }

    public void setHargaTerjual(Integer hargaTerjual) {
        this.hargaTerjual = hargaTerjual;
    }

    public String getGambar() {
        return gambar;
    }

    public void setGambar(String gambar) {
        this.gambar = gambar;
    }

    public String getGambar1() {
        return gambar1;
    }

    public void setGambar1(String gambar1) {
        this.gambar1 = gambar1;
    }

    public String getGambar2() {
        return gambar2;
    }

    public void setGambar2(String gambar2) {
        this.gambar2 = gambar2;
    }

    public Integer getCicilan() {
        return cicilan;
    }

    public void setCicilan(Integer cicilan) {
        this.cicilan = cicilan;
    }

    public Integer getTenor() {
        return tenor;
    }

    public void setTenor(Integer tenor) {
        this.tenor = tenor;
    }

    public Integer getDp() {
        return dp;
    }

    public void setDp(Integer dp) {
        this.dp = dp;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(noMesin);
        dest.writeValue(idMerk);
        dest.writeValue(idTipe);
        dest.writeValue(idTransaksi);
        dest.writeValue(idUser);
        dest.writeValue(noPolisi);
        dest.writeValue(noRangka);
        dest.writeValue(tahun);
        dest.writeValue(status);
        dest.writeValue(kondisi);
        dest.writeValue(harga);
        dest.writeValue(hjm);
        dest.writeValue(hargaTerjual);
        dest.writeValue(gambar);
        dest.writeValue(gambar1);
        dest.writeValue(gambar2);
        dest.writeValue(cicilan);
        dest.writeValue(tenor);
        dest.writeValue(dp);
    }

    public int describeContents() {
        return 0;
    }

}
