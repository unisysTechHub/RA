package com.bvifsc.mbc.model.mbcDataView;

import android.os.Parcel;

import com.bvifsc.core.events.ResponseHandlingEvent;
import com.bvifsc.core.models.response.BaseResponse;
import com.bvifsc.mbc.datamodel.generic.MBCData;

/**
 * Created by Ramesh on 23-01-2019.
 */
public class MBCGenericDataResponseModel extends BaseResponse {
    MBCData mbcGenericDataModel;


    public MBCGenericDataResponseModel(String pageType, String header) {
        super(pageType, header);
    }

    @Override
    public ResponseHandlingEvent buildResponseHandlingEvent() {
        //return ResponseHandlingEvent.createEventToReplaceFragmentWithNoPopUp();
        return null;
    }

    protected MBCGenericDataResponseModel(Parcel in) {
        super(in);
    }

    public MBCData getMbcGenericDataModel() {
        return mbcGenericDataModel;
    }

    public void setMbcGenericDataModel(MBCData mbcGenericDataModel) {
        this.mbcGenericDataModel = mbcGenericDataModel;
    }


}
