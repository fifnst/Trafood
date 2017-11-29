package id.trafood.trafood.Models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by kulinerin 1 on 28/11/2017.
 */

public class GetLike {

    @SerializedName("status")
    String status;

    @SerializedName("result")
    List<Like> ListDataLike;

    @SerializedName("message")
    String message;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Like> getListDataLike() {
        return ListDataLike;
    }

    public void setListDataLike(List<Like> listDataLike) {
        ListDataLike = listDataLike;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
