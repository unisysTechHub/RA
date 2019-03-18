package com.bvifsc.mbc.model;

import android.app.Application;
import android.content.Context;

import com.bvifsc.core.MBCHomeActivity;
import com.bvifsc.core.di.BVIRA_Application;
import com.bvifsc.core.events.ResponseHandlingEvent;
import com.bvifsc.core.models.response.BaseResponse;
import com.bvifsc.mbc.fragments.ErrorFragment;
import com.bvifsc.mbc.presenters.MBCLoginPresenter;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by Ramesh on 28-11-2018.
 */
public class HomePageResponseModel extends BaseResponse{


       UserAuthInfo userAuthInfo=UserAuthInfo.getInstance();
    @Inject
    public HomePageResponseModel(String pageType, String header) {
        super(pageType, header);


    }

    @Override
    public ResponseHandlingEvent buildResponseHandlingEvent() {
        //String welcomeMessage =  userAuthInfo.getEntityName();
        return ResponseHandlingEvent.createEventToReplaceErrorFragment(ErrorFragment.newInstance(getMessage()),this);
    }

    public UserAuthInfo getUserAuthInfo() {

        return userAuthInfo;
    }

    public void setUserAuthInfo(UserAuthInfo userAuthInfo) {
        this.userAuthInfo = userAuthInfo;
    }
}
