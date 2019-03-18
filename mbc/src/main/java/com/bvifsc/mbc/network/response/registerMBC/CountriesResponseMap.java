package com.bvifsc.mbc.network.response.registerMBC;

import com.bvifsc.mbc.network.response.ResponseInfo;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Ramesh on 07-01-2019.
 */
public class CountriesResponseMap extends ResponseInfo {
    @SerializedName("responsePayload")
    List<CountryDetailsMap> countriesMap;

    public List<CountryDetailsMap> getCountriesMap() {
        return countriesMap;
    }

    public void setCountriesMap(List<CountryDetailsMap> countriesMap) {
        this.countriesMap = countriesMap;
    }
}
