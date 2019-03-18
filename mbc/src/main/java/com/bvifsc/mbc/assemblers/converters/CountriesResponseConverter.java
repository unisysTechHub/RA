package com.bvifsc.mbc.assemblers.converters;

import android.util.Log;

import com.bvifsc.core.common.Converter;
import com.bvifsc.core.models.response.BaseResponse;
import com.bvifsc.core.models.response.BusinessError;
import com.bvifsc.mbc.Common.MBC_Constants;
import com.bvifsc.mbc.model.registerMBC.CountriesResponseModel;
import com.bvifsc.mbc.model.registerMBC.Country;
import com.bvifsc.mbc.network.response.registerMBC.CountriesResponseMap;
import com.bvifsc.mbc.network.response.registerMBC.CountryDetailsMap;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ramesh on 07-01-2019.
 */
public class CountriesResponseConverter implements Converter {
    @Override
    public <R extends BaseResponse, T> R convert(T var1) {



        CountriesResponseMap countriesResponseMap=(CountriesResponseMap) var1;

        CountriesResponseModel countriesResponseModel= null;

        if(countriesResponseMap!= null)
        {
            countriesResponseModel = new CountriesResponseModel(MBC_Constants.COUNTRIES,null);

            if(!countriesResponseMap.isSuccess())
            {
                countriesResponseModel.setSuccess(false);
                BusinessError    businessError= new BusinessError(countriesResponseMap.getResponseCode(),null,countriesResponseMap.getMessage(),
                        MBC_Constants.NOTIFICATION,MBC_Constants.TOP);
                countriesResponseModel.setBusinessError(businessError);

            }
            else
                countriesResponseModel.setSuccess(true);

            if(countriesResponseMap.getCountriesMap() != null)
            {
                countriesResponseModel.setCountries(convert(countriesResponseMap.getCountriesMap()));

            }
        }

        return (R) countriesResponseModel;
    }

    List<Country> convert(List<CountryDetailsMap> countryDetailsMapList)
    {
        List<Country> countryList=new ArrayList<>();
        Country country= new Country();
        country.setMbcCountryOfOperation("Select Country of Operation");
        countryList.add(country);
        for(CountryDetailsMap countryDetailsMap : countryDetailsMapList)
        {
             country = new Country();

            country.setCountryId(countryDetailsMap.getCountryId());
            country.setAlpha3Code(countryDetailsMap.getAlpha3Code());
            country.setCountryCode(countryDetailsMap.getCountryCode());
            country.setMbcCountryOfOperation(countryDetailsMap.getMbcCountryOfOperation());
            countryList.add(country);


        }

        return countryList;
    }
}
