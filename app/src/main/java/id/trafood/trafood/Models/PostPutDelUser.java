package id.trafood.trafood.Models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by kulinerin 1 on 27/11/2017.
 */

public class PostPutDelUser {
    @SerializedName("status")
    String status;

    @SerializedName("result")
    User mMenu;

    @SerializedName("message")
    String message;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public User getmMenu() {
        return mMenu;
    }

    public void setmMenu(User mMenu) {
        this.mMenu = mMenu;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
