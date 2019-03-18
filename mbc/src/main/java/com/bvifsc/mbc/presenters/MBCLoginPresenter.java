package com.bvifsc.mbc.presenters;

import android.app.Application;
import android.content.Context;
import android.provider.SyncStateContract;
import android.util.Log;

import com.bvifsc.core.MBCHomeActivity;
import com.bvifsc.core.di.BVIRA_Application;
import com.bvifsc.core.models.response.Action;
import com.bvifsc.core.models.response.BaseResponse;
import com.bvifsc.core.network.request.RequestExecutor;
import com.bvifsc.core.presenters.BasePresenter;
import com.bvifsc.mbc.Common.Callback;
import com.bvifsc.mbc.Common.MBC_Constants;
import com.bvifsc.mbc.events.OnResponseErrorEvent;
import com.bvifsc.mbc.model.HomePageResponseModel;
import com.bvifsc.mbc.model.UserAuthInfo;
import com.bvifsc.mbc.network.request.BaseRequest;
import com.bvifsc.mbc.network.response.ResponseInfo;
import com.bvifsc.mbc.network.response.signIn.UserInfo;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.Provides;

/**
 * Created by Ramesh on 27-11-2018.
 */
public class MBCLoginPresenter extends BasePresenter{
    public static String TAG = "MBCLoginPresenter";


    UserAuthInfo userAuthInfo;


    @Inject
    public MBCLoginPresenter(RequestExecutor requestExecutor, EventBus eventBus)
    {

       super(requestExecutor,eventBus);
        Log.d(MBC_Constants.APP_TAG,"login presenter constructor invoked");
       this.eventBus=eventBus;

    }

  public   void  verifyCredentials(Action action)  {
        BaseRequest baseRequest = new BaseRequest();

      try {
          baseRequest.addParams("username",action.getExtraParams().get("userName"));
          baseRequest.addParams("password",action.getExtraParams().get("password"));
          baseRequest.addParams("entityId", 1);
      } catch (JSONException e) {
          e.printStackTrace();
      }

      showProgressbar();
      executeAction(action,getResourceToConsume(action,baseRequest,getOnSuccessCallBack()));


  }


  <R extends BaseResponse>  Callback<R> getOnSuccessCallBack()
    {
        return  result ->{

            HomePageResponseModel homePageResponseModel=(HomePageResponseModel) result;


           this.userAuthInfo=homePageResponseModel.getUserAuthInfo();
            Log.d("@MBCRA", "" + userAuthInfo.getRoleCode() +  userAuthInfo.getRoleId() + userAuthInfo.getAuthCode());
            if(userAuthInfo.getRoleCode().equalsIgnoreCase("USER") && userAuthInfo.getRoleId() == 4)
                     publishResponseEvent(result);
            else {
                Log.d("@MBCRA", "not a RA Customer");
                OnResponseErrorEvent onResponseErrorEvent = new OnResponseErrorEvent();
                onResponseErrorEvent.setErrorMessage("Invalid User Credentials");
                onResponseErrorEvent.setMessageStyle("top");
                publishBusinessError(onResponseErrorEvent);

            }
            hideProgressbar();

        };
    }

}
