package com.bvifsc.mbc.pageModels;

import com.bvifsc.core.R;
import com.bvifsc.core.di.BVIRA_Application;
import com.bvifsc.core.models.response.Action;
import com.bvifsc.core.models.response.BaseResponse;
import com.bvifsc.core.models.response.PageResponseModel;

import com.bvifsc.core.presenters.BasePresenter;
import com.bvifsc.mbc.Common.Callback;
import com.bvifsc.mbc.Common.FragmentLoader;
import com.bvifsc.mbc.Common.MBC_Constants;
import com.bvifsc.mbc.model.signUp.SecurityQuestionResponseModel;
import com.bvifsc.mbc.model.SignUpPageResponseModel;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

/**
 * Created by Ramesh on 10-12-2018.
 */
public class SignUpPageModel implements FragmentLoader {
    private final String pageType= MBC_Constants.signUp;
    private final String  screenHeading= "RA Customer SignUp ";
    private Map<String,Action> buttonMap =setButtonMap();
    private final String pageTitle="SignUp";
    @Inject
    BasePresenter basePresenter;
    public SignUpPageModel()
    {
        inject();

    }

  private  void inject(){
        BVIRA_Application.getObjectGraph(BVIRA_Application.context).inject(this);
    }


    public String getPageType() {
        return pageType;
    }

    public String getScreenHeading() {
        return screenHeading;
    }

    public Map<String, Action> getButtonMap() {
        return this.buttonMap;
    }


    private   Map<String,Action>  setButtonMap() {
        Action primaryAction = new Action("signUp","BVI_RA_MBC","bviFscMBC","POST");
        primaryAction.setTitle("SignUp");
       // primaryAction.setBrowserUrl( " http://182.74.133.91:8080/RAPORTAL/raportal/api/user/usersignup");
        primaryAction.setBrowserUrl(buildUrl());

        Map<String,Action> buttonMap = new HashMap<>();
        buttonMap.put("PrimaryButton",primaryAction);

        Action secondaryAction = new Action("back","BVI_RA_MBC","bviFscMBC",null);
        secondaryAction.setTitle("back");
        buttonMap.put("SecondaryButton",secondaryAction);

        return buttonMap;

    }

    String buildUrl()
    {String url =
            BVIRA_Application.context.getResources().getString(R.string.http)
                    + "://"
                    + BVIRA_Application.context.getResources().getString(R.string.ip_address) + ":"
                    + BVIRA_Application.context.getResources().getString(R.string.port)
                    + BVIRA_Application.context.getResources().getString(R.string.version)
                    + BVIRA_Application.context.getResources().getString(R.string.signUp);

        return url;
    }


   private void  getSecurityQuestions()
    {
        Action securityQuestionAction = new Action("securityQuestions","BVI_RA_MBC","bviFscMBC","GET");
        securityQuestionAction.setTitle("SecurityQuestions");
        //securityQuestionAction.setBrowserUrl( "http://182.74.133.91:8080/RAPORTAL/raportal/api/core/listofquestiondesc");
        securityQuestionAction.setBrowserUrl(buildSecurityQsUrl());

        basePresenter.showProgressbar();
        basePresenter.executeAction(securityQuestionAction,basePresenter.getResourceToConsume(securityQuestionAction, onActionSuccessCallback() ));

    }

    String buildSecurityQsUrl()
    {
        String url =
                BVIRA_Application.context.getResources().getString(R.string.http)
                        + "://"
                        + BVIRA_Application.context.getResources().getString(R.string.ip_address) + ":"
                        + BVIRA_Application.context.getResources().getString(R.string.port)
                        + BVIRA_Application.context.getResources().getString(R.string.version)
                        + BVIRA_Application.context.getResources().getString(R.string.securityQuestions);

        return url;


    }

   <T extends BaseResponse> Callback<T> onActionSuccessCallback()
    {
            return response -> {
                SecurityQuestionResponseModel securityQuestionResponseModel= (SecurityQuestionResponseModel) response;
                SignUpPageResponseModel signUpPageResponseModel= new SignUpPageResponseModel(getPageType(),getScreenHeading());
                signUpPageResponseModel.setPageInfo(new PageResponseModel(getPageType(),getScreenHeading()));
                signUpPageResponseModel.getPageInfo().setButtonMap(getButtonMap());
                signUpPageResponseModel.setSecurityQuestionsModel(securityQuestionResponseModel.getSecurityQuestionsModel());
                basePresenter.publishResponseEvent(signUpPageResponseModel);
                basePresenter.hideProgressbar();

            };

    }

    @Override
    public <T> void load() {
        getSecurityQuestions();
        //onActionSuccessCallback().notify(new SecurityQuestionResponseModel(null,null));

    }
}
