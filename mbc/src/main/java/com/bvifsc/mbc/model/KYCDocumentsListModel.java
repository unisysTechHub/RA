package com.bvifsc.mbc.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.bvifsc.mbc.datamodel.KYC;

import java.util.List;

/**
 * Created by Ramesh on 17-12-2018.
 */
public class KYCDocumentsListModel implements Parcelable{

    List<KYCDocumentTypeModel> kycDocTypeList;

    public KYCDocumentsListModel()
    {}


    protected KYCDocumentsListModel(Parcel in) {
    }

    public static final Creator<KYCDocumentsListModel> CREATOR = new Creator<KYCDocumentsListModel>() {
        @Override
        public KYCDocumentsListModel createFromParcel(Parcel in) {
            return new KYCDocumentsListModel(in);
        }

        @Override
        public KYCDocumentsListModel[] newArray(int size) {
            return new KYCDocumentsListModel[size];
        }
    };



    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }

    public List<KYCDocumentTypeModel> getKycDocTypeList() {
        return kycDocTypeList;
    }

    public void setKycDocTypeList(List<KYCDocumentTypeModel> kycDocTypeList) {
        this.kycDocTypeList = kycDocTypeList;
    }


}
