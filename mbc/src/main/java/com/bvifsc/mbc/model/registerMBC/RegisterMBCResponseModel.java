package com.bvifsc.mbc.model.registerMBC;

import com.bvifsc.core.events.ResponseHandlingEvent;
import com.bvifsc.core.models.response.BaseResponse;
import com.bvifsc.mbc.fragments.ErrorFragment;

/**
 * Created by Ramesh on 16-01-2019.
 */
public class RegisterMBCResponseModel extends BaseResponse {

    public RegisterMBCResponseModel(String pageType, String header) {
        super(pageType, header);
    }

    String mbcNumber;

    @Override
    public ResponseHandlingEvent buildResponseHandlingEvent() {
        return ResponseHandlingEvent.createEventToReplaceErrorFragment(ErrorFragment.newInstance(getMessage()),this);
    }

    public String getMbcNumber() {
        return mbcNumber;
    }

    public void setMbcNumber(String mbcNumber) {
        this.mbcNumber = mbcNumber;
    }
}
