package com.bvifsc.mbc.model.kycDocuments;

import com.bvifsc.core.models.response.BaseResponse;
import com.bvifsc.mbc.datamodel.KYC;
import com.bvifsc.mbc.network.response.signIn.UserInfo;

import java.util.List;

/**
 * Created by Ramesh on 31-12-2018.
 */
public class KYCDocsDetailsResponseModel extends BaseResponse {
    List<KYC> userKycDocsDetailsList;

    public KYCDocsDetailsResponseModel(String pageType, String header) {
        super(pageType, header);
    }

    public List<KYC> getUserKycDocsDetailsList() {
        return userKycDocsDetailsList;
    }

    public void setUserKycDocsDetailsList(List<KYC> userKycDocsDetailsList) {
        this.userKycDocsDetailsList = userKycDocsDetailsList;
    }



}
