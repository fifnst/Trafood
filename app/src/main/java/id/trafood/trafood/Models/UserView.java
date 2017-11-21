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


    public UserView(String review, String article, String visit, String userid, String nama, String email, String fotouser, String kota, String tentang, String rumahmakan) {
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
}
