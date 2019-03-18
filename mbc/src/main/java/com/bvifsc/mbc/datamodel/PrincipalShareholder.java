package com.bvifsc.mbc.datamodel;

import android.os.Parcel;
import android.os.Parcelable;

import com.bvifsc.mbc.model.registerMBC.Nationality;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Ramesh on 04-01-2019.
 */
public class PrincipalShareholder implements Parcelable{


    int principalShareholderId;

    @SerializedName("professionalDesignation")
    String professionalDesignation;

    @SerializedName("firstName")
    String firstName;

    @SerializedName("middleName")
    String middleName;

    @SerializedName("lastName")
    String lastName;

    @SerializedName("nationality")
    Nationality nationality;

    @SerializedName("isFirstNameOnlyCheck")
    boolean isFirstNameOnlyCheck;

    @SerializedName("isFirstNameOnly")
    String isFirstNameOnly;

    @SerializedName("totalShares")
    int noOfShares=1;

    public PrincipalShareholder(){}

    protected PrincipalShareholder(Parcel in) {
        principalShareholderId = in.readInt();
        professionalDesignation = in.readString();
        firstName = in.readString();
        middleName = in.readString();
        lastName = in.readString();
        nationality = in.readParcelable(Nationality.class.getClassLoader());
        isFirstNameOnlyCheck = in.readByte() != 0;
        isFirstNameOnly = in.readString();
        noOfShares = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(principalShareholderId);
        dest.writeString(professionalDesignation);
        dest.writeString(firstName);
        dest.writeString(middleName);
        dest.writeString(lastName);
        dest.writeParcelable(nationality, flags);
        dest.writeByte((byte) (isFirstNameOnlyCheck ? 1 : 0));
        dest.writeString(isFirstNameOnly);
        dest.writeInt(noOfShares);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<PrincipalShareholder> CREATOR = new Creator<PrincipalShareholder>() {
        @Override
        public PrincipalShareholder createFromParcel(Parcel in) {
            return new PrincipalShareholder(in);
        }

        @Override
        public PrincipalShareholder[] newArray(int size) {
            return new PrincipalShareholder[size];
        }
    };

    public int getPrincipalShareholderId() {
        return principalShareholderId;
    }

    public void setPrincipalShareholderId(int principalShareholderId) {
        this.principalShareholderId = principalShareholderId;
    }

    public String getProfessionalDesignation() {
        return professionalDesignation;
    }

    public void setProfessionalDesignation(String professionalDesignation) {
        this.professionalDesignation = professionalDesignation;
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

    public int getNoOfShares() {
        return noOfShares;
    }

    public void setNoOfShares(int noOfShares) {
        this.noOfShares = noOfShares;
    }
}
