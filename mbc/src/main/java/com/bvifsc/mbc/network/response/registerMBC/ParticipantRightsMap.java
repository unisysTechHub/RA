package com.bvifsc.mbc.network.response.registerMBC;

import com.bvifsc.mbc.network.response.ResponseInfo;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Ramesh on 07-01-2019.
 */
public class ParticipantRightsMap {
    @SerializedName("participantRightsId")
    int participantRightsId;

    @SerializedName("participantRights")
    String participantRights;

    @SerializedName("activeFlag")
    String activeFlag;

    public int getParticipantRightsId() {
        return participantRightsId;
    }

    public void setParticipantRightsId(int participantRightsId) {
        this.participantRightsId = participantRightsId;
    }

    public String getParticipantRights() {
        return participantRights;
    }

    public void setParticipantRights(String participantRights) {
        this.participantRights = participantRights;
    }

    public String getActiveFlag() {
        return activeFlag;
    }

    public void setActiveFlag(String activeFlag) {
        this.activeFlag = activeFlag;
    }
}
