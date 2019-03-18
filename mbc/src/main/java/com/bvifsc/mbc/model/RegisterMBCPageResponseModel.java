package com.bvifsc.mbc.model;

import com.bvifsc.core.events.ResponseHandlingEvent;
import com.bvifsc.core.models.response.BaseResponse;
import com.bvifsc.core.models.response.PageResponseModel;
import com.bvifsc.mbc.datamodel.MBCData;
import com.bvifsc.mbc.fragments.RegisterMBCFragment;
import com.bvifsc.mbc.model.registerMBC.RegisterMBCModel;

/**
 * Created by Ramesh on 03-01-2019.
 */
public class RegisterMBCPageResponseModel extends BaseResponse {
    PageResponseModel pageInfo;
    RegisterMBCModel registerMBCModel;
    MBCData registerMBCDataModel;

    public RegisterMBCPageResponseModel(String pageType, String header) {
        super(pageType, header);
    }

    @Override
    public ResponseHandlingEvent buildResponseHandlingEvent() {
       return ResponseHandlingEvent.createEventToReplaceFragmentWithNoPopUp(RegisterMBCFragment.newInstance(this),this);
       // return  ResponseHandlingEvent.createEventToReplaceFragmentWithNoPopUp(AmendPrincipalFragment.newInstance(this),this);
    }

    public RegisterMBCModel getRegisterMBCModel() {
        return registerMBCModel;
    }

    public void setRegisterMBCModel(RegisterMBCModel registerMBCModel) {
        this.registerMBCModel = registerMBCModel;
    }

    public PageResponseModel getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(PageResponseModel pageInfo) {
        this.pageInfo = pageInfo;
    }

    public MBCData getRegisterMBCDataModel() {
        return registerMBCDataModel;
    }

    public void setRegisterMBCDataModel(MBCData registerMBCDataModel) {
        this.registerMBCDataModel = registerMBCDataModel;
    }
}
