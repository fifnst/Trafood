package id.trafood.trafood.Models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by TRAFOOD on 12/14/2017.
 */

public class PostPutDelAddress {
    @SerializedName("status")
    String status;

    @SerializedName("result")
    AddressP mAddress;

    @SerializedName("message")
    String message;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public AddressP getmAddress() {
        return mAddress;
    }

    public void setmAddress(AddressP mAddress) {
        this.mAddress = mAddress;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
