package id.trafood.trafood.Models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by kulinerin 1 on 07/12/2017.
 */

public class GetOrder {
    @SerializedName("status")
    String status;

    @SerializedName("result")
    List<Order> listDataOrder;

    @SerializedName("message")
    String message;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Order> getListDataOrder() {
        return listDataOrder;
    }

    public void setListDataOrder(List<Order> listDataOrder) {
        this.listDataOrder = listDataOrder;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
