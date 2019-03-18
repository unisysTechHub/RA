package com.bvifsc.mbc.network.response.signUp;

import com.bvifsc.mbc.network.response.ResponseInfo;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Ramesh on 10-12-2018.
 */
public class SecurityQuestionsResponseMap extends ResponseInfo {
    @SerializedName("responsePayload")
    List<SecurityQuestionMap> securityQuestionMapList;

    public List<SecurityQuestionMap> getSecurityQuestionMapList() {
        return securityQuestionMapList;
    }

    public void setSecurityQuestionMapList(List<SecurityQuestionMap> securityQuestionMapList) {
        this.securityQuestionMapList = securityQuestionMapList;
    }
}
