package id.trafood.trafood.Models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by kulinerin 1 on 13/10/2017.
 */

public class GetRumahmakan {
    @SerializedName("status")
    String status;

    @SerializedName("result")
    List<Rumahmakan> listDataRumahmakan;

    @SerializedName("message")
    String message;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Rumahmakan> getListDataRumahmakan() {
        return listDataRumahmakan;
    }

    public void setListDataRumahmakan(List<Rumahmakan> listDataRumahmakan) {
        this.listDataRumahmakan = listDataRumahmakan;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
