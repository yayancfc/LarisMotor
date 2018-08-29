package com.yayanheryanto.larismotor.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MerkTipe {
    @SerializedName("id_merk")
    @Expose
    private String idMerk;
    @SerializedName("nama_merk")
    @Expose
    private String namaMerk;
    @SerializedName("id_tipe")
    @Expose
    private String idTipe;
    @SerializedName("nama_tipe")
    @Expose
    private String namaTipe;

    public String getIdMerk() {
        return idMerk;
    }

    public void setIdMerk(String idMerk) {
        this.idMerk = idMerk;
    }

    public String getNamaMerk() {
        return namaMerk;
    }

    public void setNamaMerk(String namaMerk) {
        this.namaMerk = namaMerk;
    }

    public String getIdTipe() {
        return idTipe;
    }

    public void setIdTipe(String idTipe) {
        this.idTipe = idTipe;
    }

    public String getNamaTipe() {
        return namaTipe;
    }

    public void setNamaTipe(String namaTipe) {
        this.namaTipe = namaTipe;
    }
}
