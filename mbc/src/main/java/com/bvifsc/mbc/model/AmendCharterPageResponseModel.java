package com.bvifsc.mbc.model;

import com.bvifsc.core.events.ResponseHandlingEvent;
import com.bvifsc.core.models.response.BaseResponse;
import com.bvifsc.mbc.fragments.AmendPrincipalFragment;

/**
 * Created by Ramesh on 03-01-2019.
 */
public class AmendCharterPageResponseModel extends BaseResponse {
    RegisterMBCPageResponseModel registerMBCPageResponseModel;

    public AmendCharterPageResponseModel(String pageType, String header) {
        super(pageType, header);
    }

    @Override
    public ResponseHandlingEvent buildResponseHandlingEvent() {
        return  ResponseHandlingEvent.createEventToReplaceFragmentWithNoPopUp(AmendPrincipalFragment.newInstance(getRegisterMBCPageResponseModel()),this);

    }

    public RegisterMBCPageResponseModel getRegisterMBCPageResponseModel() {
        return registerMBCPageResponseModel;
    }

    public void setRegisterMBCPageResponseModel(RegisterMBCPageResponseModel registerMBCPageResponseModel) {
        this.registerMBCPageResponseModel = registerMBCPageResponseModel;
    }
}
