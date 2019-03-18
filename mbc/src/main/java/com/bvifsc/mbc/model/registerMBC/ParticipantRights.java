package com.bvifsc.mbc.model.registerMBC;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Ramesh on 07-01-2019.
 */
public class ParticipantRights implements Parcelable{

    int participantRightsId;


    String participantRights;

    public ParticipantRights()
    {}
    protected ParticipantRights(Parcel in) {
        participantRightsId = in.readInt();
        participantRights = in.readString();
    }

    public static final Creator<ParticipantRights> CREATOR = new Creator<ParticipantRights>() {
        @Override
        public ParticipantRights createFromParcel(Parcel in) {
            return new ParticipantRights(in);
        }

        @Override
        public ParticipantRights[] newArray(int size) {
            return new ParticipantRights[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(participantRightsId);
        dest.writeString(participantRights);
    }
}
