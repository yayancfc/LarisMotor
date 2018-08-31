package com.yayanheryanto.larismotor.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
public class Motor implements Parcelable {

    @SerializedName("message")
    @Expose
    private String message;

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

    @SerializedName("kondisi")
    @Expose
    private Integer kondisi;

    @SerializedName("harga_terjual")
    @Expose
    private Object hargaTerjual;
    @SerializedName("gambar")
    @Expose
    private String gambar;

    @SerializedName("gambar1")
    @Expose
    private String gambar1;

    @SerializedName("gambar2")
    @Expose
    private String gambar2;

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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
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

    public Integer getKondisi() {
        return kondisi;
    }

    public void setKondisi(Integer kondisi) {
        this.kondisi = kondisi;
    }



    protected Motor(Parcel in) {
        message = in.readString();
        noMesin = in.readString();
        noPolisi = in.readString();
        noRangka = in.readString();
        tahun = in.readString();
        hjm = in.readByte() == 0x00 ? null : in.readInt();
        status = in.readByte() == 0x00 ? null : in.readInt();
        kondisi = in.readByte() == 0x00 ? null : in.readInt();
        hargaTerjual = (Object) in.readValue(Object.class.getClassLoader());
        gambar = in.readString();
        gambar1 = in.readString();
        gambar2 = in.readString();
        idTransaksi = (Object) in.readValue(Object.class.getClassLoader());
        idMerk = (Object) in.readValue(Object.class.getClassLoader());
        idTipe = (Object) in.readValue(Object.class.getClassLoader());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(message);
        dest.writeString(noMesin);
        dest.writeString(noPolisi);
        dest.writeString(noRangka);
        dest.writeString(tahun);
        if (hjm == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeInt(hjm);
        }
        if (status == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeInt(status);
        }
        if (kondisi == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeInt(kondisi);
        }
        dest.writeValue(hargaTerjual);
        dest.writeString(gambar);
        dest.writeString(gambar1);
        dest.writeString(gambar2);
        dest.writeValue(idTransaksi);
        dest.writeValue(idMerk);
        dest.writeValue(idTipe);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Motor> CREATOR = new Parcelable.Creator<Motor>() {
        @Override
        public Motor createFromParcel(Parcel in) {
            return new Motor(in);
        }

        @Override
        public Motor[] newArray(int size) {
            return new Motor[size];
        }
    };
}