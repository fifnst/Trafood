package id.trafood.trafood.Models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by kulinerin 1 on 15/12/2017.
 */

public class GetCourier {
    @SerializedName("status")
    String status;

    @SerializedName("result")
    List<Courier> ListDataCourier;

    @SerializedName("message")
    String message;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Courier> getListDataCourier() {
        return ListDataCourier;
    }

    public void setListDataCourier(List<Courier> listDataCourier) {
        ListDataCourier = listDataCourier;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
