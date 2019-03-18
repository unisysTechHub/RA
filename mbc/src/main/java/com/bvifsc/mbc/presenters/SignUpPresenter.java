package com.bvifsc.mbc.presenters;
import android.util.Log;

import com.bvifsc.core.models.response.Action;
import com.bvifsc.core.models.response.BaseResponse;
import com.bvifsc.core.network.request.RequestExecutor;
import com.bvifsc.core.presenters.BasePresenter;
import com.bvifsc.mbc.Common.Callback;
import com.bvifsc.mbc.Common.FragmentLoader;
import com.bvifsc.mbc.Common.MBC_Constants;
import com.bvifsc.mbc.assemblers.MBC_PageLoder;
import com.bvifsc.mbc.datamodel.SignUp;
import com.bvifsc.mbc.pageModels.SignInPageModel;
import com.bvifsc.mbc.model.UserAuthInfo;
import com.bvifsc.mbc.network.request.BaseRequest;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

/**
 * Created by Ramesh on 12-12-2018.
 */
public class SignUpPresenter extends BasePresenter {

    String page;
    @Inject
    MBCLoginPresenter mbcLoginPresenter;
    @Inject
    public SignUpPresenter(RequestExecutor requestExecutor, EventBus eventBus) {
        super(requestExecutor, eventBus);
    }

    public void  signUp(SignUp signUpDataModel, Action action )
     {
         BaseRequest baseRequest = new BaseRequest();

         try {
             baseRequest.addParams("entityId",1);
             baseRequest.addParams("username",signUpDataModel.getUserName());
             baseRequest.addParams("password",signUpDataModel.getPassword());
             baseRequest.addParams("firstName",signUpDataModel.getFirstName());
             baseRequest.addParams("middleName",signUpDataModel.getMiddleName());
             baseRequest.addParams("lastName",signUpDataModel.getLastName());
             baseRequest.addParams("emailAddress",signUpDataModel.getEmail());
             baseRequest.addParams("city",signUpDataModel.getCity());
             baseRequest.addParams("phone",signUpDataModel.getPhoneNumber());
             baseRequest.addParams("streetAddress",signUpDataModel.getStreetAddress());
             baseRequest.addParams("zipcode",signUpDataModel.getZipCode());
             baseRequest.addParams("fax",signUpDataModel.getFax());
             baseRequest.addParams("securityAnswer",signUpDataModel.getSecurityAnswer());
             baseRequest.addParams("securityQuestionId",signUpDataModel.getSelectedSecurityQId());



         } catch (JSONException e) {
             e.printStackTrace();
         }

         page=action.getPageType();
         showProgressbar();
         executeAction(action,getResourceToConsume(action,baseRequest,onActionSuccessCallback()));


     }

     <R extends BaseResponse> Callback<R>  onActionSuccessCallback()
     {

         return  response -> {
                //response.setPageType(page);



                 if(response!= null) {
                     publishResponseEvent(response);

                     Log.d("@MBC", "password" + UserAuthInfo.getInstance().getPassword());
                     SignInPageModel signInPageModel = new SignInPageModel();
                     Action primaryAction =signInPageModel.getButtonMap().get(MBC_Constants.PRIMARY_BUTTON);

                     Map<String,String> extraPrarams = new HashMap<>();

                     extraPrarams.put("userName", UserAuthInfo.getInstance().getUserName());
                     extraPrarams.put("password",UserAuthInfo.getInstance().getPassword());

                     primaryAction.setExtraParams(extraPrarams);
                     mbcLoginPresenter.verifyCredentials(primaryAction);

                     new android.os.Handler().postDelayed(new Runnable() {
                         @Override
                         public void run() {

                             FragmentLoader fragmentLoader = null;
                             try {
                                 fragmentLoader = (FragmentLoader) MBC_PageLoder.PAGE_MAPPING.get(MBC_Constants.KYC_DOCUMENTS).newInstance();
                             } catch (IllegalAccessException e) {
                                 e.printStackTrace();
                             } catch (InstantiationException e) {
                                 e.printStackTrace();
                             }
                             fragmentLoader.load();
                         }
                     },5000);

                 }



             //       if(response.isSucess())
               //      publishResponseEvent(response);


             hideProgressbar();

         };
     }

}
