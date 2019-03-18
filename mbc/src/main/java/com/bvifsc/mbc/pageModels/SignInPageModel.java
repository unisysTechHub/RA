package com.bvifsc.mbc.pageModels;

import com.bvifsc.core.R;
import com.bvifsc.core.di.BVIRA_Application;
import com.bvifsc.core.models.response.Action;
import com.bvifsc.core.models.response.PageResponseModel;
import com.bvifsc.core.presenters.BasePresenter;
import com.bvifsc.mbc.Common.FragmentLoader;
import com.bvifsc.mbc.model.SignInResponseModel;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

/**
 * Created by Ramesh on 26-11-2018.
 */
public class SignInPageModel  implements FragmentLoader {
    private final String pageType = "signIn";
    private final String screenHeading = "RA SignIn ";
    private Map<String, Action> buttonMap = setButtonMap();
    private final String pageTitle = "SignIn";
    @Inject
    BasePresenter basePresenter;

    public SignInPageModel() {
        this.inject();

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

    public String getPageTitle() {
        return pageTitle;
    }

    void inject() {
        BVIRA_Application.getObjectGraph(BVIRA_Application.context).inject(this);
    }

    private Map<String, Action> setButtonMap() {
        Action primaryAction = new Action("homePage", "BVI_RA_MBC", "bviFscMBC", "POST");
        primaryAction.setTitle("SignIn");
      //  primaryAction.setBrowserUrl("http://182.74.133.91:8080/RAPORTAL/raportal/api/user/userlogin");
        primaryAction.setBrowserUrl(buildUrl());
        primaryAction.setServiceType("loginService");
        primaryAction.setIsService(true);

        Map<String, Action> buttonMap = new HashMap<>();
        buttonMap.put("PrimaryButton", primaryAction);

        Action secondaryAction = new Action("signUp", "BVI_RA_MBC", "bviFscMBC", "LOAD");
        secondaryAction.setTitle("SignUp");
        buttonMap.put("SecondaryButton", secondaryAction);

        return buttonMap;

    }

    String buildUrl()
    {
        String url =
                BVIRA_Application.context.getResources().getString(R.string.http)
                        + "://"
                        + BVIRA_Application.context.getResources().getString(R.string.ip_address) + ":"
                        + BVIRA_Application.context.getResources().getString(R.string.port)
                        + BVIRA_Application.context.getResources().getString(R.string.version)
                        + BVIRA_Application.context.getResources().getString(R.string.signIn);

        return url;

    }



    @Override
    public <T> void load() {
        SignInResponseModel signInResponseModel= new SignInResponseModel(getPageType(),getScreenHeading());
        signInResponseModel.setPageInfo(new PageResponseModel(getPageType(),getScreenHeading()));
        signInResponseModel.getPageInfo().setTitle(getPageTitle());
        signInResponseModel.getPageInfo().setButtonMap(getButtonMap());

        basePresenter.publishResponseEvent(signInResponseModel);

    }
}
