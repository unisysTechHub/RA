package com.bvifsc.mbc.network.response.registerMBC;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Ramesh on 07-01-2019.
 */
public class BusinessPurposeMap {
    @SerializedName("businessPurposeId")
    int businessPurposeId;

    @SerializedName("businessPurpose")
    String businessPurpose;

    public int getBusinessPurposeId() {
        return businessPurposeId;
    }

    public void setBusinessPurposeId(int businessPurposeId) {
        this.businessPurposeId = businessPurposeId;
    }

    public String getBusinessPurpose() {
        return businessPurpose;
    }

    public void setBusinessPurpose(String businessPurpose) {
        this.businessPurpose = businessPurpose;
    }
}
