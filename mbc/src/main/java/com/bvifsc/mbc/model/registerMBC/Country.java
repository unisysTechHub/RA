package com.bvifsc.mbc.model.registerMBC;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Ramesh on 07-01-2019.
 */
public class Country implements Parcelable{


    int countryId;


    String countryCode;


    String mbcCountryOfOperation;


    String alpha3Code;

    public Country()
    {}

    protected Country(Parcel in) {
        countryId = in.readInt();
        countryCode = in.readString();
        mbcCountryOfOperation = in.readString();
        alpha3Code = in.readString();
    }

    public static final Creator<Country> CREATOR = new Creator<Country>() {
        @Override
        public Country createFromParcel(Parcel in) {
            return new Country(in);
        }

        @Override
        public Country[] newArray(int size) {
            return new Country[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(countryId);
        dest.writeString(countryCode);
        dest.writeString(mbcCountryOfOperation);
        dest.writeString(alpha3Code);
    }
}
