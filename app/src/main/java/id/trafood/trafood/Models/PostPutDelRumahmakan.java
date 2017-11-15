package id.trafood.trafood.Models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by kulinerin 1 on 13/10/2017.
 */

public class PostPutDelRumahmakan {

    @SerializedName("status")
    String status;

    @SerializedName("result")
    String mRumahmakan;

    @SerializedName("message")
    String message;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getmRumahmakan() {
        return mRumahmakan;
    }

    public void setmRumahmakan(String mRumahmakan) {
        this.mRumahmakan = mRumahmakan;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

