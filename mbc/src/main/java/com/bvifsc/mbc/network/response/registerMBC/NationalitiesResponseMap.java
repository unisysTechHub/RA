package com.bvifsc.mbc.network.response.registerMBC;

import com.bvifsc.mbc.network.response.ResponseInfo;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Ramesh on 07-01-2019.
 */
public class NationalitiesResponseMap extends ResponseInfo{

    @SerializedName("responsePayload")
    List<NationalityMap> nationalitiesMap;

    public List<NationalityMap> getNationalitiesMap() {
        return nationalitiesMap;
    }

    public void setNationalitiesMap(List<NationalityMap> nationalitiesMap) {
        this.nationalitiesMap = nationalitiesMap;
    }
}
