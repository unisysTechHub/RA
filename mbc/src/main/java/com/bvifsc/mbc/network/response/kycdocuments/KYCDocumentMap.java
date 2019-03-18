package com.bvifsc.mbc.network.response.kycdocuments;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Ramesh on 31-12-2018.
 */
public class KYCDocumentMap {

    @SerializedName("documentId")
    int documentId;
    @SerializedName("documentName")
    String documentName;

    @SerializedName("documentFileExten")
    String documentFileExten;

    @SerializedName("document")
    String imageEncodedString;

    @SerializedName("activeFlag")
    String activeFlag;

    @SerializedName("webDocumentTypes")
    KYCDocumentTypeMap kycDocumentTypeMap;


    public int getDocumentId() {
        return documentId;
    }

    public void setDocumentId(int documentId) {
        this.documentId = documentId;
    }

    public String getDocumentName() {
        return documentName;
    }

    public void setDocumentName(String documentName) {
        this.documentName = documentName;
    }

    public String getDocumentFileExten() {
        return documentFileExten;
    }

    public void setDocumentFileExten(String documentFileExten) {
        this.documentFileExten = documentFileExten;
    }

    public String getImageEncodedString() {
        return imageEncodedString;
    }

    public void setImageEncodedString(String imageEncodedString) {
        this.imageEncodedString = imageEncodedString;
    }

    public String getActiveFlag() {
        return activeFlag;
    }

    public void setActiveFlag(String activeFlag) {
        this.activeFlag = activeFlag;
    }

    public KYCDocumentTypeMap getKycDocumentTypeMap() {
        return kycDocumentTypeMap;
    }

    public void setKycDocumentTypeMap(KYCDocumentTypeMap kycDocumentTypeMap) {
        this.kycDocumentTypeMap = kycDocumentTypeMap;
    }
}
