package com.bvifsc.mbc.model.mbcDataView;

import android.os.Parcel;

import com.bvifsc.core.events.ResponseHandlingEvent;
import com.bvifsc.core.models.response.BaseResponse;
import com.bvifsc.core.models.response.PageResponseModel;
import com.bvifsc.mbc.datamodel.MBCData;
import com.bvifsc.mbc.fragments.MBCViewFragment;

/**
 * Created by Ramesh on 29-01-2019.
 */
public class MBCViewPageResponseModel extends BaseResponse {
    MBCData mbcDataModel;
    PageResponseModel pageInfo;

    public MBCViewPageResponseModel(String pageType, String header) {
        super(pageType, header);
    }

    protected MBCViewPageResponseModel(Parcel in) {
        super(in);
    }

    @Override
    public ResponseHandlingEvent buildResponseHandlingEvent() {
        return ResponseHandlingEvent.createEventToReplaceFragmentWithNoPopUp(MBCViewFragment.newInstance(this),this);
    }

    public MBCData getMbcDataModel() {
        return mbcDataModel;
    }

    public void setMbcDataModel(MBCData mbcDataModel) {
        this.mbcDataModel = mbcDataModel;
    }

    public PageResponseModel getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(PageResponseModel pageInfo) {
        this.pageInfo = pageInfo;
    }
}
