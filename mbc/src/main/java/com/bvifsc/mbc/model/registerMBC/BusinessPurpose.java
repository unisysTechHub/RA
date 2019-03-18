package com.bvifsc.mbc.model.registerMBC;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Ramesh on 07-01-2019.
 */
public class BusinessPurpose implements Parcelable{

    int businessPurposeId;

    String businessPurpose;

    public BusinessPurpose(){}

    protected BusinessPurpose(Parcel in) {
        businessPurposeId = in.readInt();
        businessPurpose = in.readString();
    }

    public static final Creator<BusinessPurpose> CREATOR = new Creator<BusinessPurpose>() {
        @Override
        public BusinessPurpose createFromParcel(Parcel in) {
            return new BusinessPurpose(in);
        }

        @Override
        public BusinessPurpose[] newArray(int size) {
            return new BusinessPurpose[size];
        }
    };

    public int getBusinessPurposeId() {
        return businessPurposeId;
    }

    public void setBusinessPurposeId(int businessPurposeId) {
        this.businessPurposeId = businessPurposeId;
    }

    public String getBusinessPurpose() {
        return businessPurpose;
    }

    public void setBusinessPurpose(String businessPurpose) {
        this.businessPurpose = businessPurpose;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(businessPurposeId);
        dest.writeString(businessPurpose);
    }
}
