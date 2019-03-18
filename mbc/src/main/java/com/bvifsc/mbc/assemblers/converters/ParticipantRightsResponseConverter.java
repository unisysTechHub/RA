package com.bvifsc.mbc.assemblers.converters;

import com.bvifsc.core.common.Converter;
import com.bvifsc.core.models.response.BaseResponse;
import com.bvifsc.core.models.response.BusinessError;
import com.bvifsc.mbc.Common.MBC_Constants;
import com.bvifsc.mbc.model.registerMBC.ParticipantRights;
import com.bvifsc.mbc.model.registerMBC.ParticipantRightsResponseModel;
import com.bvifsc.mbc.network.response.registerMBC.ParticipantRightsMap;
import com.bvifsc.mbc.network.response.registerMBC.ParticipantRightsResponseMap;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ramesh on 07-01-2019.
 */
public class ParticipantRightsResponseConverter implements Converter {
    @Override
    public <R extends BaseResponse, T> R convert(T var1) {

        ParticipantRightsResponseMap participantRightsResponseMap=(ParticipantRightsResponseMap)var1;
        ParticipantRightsResponseModel participantRightsResponseModel= null;

        if(participantRightsResponseMap != null)
        {
            participantRightsResponseModel= new ParticipantRightsResponseModel(MBC_Constants.PARTICIPANT_RIGHTS,null);

            if(!participantRightsResponseMap.isSuccess())
            {
                participantRightsResponseModel.setSuccess(false);
                BusinessError businessError= new BusinessError(participantRightsResponseMap.getResponseCode(),null,participantRightsResponseMap.getMessage(),
                        MBC_Constants.NOTIFICATION,MBC_Constants.TOP);
                participantRightsResponseModel.setBusinessError(businessError);

            }
            else
                participantRightsResponseModel.setSuccess(true);

            if(participantRightsResponseMap.getParticipantRightsMap()!= null)
            {
                participantRightsResponseModel.setParticipantRightsList(convert(participantRightsResponseMap.getParticipantRightsMap()));


            }


        }
                return (R) participantRightsResponseModel;
    }

    List<ParticipantRights> convert(List<ParticipantRightsMap> participantRightsMapList)
    {
        List<ParticipantRights> participantRightsList=new ArrayList<>();
        ParticipantRights participantRights = new ParticipantRights();
        participantRights.setParticipantRights("Select ParticipantRights");
        participantRightsList.add(participantRights);
        for(ParticipantRightsMap participantRightsMap: participantRightsMapList)
        {
                participantRights = new ParticipantRights();
               if(participantRightsMap.getActiveFlag().equalsIgnoreCase("Y")) {

                   participantRights.setParticipantRightsId(participantRightsMap.getParticipantRightsId());
                   participantRights.setParticipantRights(participantRightsMap.getParticipantRights());

                   participantRightsList.add(participantRights);

               }


        }

        return participantRightsList;
    }
}
