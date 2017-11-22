package id.trafood.trafood.Models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by kulinerin 1 on 22/11/2017.
 */

public class PostPutDelMenu {
    @SerializedName("status")
    String status;

    @SerializedName("result")
    Menu mMenu;

    @SerializedName("message")
    String message;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Menu getmMenu() {
        return mMenu;
    }

    public void setmMenu(Menu mMenu) {
        this.mMenu = mMenu;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

