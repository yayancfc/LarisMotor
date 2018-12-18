package com.yayanheryanto.larismotor.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Tipe implements Parcelable {

    @SerializedName("id_tipe")
    @Expose
    private Integer idTipe;
    @SerializedName("id_merk")
    @Expose
    private Integer idMerk;
    @SerializedName("nama_tipe")
    @Expose
    private String namaTipe;


    @SerializedName("message")
    @Expose
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public final static Parcelable.Creator<Tipe> CREATOR = new Creator<Tipe>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Tipe createFromParcel(Parcel in) {
            return new Tipe(in);
        }

        public Tipe[] newArray(int size) {
            return (new Tipe[size]);
        }

    };

    protected Tipe(Parcel in) {
        this.idTipe = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.idMerk = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.namaTipe = ((String) in.readValue((String.class.getClassLoader())));
    }

    public Tipe() {
    }

    public Integer getIdTipe() {
        return idTipe;
    }

    public void setIdTipe(Integer idTipe) {
        this.idTipe = idTipe;
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

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(idTipe);
        dest.writeValue(idMerk);
        dest.writeValue(namaTipe);
    }

    public int describeContents() {
        return 0;
    }

}
