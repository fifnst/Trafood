package id.trafood.trafood.Models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by kulinerin 1 on 28/10/2017.
 */

public class ArticleUser {
    @SerializedName("postid")
    private String postid;

    @SerializedName("title")
    private String title;

    @SerializedName("created")
    private String created;

    @SerializedName("userid")
    private String userid;

    @SerializedName("image")
    private String image;

    public ArticleUser(String postid, String title, String created, String userid, String image) {
        this.postid = postid;
        this.title = title;
        this.created = created;
        this.userid = userid;
        this.image = image;
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

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
