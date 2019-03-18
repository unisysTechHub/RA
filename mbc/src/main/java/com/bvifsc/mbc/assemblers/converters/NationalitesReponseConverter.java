package com.bvifsc.mbc.assemblers.converters;

import com.bvifsc.core.common.Converter;
import com.bvifsc.core.models.response.BaseResponse;
import com.bvifsc.core.models.response.BusinessError;
import com.bvifsc.mbc.Common.MBC_Constants;
import com.bvifsc.mbc.model.registerMBC.NationalitiesResponseModel;
import com.bvifsc.mbc.model.registerMBC.Nationality;
import com.bvifsc.mbc.network.response.registerMBC.NationalitiesResponseMap;
import com.bvifsc.mbc.network.response.registerMBC.NationalityMap;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ramesh on 07-01-2019.
 */
public class NationalitesReponseConverter implements Converter{

    @Override
    public <R extends BaseResponse, T> R convert(T responseMap) {

        NationalitiesResponseMap nationalitiesResponseMap=(NationalitiesResponseMap) responseMap;
        NationalitiesResponseModel nationalitiesResponseModel=null;
        if(nationalitiesResponseMap!= null)
        {
             nationalitiesResponseModel= new NationalitiesResponseModel(MBC_Constants.NATIONALITIES, null);

             if(!nationalitiesResponseMap.isSuccess())
             {
                 nationalitiesResponseModel.setSuccess(false);
                 BusinessError businessError= new BusinessError(nationalitiesResponseMap.getResponseCode(),null,nationalitiesResponseMap.getMessage(),"notification","top");
                 nationalitiesResponseModel.setBusinessError(businessError);

             }

             else
                 nationalitiesResponseModel.setSuccess(true);

            if(nationalitiesResponseMap.getNationalitiesMap()!= null)
            {
                nationalitiesResponseModel.setNationalities(convert(nationalitiesResponseMap.getNationalitiesMap()));

            }

        }
        return (R) nationalitiesResponseModel;

    }

    List<Nationality> convert(List<NationalityMap> nationalityMapList)
    {   List<Nationality> nationalities=new ArrayList<>();
            Nationality nationality= new Nationality();
            nationality.setNationality("Select Nationality");
            nationalities.add(nationality);
        for(NationalityMap nationalityMap : nationalityMapList)
        {
             nationality= new Nationality();
            nationality.setNationalityId(nationalityMap.getNationalityId());
            nationality.setActiveFlag(nationalityMap.getActiveFlag());
            nationality.setNationality(nationalityMap.getNationality());
            nationality.setAlpha3Code(nationalityMap.getAlpha3Code());
            nationality.setAlpha2Code(nationalityMap.getAlpha2Code());

            nationalities.add(nationality);

        }

        return nationalities;

    }
}
