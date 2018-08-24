package com.yayanheryanto.larismotor.model;

public class MotorModel {

    private int image;
    private String nopol, hjm;

    public MotorModel(int image, String nopol, String hjm) {
        this.image = image;
        this.nopol = nopol;
        this.hjm = hjm;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getNopol() {
        return nopol;
    }

    public void setNopol(String nopol) {
        this.nopol = nopol;
    }

    public String getHjm() {
        return hjm;
    }

    public void setHjm(String hjm) {
        this.hjm = hjm;
    }
}
