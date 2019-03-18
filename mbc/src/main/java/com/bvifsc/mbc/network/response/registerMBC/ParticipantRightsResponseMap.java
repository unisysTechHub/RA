package com.bvifsc.mbc.network.response.registerMBC;

import com.bvifsc.mbc.network.response.ResponseInfo;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Ramesh on 07-01-2019.
 */
public class ParticipantRightsResponseMap extends ResponseInfo {
    @SerializedName("responsePayload")
    List<ParticipantRightsMap> participantRightsMap;

    public List<ParticipantRightsMap> getParticipantRightsMap() {
        return participantRightsMap;
    }

    public void setParticipantRightsMap(List<ParticipantRightsMap> participantRightsMap) {
        this.participantRightsMap = participantRightsMap;
    }
}
