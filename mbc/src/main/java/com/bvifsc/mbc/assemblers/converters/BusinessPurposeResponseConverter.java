package com.bvifsc.mbc.assemblers.converters;

import com.bvifsc.core.common.Converter;
import com.bvifsc.core.models.response.BaseResponse;
import com.bvifsc.core.models.response.BusinessError;
import com.bvifsc.mbc.Common.MBC_Constants;
import com.bvifsc.mbc.model.registerMBC.BusinessPurpose;
import com.bvifsc.mbc.model.registerMBC.BusinessPurposeResponseModel;
import com.bvifsc.mbc.network.response.registerMBC.BusinessPurposeMap;
import com.bvifsc.mbc.network.response.registerMBC.BusinessPurposeResponseMap;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ramesh on 07-01-2019.
 */
public class BusinessPurposeResponseConverter implements Converter {
    @Override
    public <R extends BaseResponse, T> R convert(T var1) {

        BusinessPurposeResponseMap businessPurposeResponseMap=(BusinessPurposeResponseMap) var1;
        BusinessPurposeResponseModel businessPurposeResponseModel= null;

        if(businessPurposeResponseMap != null)
        {
            businessPurposeResponseModel= new BusinessPurposeResponseModel(MBC_Constants.BUSINESS_PURPOSE,null);

            if(businessPurposeResponseMap.isSuccess())
            {
                businessPurposeResponseModel.setSuccess(false);
                BusinessError businessError= new BusinessError(businessPurposeResponseMap.getResponseCode(),null,businessPurposeResponseMap.getMessage(),MBC_Constants.NOTIFICATION,
                        MBC_Constants.TOP);
                businessPurposeResponseModel.setBusinessError(businessError);

            }
            businessPurposeResponseModel.setSuccess(true);

            if(businessPurposeResponseMap.getBusinessPurposeMapList() != null)
            {

                businessPurposeResponseModel.setBusinessPurposeList(convert(businessPurposeResponseMap.getBusinessPurposeMapList()));
            }

        }

        return (R) businessPurposeResponseModel;
    }

    List<BusinessPurpose> convert(List<BusinessPurposeMap> businessPurposeMapList)
    {
        List<BusinessPurpose> businessPurposeList=new ArrayList<>();
            BusinessPurpose businessPurpose= new BusinessPurpose();
            businessPurpose.setBusinessPurpose("Select Business Purpose");
            businessPurposeList.add(businessPurpose);

        for(BusinessPurposeMap businessPurposeMap : businessPurposeMapList)
        {
             businessPurpose = new BusinessPurpose();

            businessPurpose.setBusinessPurposeId(businessPurposeMap.getBusinessPurposeId());
            businessPurpose.setBusinessPurpose(businessPurposeMap.getBusinessPurpose());

            businessPurposeList.add(businessPurpose);
        }


        return businessPurposeList;
    }

}
