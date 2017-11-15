package id.trafood.trafood.Models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by kulinerin 1 on 30/10/2017.
 */

public class Article {
    @SerializedName("postid")
    private String postid;

    @SerializedName("title")
    private String title;

    @SerializedName("body")
    private String body;

    @SerializedName("image")
    private String image;

    @SerializedName("created")
    private String created;

    @SerializedName("Tag")
    private String tag;

    @SerializedName("category")
    private String category;

    @SerializedName("dilihat")
    private String dilihat;

    @SerializedName("nama")
    private String nama;
    @SerializedName("userid")
    private String userid;

    public Article(String postid, String title, String body, String image, String created, String tag, String category, String dilihat, String nama, String userid) {
        this.postid = postid;
        this.title = title;
        this.body = body;
        this.image = image;
        this.created = created;
        this.tag = tag;
        this.category = category;
        this.dilihat = dilihat;
        this.nama = nama;
        this.userid = userid;
    }

    public String getPostid() {
        return postid;
    }

    public void setPostid(String postid) {
        this.postid = postid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDilihat() {
        return dilihat;
    }

    public void setDilihat(String dilihat) {
        this.dilihat = dilihat;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }
}
