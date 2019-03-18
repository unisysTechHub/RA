package com.bvifsc.mbc.presenters;

import android.util.Log;

import com.bvifsc.core.models.response.Action;
import com.bvifsc.core.models.response.BaseResponse;
import com.bvifsc.core.network.request.RequestExecutor;
import com.bvifsc.core.presenters.BasePresenter;
import com.bvifsc.mbc.Common.Callback;
import com.bvifsc.mbc.Common.SharedPreferencesUtils;
import com.bvifsc.mbc.datamodel.MBCData;
import com.bvifsc.mbc.events.OnNewMbcRegistered;
import com.bvifsc.mbc.model.registerMBC.RegisterMBCResponseModel;
import com.bvifsc.mbc.network.request.BaseRequest;
import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;
import org.json.JSONObject;

import javax.inject.Inject;

/**
 * Created by Ramesh on 14-01-2019.
 */
public class RegisterMBCPresenter extends BasePresenter{

    @Inject
    SharedPreferencesUtils sharedPreferencesUtils;
    @Inject
    public RegisterMBCPresenter(RequestExecutor requestExecutor, EventBus eventBus)
    {
        super(requestExecutor,eventBus);
    }

   public void registerMBC(Action action, MBCData registerMBC)
    {
        Log.d("@MBC","BasePresenter Register MBC");
        BaseRequest baseRequest = new BaseRequest();
        //Gson gson = new Gson();
        Gson gson = new GsonBuilder()
                .setExclusionStrategies(new ExclusionStrategy() {
                    @Override
                    public boolean shouldSkipField(FieldAttributes f) {

                        if(f.getName().equalsIgnoreCase("principalShareholderId") ||
                                f.getName().equalsIgnoreCase("participantShareholderId") )
                            return true;
                        else
                            return false;
                    }

                    @Override
                    public boolean shouldSkipClass(Class<?> clazz) {
                        return false;
                    }
                })
                .create();




        String registerMBCJSon =gson.toJson(registerMBC);
        Log.d("@MBC","JSON" + registerMBCJSon );



        try {
            baseRequest.setRequestParams(new JSONObject(registerMBCJSon));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        showProgressbar();
        executeAction(action,getResourceToConsume(action,baseRequest,onActionSuccessCallback()));
    }

    <R extends BaseResponse> Callback<R>   onActionSuccessCallback()
    {
        return  response -> {
            RegisterMBCResponseModel registerMBCResponseModel = (RegisterMBCResponseModel) response;

            sharedPreferencesUtils.saveRegisteredMBCNumber(registerMBCResponseModel.getMbcNumber());
            OnNewMbcRegistered onNewMbcRegistered = new OnNewMbcRegistered();
            onNewMbcRegistered.setMbcNumber(registerMBCResponseModel.getMbcNumber());
            eventBus.post(onNewMbcRegistered);
            publishResponseEvent(response);
            hideProgressbar();
        };

    }

}
