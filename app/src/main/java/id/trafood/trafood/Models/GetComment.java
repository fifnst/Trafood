package id.trafood.trafood.Models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by kulinerin 1 on 24/10/2017.
 */

public class GetComment {
    @SerializedName("status")
    String status;

    @SerializedName("result")
    List<Comment> ListDataComment;

    @SerializedName("message")
    String message;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Comment> getListDataComment() {
        return ListDataComment;
    }

    public void setListDataComment(List<Comment> listDataComment) {
        ListDataComment = listDataComment;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
