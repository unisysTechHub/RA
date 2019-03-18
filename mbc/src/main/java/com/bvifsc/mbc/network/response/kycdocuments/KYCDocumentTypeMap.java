package com.bvifsc.mbc.network.response.kycdocuments;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Ramesh on 14-12-2018.
 */
public class KYCDocumentTypeMap {
    @SerializedName("documentTypeId")
    int documentTypeId;
    @SerializedName("documentType")
    String documentType;

    public int getDocumentTypeId() {
        return documentTypeId;
    }

    public void setDocumentTypeId(int documentTypeId) {
        this.documentTypeId = documentTypeId;
    }

    public String getDocumentType() {
        return documentType;
    }

    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }
}
