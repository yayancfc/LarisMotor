package com.yayanheryanto.larismotor.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserModel implements Parcelable {

    @SerializedName("id_user")
    @Expose
    private String idUser;
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
    @SerializedName("message")
    @Expose
    private String message;

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.idUser);
        dest.writeString(this.username);
        dest.writeString(this.password);
        dest.writeValue(this.status);
        dest.writeString(this.accesToken);
        dest.writeString(this.noKtpSales);
        dest.writeString(this.nama);
        dest.writeString(this.message);
    }

    public UserModel() {
    }

    protected UserModel(Parcel in) {
        this.idUser = in.readString();
        this.username = in.readString();
        this.password = in.readString();
        this.status = (Integer) in.readValue(Integer.class.getClassLoader());
        this.accesToken = in.readString();
        this.noKtpSales = in.readString();
        this.nama = in.readString();
        this.message = in.readString();
    }

    public static final Parcelable.Creator<UserModel> CREATOR = new Parcelable.Creator<UserModel>() {
        @Override
        public UserModel createFromParcel(Parcel source) {
            return new UserModel(source);
        }

        @Override
        public UserModel[] newArray(int size) {
            return new UserModel[size];
        }
    };
}