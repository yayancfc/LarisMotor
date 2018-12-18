package com.yayanheryanto.larismotor.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Detail implements Parcelable
{

    @SerializedName("no_mesin")
    @Expose
    private String noMesin;
    @SerializedName("harga")
    @Expose
    private Integer harga;
    @SerializedName("dp")
    @Expose
    private Integer dp;
    @SerializedName("cicilan")
    @Expose
    private Integer cicilan;
    @SerializedName("tenor")
    @Expose
    private Integer tenor;
    @SerializedName("no_polisi")
    @Expose
    private String noPolisi;
    @SerializedName("no_rangka")
    @Expose
    private String noRangka;
    @SerializedName("hjm")
    @Expose
    private Integer hjm;
    @SerializedName("kondisi")
    @Expose
    private Integer kondisi;
    @SerializedName("tahun")
    @Expose
    private Integer tahun;
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("gambar")
    @Expose
    private String gambar;
    @SerializedName("gambar1")
    @Expose
    private String gambar1;
    @SerializedName("gambar2")
    @Expose
    private String gambar2;
    @SerializedName("nama_merk")
    @Expose
    private String namaMerk;
    @SerializedName("nama_tipe")
    @Expose
    private String namaTipe;
    @SerializedName("no_wa")
    @Expose
    private String noWa;
    public final static Parcelable.Creator<Detail> CREATOR = new Creator<Detail>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Detail createFromParcel(Parcel in) {
            return new Detail(in);
        }

        public Detail[] newArray(int size) {
            return (new Detail[size]);
        }

    }
            ;

    protected Detail(Parcel in) {
        this.noMesin = ((String) in.readValue((String.class.getClassLoader())));
        this.harga = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.dp = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.cicilan = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.tenor = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.noPolisi = ((String) in.readValue((String.class.getClassLoader())));
        this.noRangka = ((String) in.readValue((String.class.getClassLoader())));
        this.hjm = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.kondisi = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.tahun = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.status = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.gambar = ((String) in.readValue((String.class.getClassLoader())));
        this.gambar1 = ((String) in.readValue((String.class.getClassLoader())));
        this.gambar2 = ((String) in.readValue((String.class.getClassLoader())));
        this.namaMerk = ((String) in.readValue((String.class.getClassLoader())));
        this.namaTipe = ((String) in.readValue((String.class.getClassLoader())));
        this.noWa = ((String) in.readValue((String.class.getClassLoader())));
    }

    public Detail() {
    }

    public String getNoMesin() {
        return noMesin;
    }

    public void setNoMesin(String noMesin) {
        this.noMesin = noMesin;
    }

    public Integer getHarga() {
        return harga;
    }

    public void setHarga(Integer harga) {
        this.harga = harga;
    }

    public Integer getDp() {
        return dp;
    }

    public void setDp(Integer dp) {
        this.dp = dp;
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

    public Integer getHjm() {
        return hjm;
    }

    public void setHjm(Integer hjm) {
        this.hjm = hjm;
    }

    public Integer getKondisi() {
        return kondisi;
    }

    public void setKondisi(Integer kondisi) {
        this.kondisi = kondisi;
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

    public String getNamaMerk() {
        return namaMerk;
    }

    public void setNamaMerk(String namaMerk) {
        this.namaMerk = namaMerk;
    }

    public String getNamaTipe() {
        return namaTipe;
    }

    public void setNamaTipe(String namaTipe) {
        this.namaTipe = namaTipe;
    }

    public String getNoWa() {
        return noWa;
    }

    public void setNoWa(String noWa) {
        this.noWa = noWa;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(noMesin);
        dest.writeValue(harga);
        dest.writeValue(dp);
        dest.writeValue(cicilan);
        dest.writeValue(tenor);
        dest.writeValue(noPolisi);
        dest.writeValue(noRangka);
        dest.writeValue(hjm);
        dest.writeValue(kondisi);
        dest.writeValue(tahun);
        dest.writeValue(status);
        dest.writeValue(gambar);
        dest.writeValue(gambar1);
        dest.writeValue(gambar2);
        dest.writeValue(namaMerk);
        dest.writeValue(namaTipe);
        dest.writeValue(noWa);
    }

    public int describeContents() {
        return 0;
    }

}
