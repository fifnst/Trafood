package id.trafood.trafood.Models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by kulinerin 1 on 26/10/2017.
 */

public class UserView {
    @SerializedName("review")
    private String review;

    @SerializedName("article")
    private String article ;

    @SerializedName("visit")
    private String visit;

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

    @SerializedName("rumahmakan")
    private String rumahmakan;

    @SerializedName("rmid")
    private String rmid;

    @SerializedName("latitude")
    private String latitude;

    @SerializedName("longitude")
    private String longitude;

    @SerializedName("namarm")
    private  String namarm;

    @SerializedName("alamatrm")
    private String alamatrm;

    @SerializedName("kategorirm")
    private String kategorirm;

    @SerializedName("fotorm")
    private  String fotorm;


    public UserView(String review, String article, String visit, String userid, String nama, String email, String fotouser, String kota, String tentang, String rumahmakan, String rmid, String latitude, String longitude, String namarm, String alamatrm, String kategorirm, String fotorm) {
        this.review = review;
        this.article = article;
        this.visit = visit;
        this.userid = userid;
        this.nama = nama;
        this.email = email;
        this.fotouser = fotouser;
        this.kota = kota;
        this.tentang = tentang;
        this.rumahmakan = rumahmakan;
        this.rmid = rmid;
        this.latitude = latitude;
        this.longitude = longitude;
        this.namarm = namarm;
        this.alamatrm = alamatrm;
        this.kategorirm = kategorirm;
        this.fotorm = fotorm;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public String getArticle() {
        return article;
    }

    public void setArticle(String article) {
        this.article = article;
    }

    public String getVisit() {
        return visit;
    }

    public void setVisit(String visit) {
        this.visit = visit;
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

    public String getRumahmakan() {
        return rumahmakan;
    }

    public void setRumahmakan(String rumahmakan) {
        this.rumahmakan = rumahmakan;
    }

    public String getRmid() {
        return rmid;
    }

    public void setRmid(String rmid) {
        this.rmid = rmid;
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

    public String getNamarm() {
        return namarm;
    }

    public void setNamarm(String namarm) {
        this.namarm = namarm;
    }

    public String getAlamatrm() {
        return alamatrm;
    }

    public void setAlamatrm(String alamatrm) {
        this.alamatrm = alamatrm;
    }

    public String getKategorirm() {
        return kategorirm;
    }

    public void setKategorirm(String kategorirm) {
        this.kategorirm = kategorirm;
    }

    public String getFotorm() {
        return fotorm;
    }

    public void setFotorm(String fotorm) {
        this.fotorm = fotorm;
    }
}

