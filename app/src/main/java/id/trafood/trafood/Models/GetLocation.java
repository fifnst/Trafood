package id.trafood.trafood.Models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by kulinerin 1 on 06/11/2017.
 */

public class GetLocation {
    @SerializedName("status")
    String status;

    @SerializedName("result")
    List<Location> lisLocation;

    @SerializedName("message")
    String message;


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Location> getLisLocation() {
        return lisLocation;
    }

    public void setLisLocation(List<Location> lisLocation) {
        this.lisLocation = lisLocation;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
