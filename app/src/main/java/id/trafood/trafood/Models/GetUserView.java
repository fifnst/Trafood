package id.trafood.trafood.Models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by kulinerin 1 on 26/10/2017.
 */

public class GetUserView {
    @SerializedName("status")
    String status;

    @SerializedName("result")
    List<UserView> ListUserView;

    @SerializedName("message")
    String message;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<UserView> getListUserView() {
        return ListUserView;
    }

    public void setListUserView(List<UserView> listUserView) {
        ListUserView = listUserView;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
