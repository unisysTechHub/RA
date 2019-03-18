package com.bvifsc.mbc.network.response.registerMBC;

import com.bvifsc.mbc.network.response.ResponseInfo;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Ramesh on 07-01-2019.
 */
public class BusinessPurposeResponseMap extends ResponseInfo {
    @SerializedName("responsePayload")
    List<BusinessPurposeMap> businessPurposeMapList;

    public List<BusinessPurposeMap> getBusinessPurposeMapList() {
        return businessPurposeMapList;
    }

    public void setBusinessPurposeMapList(List<BusinessPurposeMap> businessPurposeMapList) {
        this.businessPurposeMapList = businessPurposeMapList;
    }
}
