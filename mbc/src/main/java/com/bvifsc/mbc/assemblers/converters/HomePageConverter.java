package com.bvifsc.mbc.assemblers.converters;

import android.util.Log;

import com.bvifsc.core.common.Converter;
import com.bvifsc.core.models.response.BaseResponse;
import com.bvifsc.core.models.response.BusinessError;
import com.bvifsc.mbc.Common.MBC_Constants;
import com.bvifsc.mbc.model.HomePageResponseModel;
import com.bvifsc.mbc.model.UserAuthInfo;
import com.bvifsc.mbc.network.response.signIn.SignInResponseMap;
import com.bvifsc.mbc.network.response.signIn.UserInfo;

import javax.inject.Inject;

/**
 * Created by Ramesh on 28-11-2018.
 */
public class HomePageConverter implements Converter {
    @Inject
    UserAuthInfo userAuthInfo;
    @Override
    public <R extends BaseResponse, T> R convert(T response) {

        SignInResponseMap signInResponse=(SignInResponseMap) response;
        HomePageResponseModel homePageResponseModel = new HomePageResponseModel("homePage",null);
        if(response!=null) {
                if(signInResponse.getResponseCode().equals("400")) {
                    homePageResponseModel.setSuccess(false);
                    BusinessError businessError = new BusinessError(signInResponse.getResponseCode(), null, signInResponse.getMessage(), "notification", "top");
                    homePageResponseModel.setBusinessError(businessError);
                }
                else
                {
                homePageResponseModel.setSuccess(true);
                homePageResponseModel.setMessage(((SignInResponseMap) response).getMessage());

                }

        }
        if(signInResponse.getSignedInUserMap()!= null) {
            homePageResponseModel.setUserAuthInfo(convert(signInResponse.getSignedInUserMap()));
            Log.d(MBC_Constants.APP_TAG, "entity id" + homePageResponseModel.getUserAuthInfo().getFirstName());
        }


        return (R) homePageResponseModel;
    }


    UserAuthInfo convert(UserInfo userInfo)
    {
        UserAuthInfo userAuthInfo= UserAuthInfo.getInstance();
        userAuthInfo.setRoleId(userInfo.getRoleId());
        userAuthInfo.setRoleCode(userInfo.getRoleCode());
        userAuthInfo.setUserId(userInfo.getUserId());
        userAuthInfo.setFirstName(userInfo.getFirstName());
        userAuthInfo.setLastName(userInfo.getLastName());
        userAuthInfo.setAuthCode(userInfo.getAuthCode());
        userAuthInfo.setEntityId(userInfo.getEntityId());
        userAuthInfo.setFirstTimeLogin(userInfo.isFirstTimeLogin());
        userAuthInfo.setEntityName(userInfo.getEntityName());
        userAuthInfo.setFirstTimeLogin(userInfo.isFirstTimeLogin());
        userAuthInfo.setApiKey(userInfo.getApiKey());
        String kycSubmittedInd = userInfo.getKycSubmittedInd();
        boolean isKycSubmitted;

        if(kycSubmittedInd!=null) {
            if (kycSubmittedInd.equals("Y"))
                isKycSubmitted = true;
            else
                isKycSubmitted=false;
        }
        else
            isKycSubmitted=false;

        userAuthInfo.setKycSubmitted(isKycSubmitted);

        userAuthInfo.setUserName(userInfo.getUserName());
        userAuthInfo.setPassword(userInfo.getPassword());
        userAuthInfo.setDocument(userInfo.getDocument());

        String authorizationInd = userInfo.getAuthorizationInd();
        boolean isKycApproved;
        if(authorizationInd!=null) {
            if (authorizationInd.equals("Y"))
                isKycApproved = true;
            else
                isKycApproved=false;
        }
        else
            isKycApproved=false;
        userAuthInfo.setKycApproved(isKycApproved);
        Log.d(MBC_Constants.APP_TAG,"KYC submited ind" + userInfo.getKycSubmittedInd());
        Log.d(MBC_Constants.APP_TAG,"API key" + userInfo.getApiKey());
        return userAuthInfo;
    }
}
