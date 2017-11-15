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

    @SerializedName("fotouser")
    private String fotouser;

    public Comment(String id, String rmid, String userid, String body, String datecretae, String nama, String fotouser) {
        this.id = id;
        this.rmid = rmid;
        this.userid = userid;
        this.body = body;
        this.datecretae = datecretae;
        this.nama = nama;
        this.fotouser = fotouser;
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

    public String getFotouser() {
        return fotouser;
    }

    public void setFotouser(String fotouser) {
        this.fotouser = fotouser;
    }
}
