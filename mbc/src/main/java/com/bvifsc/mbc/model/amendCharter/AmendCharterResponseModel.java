package com.bvifsc.mbc.model.amendCharter;

import com.bvifsc.core.events.ResponseHandlingEvent;
import com.bvifsc.core.models.response.BaseResponse;
import com.bvifsc.mbc.fragments.ErrorFragment;

/**
 * Created by Ramesh on 24-01-2019.
 */
public class AmendCharterResponseModel extends BaseResponse {

    public AmendCharterResponseModel(String pageType, String header) {
        super(pageType, header);
    }

    @Override
    public ResponseHandlingEvent buildResponseHandlingEvent() {
        return ResponseHandlingEvent.createEventToReplaceErrorFragment(ErrorFragment.newInstance(getMessage()),this);
    }
}
