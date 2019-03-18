package com.bvifsc.mbc.network.response.kycdocuments;

import com.bvifsc.mbc.datamodel.KYC;
import com.bvifsc.mbc.network.request.FileData;
import com.bvifsc.mbc.network.response.ResponseInfo;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Ramesh on 14-12-2018.
 */
public class KYCDocumentsListResponseMap extends ResponseInfo {
    @SerializedName("responsePayload")
    List<KYCDocumentTypeMap>  kycDocumentTypeListMap;



    public List<KYCDocumentTypeMap> getKycDocumentTypeListMap() {
        return kycDocumentTypeListMap;
    }

    public void setKycDocumentTypeListMap(List<KYCDocumentTypeMap> kycDocumentTypeListMap) {
        this.kycDocumentTypeListMap = kycDocumentTypeListMap;
    }


}
