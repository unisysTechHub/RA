package com.bvifsc.mbc.assemblers.converters;

import com.bvifsc.core.common.Converter;
import com.bvifsc.core.models.response.BaseResponse;
import com.bvifsc.core.models.response.BusinessError;
import com.bvifsc.mbc.model.AmendCharterPageResponseModel;
import com.bvifsc.mbc.model.amendCharter.AmendCharterResponseModel;
import com.bvifsc.mbc.network.response.amendCharter.AmendCharterResponseMap;

/**
 * Created by Ramesh on 24-01-2019.
 */
public class AmendCharterResponseConverter implements Converter {
    @Override
    public <R extends BaseResponse, T> R convert(T var1) {

        AmendCharterResponseMap amendCharterResponseMap= (AmendCharterResponseMap) var1;
        AmendCharterResponseModel amendCharterResponseModel =null;
        if(amendCharterResponseMap != null)
        {
            amendCharterResponseModel= new AmendCharterResponseModel("homePage",null);

            if(!amendCharterResponseMap.isSuccess())
            {
                BusinessError businessError =new BusinessError(amendCharterResponseMap.getResponseCode(),
                        null,amendCharterResponseMap.getMessage(),"notification","top");

                amendCharterResponseModel.setBusinessError(businessError);



            }
            else
            {
                amendCharterResponseModel.setSuccess(true);
                amendCharterResponseModel.setMessage(amendCharterResponseMap.getMessage());



            }





        }
        return (R) amendCharterResponseModel;
    }
}
