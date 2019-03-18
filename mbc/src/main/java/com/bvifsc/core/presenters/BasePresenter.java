package com.bvifsc.core.presenters;

import android.app.AlertDialog;
import android.os.Bundle;
import android.util.Log;

import com.bvifsc.core.R;
import com.bvifsc.core.events.ResponseHandlingEvent;
import com.bvifsc.core.models.response.Action;
import com.bvifsc.core.models.response.BaseResponse;
import com.bvifsc.core.network.request.Resource;
import com.bvifsc.mbc.Common.AppFragmentUtils;
import com.bvifsc.mbc.Common.Callback;
import com.bvifsc.core.network.request.RequestExecutor;
import com.bvifsc.mbc.Common.MBC_Constants;
import com.bvifsc.mbc.Common.fragments.CommonDialogFragment;
import com.bvifsc.mbc.events.OnExceptionEvent;
import com.bvifsc.mbc.events.OnResponseErrorEvent;
import com.bvifsc.mbc.events.ServerResponseEvent;
import com.bvifsc.mbc.fragments.ErrorFragment;
import com.bvifsc.mbc.network.request.BaseRequest;
import com.bvifsc.mbc.network.request.FormData;
import com.bvifsc.mbc.network.response.ResponseInfo;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Inject;

/**
 * Created by Ramesh on 28-11-2018.
 */
public class BasePresenter {
    public int x=1;
   protected RequestExecutor requestExecutor;
   protected  CommonDialogFragment commonDialogFragment;
    protected EventBus eventBus;
    @Inject
    public BasePresenter(RequestExecutor requestExecutor, EventBus eventBus)
    {
        Log.d("@Ramesh", "BasePresenter Injector");
        this.requestExecutor=requestExecutor;
        this.eventBus=eventBus;
    }

    public void executeAction(Action action, Resource resource)
    {
                //showProgressbar();
                requestExecutor.executeRequest(resource);

    }
    public Resource getResourceToConsume(Action action, Callback<BaseResponse>  onActionSuccessCallback  )
    {
        Resource.ResourceBuilder builder = new Resource.ResourceBuilder(action.getPageType(),action.getAppContext(), onActionSuccessCallback, getOnExceptionCallback());
        builder.extraParameters(action.getExtraParams());
        builder.onBusinessError(getOnBusinessErrorCallBack());
        builder.actionType(action.getActionType());
        builder.browseUrl(action.getBrowserUrl());
        return builder.build();
    }

    public  Resource getResourceToConsume(Action action, BaseRequest request, Callback<BaseResponse> onActionSuccessCallback) {

        Resource.ResourceBuilder builder = new Resource.ResourceBuilder(action.getPageType(),action.getAppContext(), onActionSuccessCallback, getOnExceptionCallback());
        builder.extraParameters(action.getExtraParams());
        builder.onBusinessError(getOnBusinessErrorCallBack());
        builder.bodyRequest(request);
        builder.browseUrl(action.getBrowserUrl());
        builder.actionType(action.getActionType());
        return builder.build();
    }
    public  Resource getResourceToConsume(Action action, FormData formData, Callback<BaseResponse> onActionSuccessCallback) {

        Resource.ResourceBuilder builder = new Resource.ResourceBuilder(action.getPageType(),action.getAppContext(), onActionSuccessCallback, getOnExceptionCallback());
        builder.extraParameters(action.getExtraParams());
        builder.onBusinessError(getOnBusinessErrorCallBack());
        builder.formData(formData);
        builder.browseUrl(action.getBrowserUrl());
        builder.actionType(action.getActionType());
        return builder.build();
    }

    protected  Resource getResourceToConsume(Action action, BaseRequest request, Callback<BaseResponse> onActionSuccessCallback,Callback<Exception> onExceptionCallback) {

        Resource.ResourceBuilder builder = new Resource.ResourceBuilder(action.getPageType(),action.getAppContext(), onActionSuccessCallback, onExceptionCallback);
        builder.extraParameters(action.getExtraParams());
        builder.onBusinessError(getOnBusinessErrorCallBack());
        builder.bodyRequest(request);
        builder.browseUrl(action.getBrowserUrl());
        builder.actionType(action.getActionType());
        return builder.build();
    }
    protected Resource getResourceToConsume(Action action, Callback<BaseResponse> onActionSuccessCallback, Callback<Exception> onExceptionCallback)
    {

        Resource.ResourceBuilder builder = new Resource.ResourceBuilder(action.getPageType(),action.getAppContext(), onActionSuccessCallback, onExceptionCallback);
        builder.extraParameters(action.getExtraParams());
        builder.onBusinessError(getOnBusinessErrorCallBack());
        builder.browseUrl(action.getBrowserUrl());
        builder.actionType(action.getActionType());
        return builder.build();
    }


    <R extends BaseResponse> Callback<R> getOnActionSuccessCallBack()
    {
        return result ->{
            Log.d("@Ramesh", "Load different page " + result.getPageType());
            if(result.getBusinessError().getUserMessage()!= null)
            {
                ResponseHandlingEvent responseHandlingEvent=
                        ResponseHandlingEvent.createEventToReplaceErrorFragment(ErrorFragment.newInstance(result.getBusinessError().getUserMessage()),result);
                EventBus.getDefault().post(responseHandlingEvent);

                // ServerResponseEvent serverResponseEvent = new ServerResponseEvent();
                // serverResponseEvent.setRespponseInfo(result);
                //processServerResponse(serverResponseEvent);

            }
            publishResponseEvent(result);
            hideProgressbar();
        };

    }

    <E extends  Exception> Callback<E> getOnExceptionCallback()
    {

        return  response -> {

            hideProgressbar();
            OnExceptionEvent onExceptionEvent = new OnExceptionEvent(response);
            onExceptionEvent.setExceptionMessage(response.getMessage());
            eventBus.post(onExceptionEvent);};

    }
    <R extends BaseResponse> Callback<R> getOnBusinessErrorCallBack()
    {
        return  response -> {
            hideProgressbar();
            OnResponseErrorEvent onResponseErrorEvent = new OnResponseErrorEvent();
            onResponseErrorEvent.setErrorMessage(response.getBusinessError().getUserMessage());
          //  onResponseErrorEvent.setPageType("errorPage");
            onResponseErrorEvent.setMessageStyle("top");
            //onResponseErrorEvent.setErrorFragment(ResponseHandlingEvent.createEventToReplaceErrorFragment(ErrorFragment.newInstance(response.getMessage()),null).fragment);
            EventBus.getDefault().post(onResponseErrorEvent);


        };

    }

    public void publishResponseEvent(BaseResponse response) {
        ResponseHandlingEvent responseHandlingEvent = response.buildResponseHandlingEvent();

        if (responseHandlingEvent == null) {
            Log.d("@MBC", "response handling event null");

        } else {
            EventBus.getDefault().post(responseHandlingEvent);
        }
    }
    public void publishBusinessError(OnResponseErrorEvent onResponseErrorEvent) {


        EventBus.getDefault().post(onResponseErrorEvent);

        //businessError
    }

    public void publishBusinessError(BaseResponse baseResponse)
    {
        OnResponseErrorEvent onResponseErrorEvent = new OnResponseErrorEvent();
        onResponseErrorEvent.setErrorMessage(baseResponse.getBusinessError().getUserMessage());
        onResponseErrorEvent.setMessageStyle("top");

        EventBus.getDefault().post(onResponseErrorEvent);
    }

    public void processServerResponse(ServerResponseEvent serverResponseEvent)
    {
        EventBus.getDefault().post(serverResponseEvent);

    }

    public  void showProgressbar()
    {       Bundle args = new Bundle();
        args.putBoolean("progressDialog",true);
        commonDialogFragment= CommonDialogFragment.newInstance(args);
        commonDialogFragment.show(AppFragmentUtils.getSupportFragmentManager(),"ProgressBar");


    }
    public  void hideProgressbar()
    {
        Log.d("@MBC", "hide progress bar");
        commonDialogFragment.getDialog().dismiss();
    }

}
