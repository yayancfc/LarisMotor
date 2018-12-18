
package com.yayanheryanto.larismotor.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MerkTipe implements Parcelable {

    @SerializedName("id_tipe")
    @Expose
    private Integer idTipe;
    @SerializedName("id_merk")
    @Expose
    private Integer idMerk;
    @SerializedName("nama_tipe")
    @Expose
    private String namaTipe;
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

    public final static Parcelable.Creator<MerkTipe> CREATOR = new Creator<MerkTipe>() {


        @SuppressWarnings({
                "unchecked"
        })
        public MerkTipe createFromParcel(Parcel in) {
            return new MerkTipe(in);
        }

        public MerkTipe[] newArray(int size) {
            return (new MerkTipe[size]);
        }

    };

    protected MerkTipe(Parcel in) {
        this.idTipe = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.idMerk = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.namaTipe = ((String) in.readValue((String.class.getClassLoader())));
        this.namaMerk = ((String) in.readValue((String.class.getClassLoader())));
    }

    public MerkTipe() {
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

    public String getNamaMerk() {
        return namaMerk;
    }

    public void setNamaMerk(String namaMerk) {
        this.namaMerk = namaMerk;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(idTipe);
        dest.writeValue(idMerk);
        dest.writeValue(namaTipe);
        dest.writeValue(namaMerk);
    }

    public int describeContents() {
        return 0;
    }

}
