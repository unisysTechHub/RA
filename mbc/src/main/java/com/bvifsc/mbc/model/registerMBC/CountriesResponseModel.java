package com.bvifsc.mbc.model.registerMBC;

import android.os.Parcelable;

import com.bvifsc.core.models.response.BaseResponse;

import java.util.List;

/**
 * Created by Ramesh on 07-01-2019.
 */
public class CountriesResponseModel extends BaseResponse  {
    List<Country> countries;
    public CountriesResponseModel(String pageType, String header) {
        super(pageType, header);
    }

    public List<Country> getCountries() {
        return countries;
    }

    public void setCountries(List<Country> countries) {
        this.countries = countries;
    }
}
