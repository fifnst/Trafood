package id.trafood.trafood.Models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by kulinerin 1 on 30/10/2017.
 */

public class GetArticle {
    @SerializedName("status")
    String status;

    @SerializedName("result")
    List<Article> getListArticle;

    @SerializedName("message")
    String message;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Article> getGetListArticle() {
        return getListArticle;
    }

    public void setGetListArticle(List<Article> getListArticle) {
        this.getListArticle = getListArticle;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
