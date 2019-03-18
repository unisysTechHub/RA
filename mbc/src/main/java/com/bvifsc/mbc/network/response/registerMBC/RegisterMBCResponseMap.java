package com.bvifsc.mbc.network.response.registerMBC;

import com.bvifsc.mbc.network.response.ResponseInfo;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Ramesh on 16-01-2019.
 */
public class RegisterMBCResponseMap extends ResponseInfo {
    @SerializedName("responsePayload")
    String  mbcNumber;

    public String getMbcNumber() {
        return mbcNumber;
    }

    public void setMbcNumber(String mbcNumber) {
        this.mbcNumber = mbcNumber;
    }
}
