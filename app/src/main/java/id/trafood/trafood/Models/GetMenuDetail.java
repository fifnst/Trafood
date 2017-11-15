package id.trafood.trafood.Models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by kulinerin 1 on 24/10/2017.
 */

public class GetMenuDetail {
    @SerializedName("status")
    String status;

    @SerializedName("result")
    List<MenuDetail> ListDataMenuDetail;

    @SerializedName("message")
    String message;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<MenuDetail> getListDataMenuDetail() {
        return ListDataMenuDetail;
    }

    public void setListDataMenuDetail(List<MenuDetail> listDataMenuDetail) {
        ListDataMenuDetail = listDataMenuDetail;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
