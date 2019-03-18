package com.bvifsc.mbc.model;

import com.bvifsc.core.models.response.BaseResponse;

/**
 * Created by Ramesh on 10-12-2018.
 */
public class SecurityQuestionResponseModel extends BaseResponse {
    SecurityQuestionsModel securityQuestionsModel;
    public SecurityQuestionResponseModel(String pageType, String header) {
        super(pageType, header);

    }

    public SecurityQuestionsModel getSecurityQuestionsModel() {
        return securityQuestionsModel;
    }

    public void setSecurityQuestionsModel(SecurityQuestionsModel securityQuestionsModel) {
        this.securityQuestionsModel = securityQuestionsModel;
    }
}
