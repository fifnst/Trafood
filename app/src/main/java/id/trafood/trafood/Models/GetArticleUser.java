package id.trafood.trafood.Models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by kulinerin 1 on 28/10/2017.
 */

public class GetArticleUser {
    @SerializedName("status")
    String status;

    @SerializedName("result")
    List<ArticleUser> ArtcileUser;

    @SerializedName("message")
    String message;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<ArticleUser> getArtcileUser() {
        return ArtcileUser;
    }

    public void setArtcileUser(List<ArticleUser> artcileUser) {
        ArtcileUser = artcileUser;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
