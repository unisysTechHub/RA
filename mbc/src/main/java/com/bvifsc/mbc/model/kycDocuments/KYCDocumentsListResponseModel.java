package com.bvifsc.mbc.model.kycDocuments;

import com.bvifsc.core.models.response.BaseResponse;
import com.bvifsc.mbc.model.kycDocuments.KYCDocumentsListModel;

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
