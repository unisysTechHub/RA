package com.bvifsc.mbc.presenters;



import android.util.Log;

import com.bvifsc.core.models.response.Action;
import com.bvifsc.core.models.response.BaseResponse;
import com.bvifsc.core.network.request.RequestExecutor;
import com.bvifsc.core.presenters.BasePresenter;
import com.bvifsc.mbc.Common.Callback;
import com.bvifsc.mbc.datamodel.KYCDocuments;
import com.bvifsc.mbc.model.UserAuthInfo;
import com.bvifsc.mbc.network.request.BaseRequest;
import com.bvifsc.mbc.network.request.FormData;


import org.greenrobot.eventbus.EventBus;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import javax.inject.Inject;

/**
 * Created by Ramesh on 19-12-2018.
 */
public class KYCDocumentsPresenter extends BasePresenter {
    String pageType;

    @Inject
    public KYCDocumentsPresenter(RequestExecutor requestExecutor, EventBus eventBus) {
        super(requestExecutor, eventBus);
    }

    public void uploadKYCDocs(KYCDocuments kyc, Action action)

    {
        FormData formData = new FormData();
        try {

            formData.addFile("kycDoc1",kyc.getList().get(0).getFileData());
            formData.addFile("kycDoc2",kyc.getList().get(1).getFileData());
            formData.addFile("kycDoc3",kyc.getList().get(2).getFileData());


            JSONArray kycDocTypes = new JSONArray();
            kycDocTypes.put(kyc.getList().get(0).getKycDocType());
            kycDocTypes.put((kyc.getList().get(1).getKycDocType()));
            kycDocTypes.put(kyc.getList().get(2).getKycDocType());

            JSONObject userKycTblJsonObj =new JSONObject();
            Log.d("@MBC", "userId" +UserAuthInfo.getInstance().getUserLoginId());
            userKycTblJsonObj.put("userId", UserAuthInfo.getInstance().getUserId());
            userKycTblJsonObj.put("kycDocumentTypeId",kycDocTypes);
            formData.addJson("userKycTblJson",userKycTblJsonObj);



        } catch (JSONException e) {
            e.printStackTrace();
        }

        pageType=action.getPageType();
        showProgressbar();
        executeAction(action,getResourceToConsume(action,formData,onActionScuessCallback()));




    }

    <R extends BaseResponse> Callback<R> onActionScuessCallback()
    {

        return  response -> {

                        publishResponseEvent(response);
                        hideProgressbar();

        };
    }
}
