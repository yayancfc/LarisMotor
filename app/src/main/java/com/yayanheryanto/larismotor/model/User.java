package com.yayanheryanto.larismotor.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User implements Parcelable
{

    @SerializedName("id_user")
    @Expose
    private Integer idUser;
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("AccesToken")
    @Expose
    private String accesToken;
    @SerializedName("no_ktp_sales")
    @Expose
    private String noKtpSales;
    @SerializedName("nama")
    @Expose
    private String nama;
    @SerializedName("no_wa")
    @Expose
    private String noWa;
    @SerializedName("alamat")
    @Expose
    private String alamat;
    @SerializedName("message")
    @Expose
    private String message;
    public final static Parcelable.Creator<User> CREATOR = new Creator<User>() {


        @SuppressWarnings({
                "unchecked"
        })
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        public User[] newArray(int size) {
            return (new User[size]);
        }

    }
            ;

    protected User(Parcel in) {
        this.idUser = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.username = ((String) in.readValue((String.class.getClassLoader())));
        this.password = ((String) in.readValue((String.class.getClassLoader())));
        this.status = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.accesToken = ((String) in.readValue((String.class.getClassLoader())));
        this.noKtpSales = ((String) in.readValue((String.class.getClassLoader())));
        this.nama = ((String) in.readValue((String.class.getClassLoader())));
        this.noWa = ((String) in.readValue((String.class.getClassLoader())));
        this.alamat = ((String) in.readValue((String.class.getClassLoader())));
        this.message = ((String) in.readValue((String.class.getClassLoader())));
    }

    public User() {
    }

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getAccesToken() {
        return accesToken;
    }

    public void setAccesToken(String accesToken) {
        this.accesToken = accesToken;
    }

    public String getNoKtpSales() {
        return noKtpSales;
    }

    public void setNoKtpSales(String noKtpSales) {
        this.noKtpSales = noKtpSales;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNoWa() {
        return noWa;
    }

    public void setNoWa(String noWa) {
        this.noWa = noWa;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(idUser);
        dest.writeValue(username);
        dest.writeValue(password);
        dest.writeValue(status);
        dest.writeValue(accesToken);
        dest.writeValue(noKtpSales);
        dest.writeValue(nama);
        dest.writeValue(noWa);
        dest.writeValue(alamat);
        dest.writeValue(message);
    }

    public int describeContents() {
        return 0;
    }

}
