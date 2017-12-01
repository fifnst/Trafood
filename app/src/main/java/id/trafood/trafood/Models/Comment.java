package id.trafood.trafood.Models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by kulinerin 1 on 24/10/2017.
 */

public class Comment {
    @SerializedName("id")
    private String id;

    @SerializedName("rmid")
    private String rmid;

    @SerializedName("userid")
    private String userid;

    @SerializedName("body")
    private String body;

    @SerializedName("datecretae")
    private String datecretae;

    @SerializedName("nama")
    private String nama;

    @SerializedName("kota")
    private String kota;

    @SerializedName("fotouser")
    private String fotouser;

    @SerializedName("rating1")
    private String rating1;

    @SerializedName("rating2")
    private String rating2;

    @SerializedName("rating3")
    private String rating3;

    @SerializedName("rating4")
    private String rating4;

    public Comment(String id, String rmid, String userid, String body, String datecretae, String nama, String kota, String fotouser, String rating1, String rating2, String rating3, String rating4) {
        this.id = id;
        this.rmid = rmid;
        this.userid = userid;
        this.body = body;
        this.datecretae = datecretae;
        this.nama = nama;
        this.kota = kota;
        this.fotouser = fotouser;
        this.rating1 = rating1;
        this.rating2 = rating2;
        this.rating3 = rating3;
        this.rating4 = rating4;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRmid() {
        return rmid;
    }

    public void setRmid(String rmid) {
        this.rmid = rmid;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getDatecretae() {
        return datecretae;
    }

    public void setDatecretae(String datecretae) {
        this.datecretae = datecretae;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getKota() {
        return kota;
    }

    public void setKota(String kota) {
        this.kota = kota;
    }

    public String getFotouser() {
        return fotouser;
    }

    public void setFotouser(String fotouser) {
        this.fotouser = fotouser;
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
}
