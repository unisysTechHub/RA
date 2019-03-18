package com.bvifsc.mbc.datamodel;

import android.os.Parcel;
import android.os.Parcelable;

import com.bvifsc.mbc.model.registerMBC.Nationality;
import com.bvifsc.mbc.model.registerMBC.ParticipantRights;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Ramesh on 04-01-2019.
 */
public class ParticipantShareholder implements Parcelable{
    int participantShareholderId;
    @SerializedName("professionalDesignation")
    String designation;
    String firstName;
    String middleName;
    String lastName;
    Nationality nationality;
    ParticipantRights participantRights;
    boolean isFirstNameOnlyCheck;
    String isFirstNameOnly;

    public ParticipantShareholder(){}

    protected ParticipantShareholder(Parcel in) {
        participantShareholderId = in.readInt();
        designation = in.readString();
        firstName = in.readString();
        middleName = in.readString();
        lastName = in.readString();
        nationality = in.readParcelable(Nationality.class.getClassLoader());
        participantRights = in.readParcelable(ParticipantRights.class.getClassLoader());
        isFirstNameOnlyCheck = in.readByte() != 0;
        isFirstNameOnly = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(participantShareholderId);
        dest.writeString(designation);
        dest.writeString(firstName);
        dest.writeString(middleName);
        dest.writeString(lastName);
        dest.writeParcelable(nationality, flags);
        dest.writeParcelable(participantRights, flags);
        dest.writeByte((byte) (isFirstNameOnlyCheck ? 1 : 0));
        dest.writeString(isFirstNameOnly);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ParticipantShareholder> CREATOR = new Creator<ParticipantShareholder>() {
        @Override
        public ParticipantShareholder createFromParcel(Parcel in) {
            return new ParticipantShareholder(in);
        }

        @Override
        public ParticipantShareholder[] newArray(int size) {
            return new ParticipantShareholder[size];
        }
    };

    public int getParticipantShareholderId() {
        return participantShareholderId;
    }

    public void setParticipantShareholderId(int participantShareholderId) {
        this.participantShareholderId = participantShareholderId;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Nationality getNationality() {
        return nationality;
    }

    public void setNationality(Nationality nationality) {
        this.nationality = nationality;
    }

    public ParticipantRights getParticipantRights() {
        return participantRights;
    }

    public void setParticipantRights(ParticipantRights participantRights) {
        this.participantRights = participantRights;
    }

    public boolean isFirstNameOnlyCheck() {
        return isFirstNameOnlyCheck;
    }

    public void setFirstNameOnlyCheck(boolean firstNameOnlyCheck) {
        isFirstNameOnlyCheck = firstNameOnlyCheck;
    }

    public String getIsFirstNameOnly() {
        return isFirstNameOnly;
    }

    public void setIsFirstNameOnly(String isFirstNameOnly) {
        this.isFirstNameOnly = isFirstNameOnly;
    }
}
