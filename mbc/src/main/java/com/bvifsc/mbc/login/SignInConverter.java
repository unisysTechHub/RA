package com.bvifsc.mbc.login;

import android.util.Log;

import com.bvifsc.core.common.Converter;
import com.bvifsc.core.models.response.BaseResponse;
import com.bvifsc.core.models.response.PageResponseModel;
import com.bvifsc.mbc.Common.MBC_Constants;
import com.bvifsc.mbc.model.SignInResponseModel;
import com.bvifsc.mbc.pageModels.SignInPageModel;

/**
 * Created by Ramesh on 26-11-2018.
 */
public class SignInConverter  implements Converter {

    @Override
    public <R extends BaseResponse, T> R convert(T var1) {
        SignInResponseModel signInResponseModel = null;
        SignInPageModel signInPageModel= new SignInPageModel();

        signInResponseModel = new SignInResponseModel(signInPageModel.getPageType(),signInPageModel.getScreenHeading());
        signInResponseModel.setPageInfo(new PageResponseModel(signInPageModel.getPageType(),signInPageModel.getScreenHeading()));
        signInResponseModel.getPageInfo().setButtonMap(signInPageModel.getButtonMap());
        Log.d("@RAMBCAPP","browseUrl" + signInPageModel.getButtonMap().get(MBC_Constants.PRIMARY_BUTTON).getBrowserUrl() );

        return (R) signInResponseModel;
    }

}
