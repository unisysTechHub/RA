package com.bvifsc.mbc.presenters;

import android.util.Log;

import com.bvifsc.core.models.response.Action;
import com.bvifsc.core.models.response.BaseResponse;
import com.bvifsc.core.network.request.RequestExecutor;
import com.bvifsc.core.presenters.BasePresenter;
import com.bvifsc.mbc.Common.Callback;
import com.bvifsc.mbc.datamodel.MBCData;
import com.bvifsc.mbc.network.request.BaseRequest;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.inject.Inject;

/**
 * Created by Ramesh on 14-01-2019.
 */
public class AmendCharterPresenter extends BasePresenter{

    @Inject
    public AmendCharterPresenter(RequestExecutor requestExecutor, EventBus eventBus)
    {
        super(requestExecutor,eventBus);
    }

   public void amendCharter(Action action, MBCData mbcData)
    {
        Log.d("@MBC","BasePresenter Register MBC");
        BaseRequest baseRequest = new BaseRequest();
        Gson gson = new Gson();

        String mbcDataJSon =gson.toJson(mbcData);
        Log.d("@MBC","JSON" + mbcDataJSon );
        try {
            JSONObject jsonObject = new JSONObject(mbcDataJSon);
            JSONArray jsonArray=jsonObject.getJSONArray("participantShareholderTbls");
            for(int i=0;i<jsonArray.length();i++)
            {
                int participantId =((JSONObject)jsonArray.get(i)).getInt("participantShareholderId");
                if(participantId == 0)
                {
                    ((JSONObject)jsonArray.get(i)).remove("participantShareholderId");

                }


            }
            mbcDataJSon=jsonObject.toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }


        try {
            baseRequest.setRequestParams(new JSONObject(mbcDataJSon));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        showProgressbar();
        executeAction(action,getResourceToConsume(action,baseRequest,onActionSuccessCallback()));
    }

    <R extends BaseResponse> Callback<R>   onActionSuccessCallback()
    {
        return  response -> {

            publishResponseEvent(response);
            hideProgressbar();
        };

    }

}
