package com.bvifsc.mbc.assemblers.converters;

import com.bvifsc.core.common.Converter;
import com.bvifsc.core.models.response.BaseResponse;
import com.bvifsc.core.models.response.BusinessError;
import com.bvifsc.mbc.Common.CommonConverterUtils;
import com.bvifsc.mbc.model.signUp.SecurityQuestionResponseModel;
import com.bvifsc.mbc.network.response.signUp.SecurityQuestionsResponseMap;
import com.bvifsc.mbc.model.signUp.SecurityQuestionsModel;

/**
 * Created by Ramesh on 10-12-2018.
 */
public class SecurityQuestionsResponseConverter implements Converter{


    @Override
    public <R extends BaseResponse, T> R convert(T response) {

        SecurityQuestionsResponseMap securityQuestionsResponseMap=(SecurityQuestionsResponseMap)response;

        SecurityQuestionResponseModel securityQuestionResponseModel= new SecurityQuestionResponseModel("securityQuestions",null);

        if(!securityQuestionsResponseMap.isSuccess()) {
            securityQuestionResponseModel.setSuccess(false);
            BusinessError businessError = new BusinessError(securityQuestionsResponseMap.getResponseCode(), null, securityQuestionsResponseMap.getMessage(), "notification", "top");
            securityQuestionResponseModel.setBusinessError(businessError);
        }
        else
        {
            securityQuestionResponseModel.setSuccess(true);

        }

        SecurityQuestionsModel securityQuestionsModel=new SecurityQuestionsModel();
        securityQuestionsModel.setSecurityQuestionList(CommonConverterUtils.convert(securityQuestionsResponseMap.getSecurityQuestionMapList()));
        securityQuestionResponseModel.setSecurityQuestionsModel(securityQuestionsModel);
        return (R) securityQuestionResponseModel;
    }

}
