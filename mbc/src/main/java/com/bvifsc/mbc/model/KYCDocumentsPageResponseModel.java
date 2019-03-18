package com.bvifsc.mbc.model;

import com.bvifsc.core.events.ResponseHandlingEvent;
import com.bvifsc.core.models.response.BaseResponse;
import com.bvifsc.core.models.response.PageResponseModel;
import com.bvifsc.mbc.datamodel.KYC;
import com.bvifsc.mbc.fragments.KYCDocumentsFragment;
import com.bvifsc.mbc.model.kycDocuments.KYCDocumentsListModel;

import java.util.List;

/**
 * Created by Ramesh on 17-12-2018.
 */
public class KYCDocumentsPageResponseModel extends BaseResponse {
    PageResponseModel pageInfo;
    KYCDocumentsListModel kycDocumentsListModel;
    List<KYC> kycDocsDetails;



    public KYCDocumentsPageResponseModel(String pageType, String header) {
        super(pageType, header);
    }

    @Override
    public ResponseHandlingEvent buildResponseHandlingEvent() {
        return ResponseHandlingEvent.createEventToReplaceFragmentWithNoPopUp(KYCDocumentsFragment.newInstance(this),this);
    }

    public PageResponseModel getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(PageResponseModel pageInfo) {
        this.pageInfo = pageInfo;
    }

    public KYCDocumentsListModel getKycDocumentsListModel() {
        return kycDocumentsListModel;
    }

    public void setKycDocumentsListModel(KYCDocumentsListModel kycDocumentsListModel) {
        this.kycDocumentsListModel = kycDocumentsListModel;
    }

    public List<KYC> getKycDocsDetails() {
        return kycDocsDetails;
    }

    public void setKycDocsDetails(List<KYC> kycDocsDetails) {
        this.kycDocsDetails = kycDocsDetails;
    }
}
