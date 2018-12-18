package com.yayanheryanto.larismotor.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PendingJual implements Parcelable
{



    @SerializedName("message")
    @Expose
    private String message;

    @SerializedName("id_pending")
    @Expose
    private Integer idPending;
    @SerializedName("nama")
    @Expose
    private String nama;
    @SerializedName("alamat")
    @Expose
    private String alamat;
    @SerializedName("no_telp")
    @Expose
    private String noTelp;
    @SerializedName("no_mesin")
    @Expose
    private String noMesin;
    @SerializedName("no_polisi")
    @Expose
    private String noPolisi;
    @SerializedName("merk")
    @Expose
    private String merk;
    @SerializedName("tipe")
    @Expose
    private String tipe;
    @SerializedName("tahun")
    @Expose
    private Integer tahun;
    @SerializedName("harga")
    @Expose
    private Integer harga;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public final static Parcelable.Creator<PendingJual> CREATOR = new Creator<PendingJual>() {


        @SuppressWarnings({
                "unchecked"
        })
        public PendingJual createFromParcel(Parcel in) {
            return new PendingJual(in);
        }

        public PendingJual[] newArray(int size) {
            return (new PendingJual[size]);
        }

    }
            ;

    protected PendingJual(Parcel in) {
        this.idPending = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.nama = ((String) in.readValue((String.class.getClassLoader())));
        this.alamat = ((String) in.readValue((String.class.getClassLoader())));
        this.noTelp = ((String) in.readValue((String.class.getClassLoader())));
        this.noMesin = ((String) in.readValue((String.class.getClassLoader())));
        this.noPolisi = ((String) in.readValue((String.class.getClassLoader())));
        this.merk = ((String) in.readValue((String.class.getClassLoader())));
        this.tipe = ((String) in.readValue((String.class.getClassLoader())));
        this.tahun = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.harga = ((Integer) in.readValue((Integer.class.getClassLoader())));
    }

    public PendingJual() {
    }

    public Integer getIdPending() {
        return idPending;
    }

    public void setIdPending(Integer idPending) {
        this.idPending = idPending;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getNoTelp() {
        return noTelp;
    }

    public void setNoTelp(String noTelp) {
        this.noTelp = noTelp;
    }

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

    public String getMerk() {
        return merk;
    }

    public void setMerk(String merk) {
        this.merk = merk;
    }

    public String getTipe() {
        return tipe;
    }

    public void setTipe(String tipe) {
        this.tipe = tipe;
    }

    public Integer getTahun() {
        return tahun;
    }

    public void setTahun(Integer tahun) {
        this.tahun = tahun;
    }

    public Integer getHarga() {
        return harga;
    }

    public void setHarga(Integer harga) {
        this.harga = harga;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(idPending);
        dest.writeValue(nama);
        dest.writeValue(alamat);
        dest.writeValue(noTelp);
        dest.writeValue(noMesin);
        dest.writeValue(noPolisi);
        dest.writeValue(merk);
        dest.writeValue(tipe);
        dest.writeValue(tahun);
        dest.writeValue(harga);
    }

    public int describeContents() {
        return 0;
    }

}
