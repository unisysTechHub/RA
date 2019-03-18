package com.bvifsc.mbc.model;

import com.bvifsc.core.events.ResponseHandlingEvent;
import com.bvifsc.core.models.response.BaseResponse;
import com.bvifsc.core.models.response.PageResponseModel;
import com.bvifsc.mbc.fragments.MBC_SignUpFragment;
import com.bvifsc.mbc.model.signUp.SecurityQuestionsModel;

/**
 * Created by Ramesh on 10-12-2018.
 */
public class SignUpPageResponseModel extends BaseResponse{
    PageResponseModel pageInfo;

    SecurityQuestionsModel securityQuestionsModel;

    @Override
    public ResponseHandlingEvent buildResponseHandlingEvent() {
        return ResponseHandlingEvent.createEventToReplaceFragmentWithPopUp(MBC_SignUpFragment.newInstance(this),this);
    }

    public SignUpPageResponseModel(String pageType, String header) {
        super(pageType, header);
    }


    public PageResponseModel getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(PageResponseModel pageInfo) {
        this.pageInfo = pageInfo;
    }

    public SecurityQuestionsModel getSecurityQuestionsModel() {
        return securityQuestionsModel;
    }

    public void setSecurityQuestionsModel(SecurityQuestionsModel securityQuestionsModel) {
        this.securityQuestionsModel = securityQuestionsModel;
    }
}
