package id.trafood.trafood.Models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by TRAFOOD on 12/14/2017.
 */

public class GetAddress {
    @SerializedName("status")
    String status;

    @SerializedName("result")
    List<Address> getListAddress;

    @SerializedName("message")
    String message;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Address> getGetListAddress() {
        return getListAddress;
    }

    public void setGetListAddress(List<Address> getListAddress) {
        this.getListAddress = getListAddress;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
