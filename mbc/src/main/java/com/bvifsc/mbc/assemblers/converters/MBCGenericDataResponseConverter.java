package com.bvifsc.mbc.assemblers.converters;

import com.bvifsc.core.common.Converter;
import com.bvifsc.core.models.response.BaseResponse;
import com.bvifsc.core.models.response.BusinessError;
import com.bvifsc.mbc.Common.MBC_Constants;
import com.bvifsc.mbc.model.mbcDataView.MBCGenericDataResponseModel;
import com.bvifsc.mbc.network.response.mbcView.MBCGenericDataResponseMap;

/**
 * Created by Ramesh on 28-01-2019.
 */
public class MBCGenericDataResponseConverter implements Converter {
    @Override
    public <R extends BaseResponse, T> R convert(T var1) {
        MBCGenericDataResponseMap mbcGenericDataResponseMap= (MBCGenericDataResponseMap) var1;

        MBCGenericDataResponseModel mbcGenericDataResponseModel= null;
        if(mbcGenericDataResponseMap != null)
        {
            mbcGenericDataResponseModel = new MBCGenericDataResponseModel(MBC_Constants.MBC_DATA,null);

            if(!mbcGenericDataResponseMap.isSuccess())
            {
                mbcGenericDataResponseModel.setSuccess(false);
                BusinessError businessError= new BusinessError(mbcGenericDataResponseMap.getResponseCode(),null,mbcGenericDataResponseMap.getMessage(),
                        MBC_Constants.NOTIFICATION,MBC_Constants.TOP);
                mbcGenericDataResponseModel.setBusinessError(businessError);

            }
            else
            {
                mbcGenericDataResponseModel.setSuccess(true);

                if(mbcGenericDataResponseMap.getRegisterMBCDataMap() != null)
                {
                    mbcGenericDataResponseModel.setMbcGenericDataModel(mbcGenericDataResponseMap.getRegisterMBCDataMap());

                }

            }

        }


        return (R) mbcGenericDataResponseModel;
    }
}

