package com.bvifsc.mbc.network.response.amendCharter;

import com.bvifsc.mbc.datamodel.MBCData;
import com.bvifsc.mbc.network.response.ResponseInfo;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Ramesh on 24-01-2019.
 */
public class AmendCharterResponseMap extends ResponseInfo {
    @SerializedName("responsePayload")
    MBCData registerMBCDataMap;

    public MBCData getRegisterMBCDataMap() {
        return registerMBCDataMap;
    }

    public void setRegisterMBCDataMap(MBCData registerMBCDataMap) {
        this.registerMBCDataMap = registerMBCDataMap;
    }
}
