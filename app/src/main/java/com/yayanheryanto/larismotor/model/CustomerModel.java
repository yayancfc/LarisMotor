package com.yayanheryanto.larismotor.model;

public class CustomerModel {

    private String nama, telepon;

    public CustomerModel(String nama, String telepon) {
        this.nama = nama;
        this.telepon = telepon;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getTelepon() {
        return telepon;
    }

    public void setTelepon(String telepon) {
        this.telepon = telepon;
    }
}
