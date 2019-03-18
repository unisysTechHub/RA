package com.bvifsc.mbc.network.response.kycdocuments;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Ramesh on 31-12-2018.
 */
public class KYC3DocumentsDetails {
    @SerializedName("kycDocument1Id")
    KYCDocumentMap kycDocument1Map;

    @SerializedName("kycDocument2Id")
    KYCDocumentMap kycDocument2Map;

    @SerializedName("kycDocument3Id")
    KYCDocumentMap kycDocument3Map;
    @SerializedName("kycDoc1AuthComment")
    String kycDoc1AuthComment;

    @SerializedName("kycDoc2AuthComment")
    String kycDoc2AuthComment;

    @SerializedName("kycDoc3AuthComment")
    String kycDoc3AuthComment;


    public KYCDocumentMap getKycDocument1Map() {
        return kycDocument1Map;
    }

    public void setKycDocument1Map(KYCDocumentMap kycDocument1Map) {
        this.kycDocument1Map = kycDocument1Map;
    }

    public KYCDocumentMap getKycDocument2Map() {
        return kycDocument2Map;
    }

    public void setKycDocument2Map(KYCDocumentMap kycDocument2Map) {
        this.kycDocument2Map = kycDocument2Map;
    }

    public KYCDocumentMap getKycDocument3Map() {
        return kycDocument3Map;
    }

    public void setKycDocument3Map(KYCDocumentMap kycDocument3Map) {
        this.kycDocument3Map = kycDocument3Map;
    }
    public String getKycDoc1AuthComment() {
        return kycDoc1AuthComment;
    }

    public void setKycDoc1AuthComment(String kycDoc1AuthComment) {
        this.kycDoc1AuthComment = kycDoc1AuthComment;
    }

    public String getKycDoc2AuthComment() {
        return kycDoc2AuthComment;
    }

    public void setKycDoc2AuthComment(String kycDoc2AuthComment) {
        this.kycDoc2AuthComment = kycDoc2AuthComment;
    }

    public String getKycDoc3AuthComment() {
        return kycDoc3AuthComment;
    }

    public void setKycDoc3AuthComment(String kycDoc3AuthComment) {
        this.kycDoc3AuthComment = kycDoc3AuthComment;
    }
}
