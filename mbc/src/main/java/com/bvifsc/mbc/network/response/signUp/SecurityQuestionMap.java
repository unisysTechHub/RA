package com.bvifsc.mbc.network.response.signUp;

import com.bvifsc.mbc.network.response.ResponseInfo;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Ramesh on 10-12-2018.
 */
public class SecurityQuestionMap  {


    @SerializedName("securityQuestionId")
    int securityQuestionId;
    @SerializedName("questionDesc")
    String questionDesc;

    public int getSecurityQuestionId() {
        return securityQuestionId;
    }

    public void setSecurityQuestionId(int securityQuestionId) {
        this.securityQuestionId = securityQuestionId;
    }

    public String getQuestionDesc() {
        return questionDesc;
    }

    public void setQuestionDesc(String questionDesc) {
        this.questionDesc = questionDesc;
    }

}
