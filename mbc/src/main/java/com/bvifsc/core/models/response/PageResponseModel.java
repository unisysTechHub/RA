package com.bvifsc.core.models.response;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Map;

/**
 * Created by Ramesh on 26-11-2018.
 */
public class PageResponseModel extends BasePageModel implements Parcelable {
    private BusinessError businessError;

    private Map<String, Action> buttonMap;

    //    private String title;
    private  String message;

    private String subTitle;

    private String appUrl;

    private String browserUrl;

    private String progressPercent;

    private String preOrderFlow;

    private String inventoryStatusMessageText;

    private String inventoryStatusMessageColor;
    public PageResponseModel(String pageType, String header) {
        super(pageType, header);
    }

    protected PageResponseModel(Parcel in) {
        super(in);
        businessError = in.readParcelable(BusinessError.class.getClassLoader());
        message = in.readString();
        subTitle = in.readString();
        appUrl = in.readString();
        browserUrl = in.readString();
        progressPercent = in.readString();
        preOrderFlow = in.readString();
        inventoryStatusMessageText = in.readString();
        inventoryStatusMessageColor = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeParcelable(businessError, flags);
        dest.writeString(message);
        dest.writeString(subTitle);
        dest.writeString(appUrl);
        dest.writeString(browserUrl);
        dest.writeString(progressPercent);
        dest.writeString(preOrderFlow);
        dest.writeString(inventoryStatusMessageText);
        dest.writeString(inventoryStatusMessageColor);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<PageResponseModel> CREATOR = new Creator<PageResponseModel>() {
        @Override
        public PageResponseModel createFromParcel(Parcel in) {
            return new PageResponseModel(in);
        }

        @Override
        public PageResponseModel[] newArray(int size) {
            return new PageResponseModel[size];
        }
    };

    public BusinessError getBusinessError() {
        return businessError;
    }

    public void setBusinessError(BusinessError businessError) {
        this.businessError = businessError;
    }

    public Map<String, Action> getButtonMap() {
        return buttonMap;
    }

    public void setButtonMap(Map<String, Action> buttonMap) {
        this.buttonMap = buttonMap;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public String getAppUrl() {
        return appUrl;
    }

    public void setAppUrl(String appUrl) {
        this.appUrl = appUrl;
    }

    public String getBrowserUrl() {
        return browserUrl;
    }

    public void setBrowserUrl(String browserUrl) {
        this.browserUrl = browserUrl;
    }

    public String getProgressPercent() {
        return progressPercent;
    }

    public void setProgressPercent(String progressPercent) {
        this.progressPercent = progressPercent;
    }

    public String getPreOrderFlow() {
        return preOrderFlow;
    }

    public void setPreOrderFlow(String preOrderFlow) {
        this.preOrderFlow = preOrderFlow;
    }

    public String getInventoryStatusMessageText() {
        return inventoryStatusMessageText;
    }

    public void setInventoryStatusMessageText(String inventoryStatusMessageText) {
        this.inventoryStatusMessageText = inventoryStatusMessageText;
    }

    public String getInventoryStatusMessageColor() {
        return inventoryStatusMessageColor;
    }

    public void setInventoryStatusMessageColor(String inventoryStatusMessageColor) {
        this.inventoryStatusMessageColor = inventoryStatusMessageColor;
    }
}
