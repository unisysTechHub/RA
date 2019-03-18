package com.bvifsc.mbc.assemblers.converters;

import android.util.Log;

import com.bvifsc.core.common.Converter;
import com.bvifsc.core.models.response.BaseResponse;
import com.bvifsc.core.models.response.BusinessError;
import com.bvifsc.mbc.Common.MBC_Constants;
import com.bvifsc.mbc.model.registerMBC.MBCDataResponseModel;
import com.bvifsc.mbc.network.response.amendCharter.MBCDataResponseMap;

/**
 * Created by Ramesh on 23-01-2019.
 */
public class MBCDataResponseConverter implements Converter {
    @Override
    public <R extends BaseResponse, T> R convert(T var1) {
                MBCDataResponseMap mbcDataResponseMap= (MBCDataResponseMap)var1;

        MBCDataResponseModel mbcDataResponseModel= null;
        if(mbcDataResponseMap != null)
        {
            mbcDataResponseModel = new MBCDataResponseModel(MBC_Constants.MBC_DATA,null);

            if(!mbcDataResponseMap.isSuccess())
            {
                mbcDataResponseModel.setSuccess(false);
                BusinessError businessError= new BusinessError(mbcDataResponseMap.getResponseCode(),null,mbcDataResponseMap.getMessage(),
                        MBC_Constants.NOTIFICATION,MBC_Constants.TOP);
                mbcDataResponseModel.setBusinessError(businessError);

            }
            else
            {
                mbcDataResponseModel.setSuccess(true);

                if(mbcDataResponseMap.getRegisterMBCDataMap() != null)
                {
                    mbcDataResponseModel.setMbcDataModel(mbcDataResponseMap.getRegisterMBCDataMap());

                }

            }



        }


        return (R) mbcDataResponseModel;
    }
}
