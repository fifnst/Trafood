package id.trafood.trafood.Models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by kulinerin 1 on 24/10/2017.
 */

public class MenuDetail {
    @SerializedName("menuid")
    private String menuid;
    @SerializedName("namamenu")
    private String namamenu;
    @SerializedName("harga")
    private String harga;
    @SerializedName("foto")
    private String foto;
    @SerializedName("likes")
    private String likes;
    @SerializedName("tag")
    private String tag;
    @SerializedName("dilihat")
    private String dilihat;
    @SerializedName("deskripsi")
    private String deskripsi;
    @SerializedName("rmid")
    private String rmid;

    @SerializedName("namarm")
    private  String namarm;

    @SerializedName("alamat")
    private String alamat;

    @SerializedName("fotosampul")
    private String fotosampul;

    @SerializedName("kategorirm")
    private String kategorirm;

    @SerializedName("latitude")
    private String latitude;

    @SerializedName("longitude")
    private String longitude;

    @SerializedName("deskripsirm")
    private String deskripsirm;


    public MenuDetail(String menuid, String namamenu, String harga, String foto, String likes, String tag, String dilihat, String deskripsi, String rmid, String namarm, String alamat, String fotosampul, String kategorirm, String latitude, String longitude) {
        this.menuid = menuid;
        this.namamenu = namamenu;
        this.harga = harga;
        this.foto = foto;
        this.likes = likes;
        this.tag = tag;
        this.dilihat = dilihat;
        this.deskripsi = deskripsi;
        this.rmid = rmid;
        this.namarm = namarm;
        this.alamat = alamat;
        this.fotosampul = fotosampul;
        this.kategorirm = kategorirm;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public String getMenuid() {
        return menuid;
    }

    public void setMenuid(String menuid) {
        this.menuid = menuid;
    }

    public String getNamamenu() {
        return namamenu;
    }

    public void setNamamenu(String namamenu) {
        this.namamenu = namamenu;
    }

    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getLikes() {
        return likes;
    }

    public void setLikes(String likes) {
        this.likes = likes;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getDilihat() {
        return dilihat;
    }

    public void setDilihat(String dilihat) {
        this.dilihat = dilihat;
    }

    public String getRmid() {
        return rmid;
    }

    public void setRmid(String rmid) {
        this.rmid = rmid;
    }

    public String getNamarm() {
        return namarm;
    }

    public void setNamarm(String namarm) {
        this.namarm = namarm;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getFotosampul() {
        return fotosampul;
    }

    public void setFotosampul(String fotosampul) {
        this.fotosampul = fotosampul;
    }

    public String getKategorirm() {
        return kategorirm;
    }

    public void setKategorirm(String kategorirm) {
        this.kategorirm = kategorirm;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }
}
