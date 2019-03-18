package com.bvifsc.mbc.model.registerMBC;

import com.bvifsc.core.models.response.BaseResponse;

import java.util.List;

/**
 * Created by Ramesh on 07-01-2019.
 */
public class BusinessPurposeResponseModel extends BaseResponse{

    List<BusinessPurpose> businessPurposeList;

    public BusinessPurposeResponseModel(String pageType, String header) {
        super(pageType, header);
    }

    public List<BusinessPurpose> getBusinessPurposeList() {
        return businessPurposeList;
    }

    public void setBusinessPurposeList(List<BusinessPurpose> businessPurposeList) {
        this.businessPurposeList = businessPurposeList;
    }
}
