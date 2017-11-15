package id.trafood.trafood.Models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by kulinerin 1 on 23/10/2017.
 */

public class GetGalery {
    @SerializedName("status")
    String status;

    @SerializedName("result")
    List<Galery> ListDataGalery;

    @SerializedName("message")
    String message;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Galery> getListDataGalery() {
        return ListDataGalery;
    }

    public void setListDataGalery(List<Galery> listDataGalery) {
        ListDataGalery = listDataGalery;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
