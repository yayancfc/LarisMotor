package com.yayanheryanto.larismotor.model;


import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Pending implements Parcelable
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
    @SerializedName("tahun")
    @Expose
    private Integer tahun;
    @SerializedName("harga")
    @Expose
    private Integer harga;
    @SerializedName("nama_merk")
    @Expose
    private String namaMerk;
    @SerializedName("id_merk")
    @Expose
    private Integer idMerk;
    @SerializedName("nama_tipe")
    @Expose
    private String namaTipe;
    @SerializedName("id_tipe")
    @Expose
    private Integer idTipe;
    public final static Parcelable.Creator<Pending> CREATOR = new Creator<Pending>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Pending createFromParcel(Parcel in) {
            return new Pending(in);
        }

        public Pending[] newArray(int size) {
            return (new Pending[size]);
        }

    }
            ;

    protected Pending(Parcel in) {
        this.idPending = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.nama = ((String) in.readValue((String.class.getClassLoader())));
        this.alamat = ((String) in.readValue((String.class.getClassLoader())));
        this.noTelp = ((String) in.readValue((String.class.getClassLoader())));
        this.tahun = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.harga = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.namaMerk = ((String) in.readValue((String.class.getClassLoader())));
        this.idMerk = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.namaTipe = ((String) in.readValue((String.class.getClassLoader())));
        this.idTipe = ((Integer) in.readValue((Integer.class.getClassLoader())));
    }

    public Pending() {
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

    public String getNamaMerk() {
        return namaMerk;
    }

    public void setNamaMerk(String namaMerk) {
        this.namaMerk = namaMerk;
    }

    public Integer getIdMerk() {
        return idMerk;
    }

    public void setIdMerk(Integer idMerk) {
        this.idMerk = idMerk;
    }

    public String getNamaTipe() {
        return namaTipe;
    }

    public void setNamaTipe(String namaTipe) {
        this.namaTipe = namaTipe;
    }

    public Integer getIdTipe() {
        return idTipe;
    }

    public void setIdTipe(Integer idTipe) {
        this.idTipe = idTipe;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(idPending);
        dest.writeValue(nama);
        dest.writeValue(alamat);
        dest.writeValue(noTelp);
        dest.writeValue(tahun);
        dest.writeValue(harga);
        dest.writeValue(namaMerk);
        dest.writeValue(idMerk);
        dest.writeValue(namaTipe);
        dest.writeValue(idTipe);
    }

    public int describeContents() {
        return 0;
    }

}
