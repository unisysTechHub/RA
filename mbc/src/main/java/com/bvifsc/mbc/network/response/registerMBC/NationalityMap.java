package com.bvifsc.mbc.network.response.registerMBC;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Ramesh on 07-01-2019.
 */
public class NationalityMap {

    @SerializedName("nationalityId")
    int nationalityId;

    @SerializedName("alpha2Code")
    String alpha2Code;

    @SerializedName("nationality")
    String nationality;

    @SerializedName("activeFlag")
    String activeFlag;

    @SerializedName("alpha3Code")
    String alpha3Code;

    public int getNationalityId() {
        return nationalityId;
    }

    public void setNationalityId(int nationalityId) {
        this.nationalityId = nationalityId;
    }

    public String getAlpha2Code() {
        return alpha2Code;
    }

    public void setAlpha2Code(String alpha2Code) {
        this.alpha2Code = alpha2Code;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getActiveFlag() {
        return activeFlag;
    }

    public void setActiveFlag(String activeFlag) {
        this.activeFlag = activeFlag;
    }

    public String getAlpha3Code() {
        return alpha3Code;
    }

    public void setAlpha3Code(String alpha3Code) {
        this.alpha3Code = alpha3Code;
    }
}
