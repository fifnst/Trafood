package id.trafood.trafood.Models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by kulinerin 1 on 28/11/2017.
 */

public class Like {
    @SerializedName("menuid")
    private String menuid;
    @SerializedName("namamenu")
    private String namamenu;
    @SerializedName("harga")
    private String harga;
    @SerializedName("rmid")
    private String rmid;
    @SerializedName("foto")
    private String foto;
    @SerializedName("likes")
    private String likes;
    @SerializedName("namarm")
    private String namarm;
    @SerializedName ("kota")
    private String kota;
    @SerializedName("kecamatan")
    private String kecamatan;
    @SerializedName("userid")
    private String userid;

    @SerializedName("ipaddress")
    private String ipaddress;

    public Like(String menuid, String namamenu, String harga, String rmid, String foto, String likes, String namarm, String kota, String kecamatan, String userid, String ipaddress) {
        this.menuid = menuid;
        this.namamenu = namamenu;
        this.harga = harga;
        this.rmid = rmid;
        this.foto = foto;
        this.likes = likes;
        this.namarm = namarm;
        this.kota = kota;
        this.kecamatan = kecamatan;
        this.userid = userid;
        this.ipaddress = ipaddress;
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

    public String getRmid() {
        return rmid;
    }

    public void setRmid(String rmid) {
        this.rmid = rmid;
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

    public String getNamarm() {
        return namarm;
    }

    public void setNamarm(String namarm) {
        this.namarm = namarm;
    }

    public String getKota() {
        return kota;
    }

    public void setKota(String kota) {
        this.kota = kota;
    }

    public String getKecamatan() {
        return kecamatan;
    }

    public void setKecamatan(String kecamatan) {
        this.kecamatan = kecamatan;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getIpaddress() {
        return ipaddress;
    }

    public void setIpaddress(String ipaddress) {
        this.ipaddress = ipaddress;
    }
}
