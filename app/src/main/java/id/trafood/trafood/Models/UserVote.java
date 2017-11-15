package id.trafood.trafood.Models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by kulinerin 1 on 28/10/2017.
 */

public class UserVote {
    @SerializedName("rmid")
    private String rmid;

    @SerializedName("namarm")
    private  String namarm;

    @SerializedName("kecamatan")
    private String kecamatan;

    @SerializedName ("kota")
    private String kota;

    @SerializedName("fotosampul")
    private String fotosampul;

    @SerializedName("userid")
    private String userid;

    @SerializedName("rating1")
    private String rating1;

    @SerializedName("rating2")
    private String rating2;

    @SerializedName("rating3")
    private String rating3;

    @SerializedName("rating4")
    private String rating4;

    @SerializedName("datecreate")
    private String datecreate;

    @SerializedName("body")
    private String body;

    public UserVote(String rmid, String namarm, String kecamatan, String kota, String fotosampul, String userid, String rating1, String rating2, String rating3, String rating4, String datecreate, String body) {
        this.rmid = rmid;
        this.namarm = namarm;
        this.kecamatan = kecamatan;
        this.kota = kota;
        this.fotosampul = fotosampul;
        this.userid = userid;
        this.rating1 = rating1;
        this.rating2 = rating2;
        this.rating3 = rating3;
        this.rating4 = rating4;
        this.datecreate = datecreate;
        this.body = body;
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

    public String getKecamatan() {
        return kecamatan;
    }

    public void setKecamatan(String kecamatan) {
        this.kecamatan = kecamatan;
    }

    public String getKota() {
        return kota;
    }

    public void setKota(String kota) {
        this.kota = kota;
    }

    public String getFotosampul() {
        return fotosampul;
    }

    public void setFotosampul(String fotosampul) {
        this.fotosampul = fotosampul;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getRating1() {
        return rating1;
    }

    public void setRating1(String rating1) {
        this.rating1 = rating1;
    }

    public String getRating2() {
        return rating2;
    }

    public void setRating2(String rating2) {
        this.rating2 = rating2;
    }

    public String getRating3() {
        return rating3;
    }

    public void setRating3(String rating3) {
        this.rating3 = rating3;
    }

    public String getRating4() {
        return rating4;
    }

    public void setRating4(String rating4) {
        this.rating4 = rating4;
    }

    public String getDatecreate() {
        return datecreate;
    }

    public void setDatecreate(String datecreate) {
        this.datecreate = datecreate;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
