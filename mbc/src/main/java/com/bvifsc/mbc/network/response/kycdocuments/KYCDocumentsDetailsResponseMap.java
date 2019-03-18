package com.bvifsc.mbc.network.response.kycdocuments;

import com.bvifsc.mbc.network.response.ResponseInfo;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Ramesh on 31-12-2018.
 */
public class KYCDocumentsDetailsResponseMap extends ResponseInfo {


    @SerializedName("responsePayload")
    KYCDocsDetailsMap kycDocsDetailsMap;

    public KYCDocsDetailsMap getKycDocsDetailsMap() {
        return kycDocsDetailsMap;
    }

    public void setKycDocsDetailsMap(KYCDocsDetailsMap kycDocsDetailsMap) {
        this.kycDocsDetailsMap = kycDocsDetailsMap;
    }
}
