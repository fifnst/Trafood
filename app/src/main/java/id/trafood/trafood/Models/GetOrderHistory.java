package id.trafood.trafood.Models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by kulinerin 1 on 22/12/2017.
 */

public class GetOrderHistory {
    @SerializedName("status")
    String status;

    @SerializedName("result")
    List<OrderHistory> listDataOrderHistory;

    @SerializedName("message")
    String message;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<OrderHistory> getListDataOrderHistory() {
        return listDataOrderHistory;
    }

    public void setListDataOrderHistory(List<OrderHistory> listDataOrderHistory) {
        this.listDataOrderHistory = listDataOrderHistory;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
