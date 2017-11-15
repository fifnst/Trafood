package id.trafood.trafood.Home;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import id.trafood.trafood.Models.ArticleUser;

/**
 * Created by kulinerin 1 on 30/10/2017.
 */

public class GetModelMenu {
    @SerializedName("status")
    String status;

    @SerializedName("result")
    List<ModelMenu> LisModelmenu;

    @SerializedName("message")
    String message;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<ModelMenu> getLisModelmenu() {
        return LisModelmenu;
    }

    public void setLisModelmenu(List<ModelMenu> lisModelmenu) {
        LisModelmenu = lisModelmenu;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
