package com.bvifsc.mbc.model.registerMBC;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Ramesh on 07-01-2019.
 */
public class Nationality implements Parcelable{
    int nationalityId;


    String alpha2Code;


    String nationality;


    String activeFlag;


    String alpha3Code;

    public Nationality()
    {}

    protected Nationality(Parcel in) {
        nationalityId = in.readInt();
        alpha2Code = in.readString();
        nationality = in.readString();
        activeFlag = in.readString();
        alpha3Code = in.readString();
    }

    public static final Creator<Nationality> CREATOR = new Creator<Nationality>() {
        @Override
        public Nationality createFromParcel(Parcel in) {
            return new Nationality(in);
        }

        @Override
        public Nationality[] newArray(int size) {
            return new Nationality[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(nationalityId);
        dest.writeString(alpha2Code);
        dest.writeString(nationality);
        dest.writeString(activeFlag);
        dest.writeString(alpha3Code);
    }
}
