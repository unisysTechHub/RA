package com.bvifsc.mbc.model.signUp;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by Ramesh on 10-12-2018.
 */
public class SecurityQuestionsModel implements Parcelable {

    List<String> securityQuestionList;
   public SecurityQuestionsModel()
    {}

    public SecurityQuestionsModel(Parcel in) {
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<SecurityQuestionsModel> CREATOR = new Creator<SecurityQuestionsModel>() {
        @Override
        public SecurityQuestionsModel createFromParcel(Parcel in) {
            return new SecurityQuestionsModel(in);
        }

        @Override
        public SecurityQuestionsModel[] newArray(int size) {
            return new SecurityQuestionsModel[size];
        }
    };

    public List<String> getSecurityQuestionList() {
        return securityQuestionList;
    }

    public void setSecurityQuestionList(List<String> securityQuestionList) {
        this.securityQuestionList = securityQuestionList;
    }

    public static Creator<SecurityQuestionsModel> getCREATOR() {
        return CREATOR;
    }
}
