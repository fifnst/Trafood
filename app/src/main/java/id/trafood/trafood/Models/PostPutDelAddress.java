package id.trafood.trafood.Models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by TRAFOOD on 12/14/2017.
 */

public class PostPutDelAddress {
    @SerializedName("status")
    String status;

    @SerializedName("result")
    Address mAddress;

    @SerializedName("message")
    String message;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Address getmAddress() {
        return mAddress;
    }

    public void setmAddress(Address mAddress) {
        this.mAddress = mAddress;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
