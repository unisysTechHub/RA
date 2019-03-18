package com.bvifsc.mbc.model.registerMBC;

import com.bvifsc.core.models.response.BaseResponse;

import java.util.List;

/**
 * Created by Ramesh on 07-01-2019.
 */
public class NationalitiesResponseModel extends BaseResponse {

    List<Nationality> nationalities;

    public NationalitiesResponseModel(String pageType, String header) {
        super(pageType, header);
    }

    public List<Nationality> getNationalities() {
        return nationalities;
    }

    public void setNationalities(List<Nationality> nationalities) {
        this.nationalities = nationalities;
    }
}
