package id.trafood.trafood.Models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by kulinerin 1 on 27/11/2017.
 */

public class User {
    @SerializedName("userid")
    private String userid;

    @SerializedName("nama")
    private String nama;

    @SerializedName("email")
    private String email;

    @SerializedName("fotouser")
    private String fotouser;

    @SerializedName("kota")
    private String kota;

    @SerializedName("tentang")
    private String tentang;

    @SerializedName("telp")
    private String telp;

    @SerializedName("tgllahir")
    private String tgllahir;

    @SerializedName("jk")
    private String jk;

    public User(String userid, String nama, String email, String fotouser, String kota, String tentang, String telp, String tgllahir, String jk) {
        this.userid = userid;
        this.nama = nama;
        this.email = email;
        this.fotouser = fotouser;
        this.kota = kota;
        this.tentang = tentang;
        this.telp = telp;
        this.tgllahir = tgllahir;
        this.jk = jk;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFotouser() {
        return fotouser;
    }

    public void setFotouser(String fotouser) {
        this.fotouser = fotouser;
    }

    public String getKota() {
        return kota;
    }

    public void setKota(String kota) {
        this.kota = kota;
    }

    public String getTentang() {
        return tentang;
    }

    public void setTentang(String tentang) {
        this.tentang = tentang;
    }

    public String getTelp() {
        return telp;
    }

    public void setTelp(String telp) {
        this.telp = telp;
    }

    public String getTgllahir() {
        return tgllahir;
    }

    public void setTgllahir(String tgllahir) {
        this.tgllahir = tgllahir;
    }

    public String getJk() {
        return jk;
    }

    public void setJk(String jk) {
        this.jk = jk;
    }
}
