package com.bvifsc.core.models.response;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Ramesh on 26-11-2018.
 */
public class BasePageModel implements Parcelable {
    private String pageType;
    private String screenHeading;
    private String presentationStyle;
    private String title;
    private String parentPageType;

    protected BasePageModel(Parcel in) {
        pageType = in.readString();
        screenHeading = in.readString();
        presentationStyle = in.readString();
        title = in.readString();
        parentPageType = in.readString();
    }
    public BasePageModel(String pageType, String screenHeading)
    {
        this.pageType=pageType;
        this.screenHeading=screenHeading;

    }

    public static final Creator<BasePageModel> CREATOR = new Creator<BasePageModel>() {
        @Override
        public BasePageModel createFromParcel(Parcel in) {
            return new BasePageModel(in);
        }

        @Override
        public BasePageModel[] newArray(int size) {
            return new BasePageModel[size];
        }
    };

    public String getPageType() {
        return pageType;
    }

    public void setPageType(String pageType) {
        this.pageType = pageType;
    }

    public String getScreenHeading() {
        return screenHeading;
    }

    public void setScreenHeading(String screenHeading) {
        this.screenHeading = screenHeading;
    }

    public String getPresentationStyle() {
        return presentationStyle;
    }

    public void setPresentationStyle(String presentationStyle) {
        this.presentationStyle = presentationStyle;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getParentPageType() {
        return parentPageType;
    }

    public void setParentPageType(String parentPageType) {
        this.parentPageType = parentPageType;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(pageType);
        dest.writeString(screenHeading);
        dest.writeString(presentationStyle);
        dest.writeString(title);
        dest.writeString(parentPageType);
    }
}
