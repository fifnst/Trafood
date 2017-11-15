package id.trafood.trafood.Models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by kulinerin 1 on 28/10/2017.
 */

public class GetUserVote {

    @SerializedName("status")
    String status;

    @SerializedName("result")
    List<UserVote> ListUserVote;

    @SerializedName("message")
    String message;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<UserVote> getListUserVote() {
        return ListUserVote;
    }

    public void setListUserVote(List<UserVote> listUserVote) {
        ListUserVote = listUserVote;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
