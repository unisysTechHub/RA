package com.bvifsc.mbc.network.response;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Ramesh on 27-11-2018.
 */
public class ResponseInfo {

    @SerializedName("success")
    boolean success;
    @SerializedName("message")
    String message;
    @SerializedName("responseCode")
    String responseCode;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }
}
