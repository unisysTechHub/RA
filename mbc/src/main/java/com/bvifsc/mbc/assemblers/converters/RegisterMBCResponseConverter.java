package com.bvifsc.mbc.assemblers.converters;

import com.bvifsc.core.common.Converter;
import com.bvifsc.core.models.response.BaseResponse;
import com.bvifsc.core.models.response.BusinessError;
import com.bvifsc.mbc.model.registerMBC.RegisterMBCResponseModel;
import com.bvifsc.mbc.network.response.registerMBC.RegisterMBCResponseMap;

/**
 * Created by Ramesh on 16-01-2019.
 */
public class RegisterMBCResponseConverter implements Converter {
    @Override
    public <R extends BaseResponse, T> R convert(T var1) {

        RegisterMBCResponseMap registerMBCResponseMap= (RegisterMBCResponseMap) var1;
        RegisterMBCResponseModel registerMBCResponseModel=null;
        if(registerMBCResponseMap!=null)
        {
             registerMBCResponseModel = new RegisterMBCResponseModel("homePage",null);
            if(!registerMBCResponseMap.isSuccess())
            {
                BusinessError  businessError =new BusinessError(registerMBCResponseMap.getResponseCode(),
                        null,registerMBCResponseMap.getMessage(),"notification","top");

                registerMBCResponseModel.setBusinessError(businessError);



            }
            else
            {

                registerMBCResponseModel.setSuccess(true);
                registerMBCResponseModel.setMessage(registerMBCResponseMap.getMessage());
                if(registerMBCResponseMap.getMbcNumber() != null)
                    registerMBCResponseModel.setMbcNumber(registerMBCResponseMap.getMbcNumber());

            }



        }
        return (R) registerMBCResponseModel;
    }
}
