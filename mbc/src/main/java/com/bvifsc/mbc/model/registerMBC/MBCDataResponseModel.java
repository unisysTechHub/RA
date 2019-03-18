package com.bvifsc.mbc.model.registerMBC;

import com.bvifsc.core.models.response.BaseResponse;
import com.bvifsc.mbc.datamodel.MBCData;

/**
 * Created by Ramesh on 23-01-2019.
 */
public class MBCDataResponseModel extends BaseResponse {
    MBCData mbcDataModel;

    public MBCDataResponseModel(String pageType, String header) {
        super(pageType, header);
    }

    public MBCData getMbcDataModel() {
        return mbcDataModel;
    }

    public void setMbcDataModel(MBCData mbcDataModel) {
        this.mbcDataModel = mbcDataModel;
    }
}
