package com.bvifsc.mbc.network.response.mbcView;

import com.bvifsc.mbc.datamodel.generic.MBCData;
import com.bvifsc.mbc.network.response.ResponseInfo;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Ramesh on 23-01-2019.
 */
public class MBCGenericDataResponseMap extends ResponseInfo {

    @SerializedName("responsePayload")
    MBCData registerMBCDataMap;

    public MBCData getRegisterMBCDataMap() {
        return registerMBCDataMap;
    }

    public void setRegisterMBCDataMap(MBCData registerMBCDataMap) {
        this.registerMBCDataMap = registerMBCDataMap;
    }
}
