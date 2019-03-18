package com.bvifsc.mbc.network.response.registerMBC;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Ramesh on 07-01-2019.
 */
public class CountryDetailsMap {
    @SerializedName("countryId")
    int countryId;

    @SerializedName("countryCode")
    String countryCode;

    @SerializedName("mbcCountryOfOperation")
    String mbcCountryOfOperation;

    @SerializedName("alpha3Code")
    String alpha3Code;


    public int getCountryId() {
        return countryId;
    }

    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getMbcCountryOfOperation() {
        return mbcCountryOfOperation;
    }

    public void setMbcCountryOfOperation(String mbcCountryOfOperation) {
        this.mbcCountryOfOperation = mbcCountryOfOperation;
    }

    public String getAlpha3Code() {
        return alpha3Code;
    }

    public void setAlpha3Code(String alpha3Code) {
        this.alpha3Code = alpha3Code;
    }
}

