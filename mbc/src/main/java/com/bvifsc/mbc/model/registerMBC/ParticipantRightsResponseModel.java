package com.bvifsc.mbc.model.registerMBC;

import com.bvifsc.core.models.response.BaseResponse;

import java.util.List;

/**
 * Created by Ramesh on 07-01-2019.
 */
public class ParticipantRightsResponseModel extends BaseResponse{

    List<ParticipantRights> participantRightsList;

    public ParticipantRightsResponseModel(String pageType, String header) {
        super(pageType, header);
    }

    public List<ParticipantRights> getParticipantRightsList() {
        return participantRightsList;
    }

    public void setParticipantRightsList(List<ParticipantRights> participantRightsList) {
        this.participantRightsList = participantRightsList;
    }
}
