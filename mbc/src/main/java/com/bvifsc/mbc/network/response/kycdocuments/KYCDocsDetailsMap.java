package com.bvifsc.mbc.network.response.kycdocuments;

import com.bvifsc.mbc.network.response.ResponseInfo;
import com.bvifsc.mbc.network.response.signIn.UserInfo;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Ramesh on 31-12-2018.
 */
public class KYCDocsDetailsMap  extends UserInfo{




  @SerializedName("webUserKycDetails")
    KYC3DocumentsDetails kyc3DocumentsDetails;



    public KYC3DocumentsDetails getKyc3DocumentsDetails() {
        return kyc3DocumentsDetails;
    }

    public void setKyc3DocumentsDetails(KYC3DocumentsDetails kyc3DocumentsDetails) {
        this.kyc3DocumentsDetails = kyc3DocumentsDetails;
    }


}
