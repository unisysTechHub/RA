package com.bvifsc.mbc.model;

import com.bvifsc.core.models.response.BaseResponse;

/**
 * Created by Ramesh on 17-12-2018.
 */
public class KYCDocumentsListResponseModel extends BaseResponse {
    KYCDocumentsListModel kycDocumentsListModel;


    public KYCDocumentsListResponseModel(String pageType, String header) {
        super(pageType, header);
    }


    public KYCDocumentsListModel getKycDocumentsListModel() {
        return kycDocumentsListModel;
    }

    public void setKycDocumentsListModel(KYCDocumentsListModel kycDocumentsListModel) {
        this.kycDocumentsListModel = kycDocumentsListModel;
    }
}
