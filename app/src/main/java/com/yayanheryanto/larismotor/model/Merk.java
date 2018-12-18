package com.yayanheryanto.larismotor.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Merk implements Parcelable
{

    @SerializedName("id_merk")
    @Expose
    private Integer idMerk;
    @SerializedName("nama_merk")
    @Expose
    private String namaMerk;

    @SerializedName("message")
    @Expose
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public final static Parcelable.Creator<Merk> CREATOR = new Creator<Merk>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Merk createFromParcel(Parcel in) {
            return new Merk(in);
        }

        public Merk[] newArray(int size) {
            return (new Merk[size]);
        }

    }
            ;

    protected Merk(Parcel in) {
        this.idMerk = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.namaMerk = ((String) in.readValue((String.class.getClassLoader())));
    }

    public Merk() {
    }

    public Integer getIdMerk() {
        return idMerk;
    }

    public void setIdMerk(Integer idMerk) {
        this.idMerk = idMerk;
    }

    public String getNamaMerk() {
        return namaMerk;
    }

    public void setNamaMerk(String namaMerk) {
        this.namaMerk = namaMerk;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(idMerk);
        dest.writeValue(namaMerk);
    }

    public int describeContents() {
        return 0;
    }

}