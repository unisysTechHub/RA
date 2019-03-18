package com.bvifsc.mbc.model;

import com.bvifsc.core.events.ResponseHandlingEvent;
import com.bvifsc.core.models.response.BaseResponse;
import com.bvifsc.core.models.response.PageResponseModel;
import com.bvifsc.mbc.fragments.MBCLoginFragment;

/**
 * Created by Ramesh on 26-11-2018.
 */
public class SignInResponseModel extends BaseResponse{

    PageResponseModel pageInfo;

    public SignInResponseModel(String pageType, String header) {
        super(pageType, header);
    }

    public PageResponseModel getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(PageResponseModel pageInfo) {
        this.pageInfo = pageInfo;
    }

    @Override
    public ResponseHandlingEvent buildResponseHandlingEvent() {
        return ResponseHandlingEvent.createEventToReplaceFragmentWithPopUp(MBCLoginFragment.newInstance(this),this);
    }
}
