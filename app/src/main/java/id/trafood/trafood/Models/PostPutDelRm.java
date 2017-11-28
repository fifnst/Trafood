package id.trafood.trafood.Models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by kulinerin 1 on 27/11/2017.
 */

public class PostPutDelRm {
    @SerializedName("status")
    String status;

    @SerializedName("result")
    Rumahmakan mRumahmakan;

    @SerializedName("message")
    String message;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Rumahmakan getmRumahmakan() {
        return mRumahmakan;
    }

    public void setmRumahmakan(Rumahmakan mRumahmakan) {
        this.mRumahmakan = mRumahmakan;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
