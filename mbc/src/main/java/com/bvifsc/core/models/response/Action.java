package com.bvifsc.core.models.response;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Ramesh on 26-11-2018.
 */
public class Action implements Parcelable {
    public static final String DEFAULT_APPLICATION_CONTEXT = "bviFscMBC";
    private static final int ACTIVE_ACTION = 1;
    private static final int INACTIVE_ACTION = 0;
    protected String actionType;
    protected String pageType = "";
    protected String serviceType;
    protected boolean isService;
    protected String title;
    protected String appContext;
    protected boolean active = true;

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public boolean getIsService() {
        return isService;
    }

    public void setIsService(boolean isService) {
        this.isService = isService;
    }

    protected Parcelable extraInfo;
    protected String imageName;
    protected String message;
    protected boolean isFromSignIn;
    protected String mbcNumber;
    protected String deviceProdId;
    private boolean disableAction;
    protected String languageOption;
    private String browserUrl;
    public Action(String pageType, String title, String appContext,String actionType)
    {this.pageType=pageType;
        this.title=title;
        this.appContext=appContext;
        this.actionType=actionType;

    }
    protected Map<String, String> extraParams = new HashMap();

    protected Action(Parcel in) {
        actionType = in.readString();
        pageType = in.readString();
        serviceType=in.readString();
        title = in.readString();
        appContext = in.readString();
        active = in.readByte() != 0;
        imageName = in.readString();
        message = in.readString();
        isFromSignIn = in.readByte() != 0;
        mbcNumber = in.readString();
        deviceProdId = in.readString();
        disableAction = in.readByte() != 0;
        languageOption = in.readString();
        browserUrl = in.readString();
    }

    public static final Creator<Action> CREATOR = new Creator<Action>() {
        @Override
        public Action createFromParcel(Parcel in) {
            return new Action(in);
        }

        @Override
        public Action[] newArray(int size) {
            return new Action[size];
        }
    };

    public static String getDefaultApplicationContext() {
        return DEFAULT_APPLICATION_CONTEXT;
    }

    public static int getActiveAction() {
        return ACTIVE_ACTION;
    }

    public static int getInactiveAction() {
        return INACTIVE_ACTION;
    }

    public String getActionType() {
        return actionType;
    }

    public String getPageType() {
        return pageType;
    }

    public void setPageType(String pageType) {
        this.pageType = pageType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAppContext() {
        return appContext;
    }

    public void setAppContext(String appContext) {
        this.appContext = appContext;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }



    public Parcelable getExtraInfo() {
        return extraInfo;
    }

    public void setExtraInfo(Parcelable extraInfo) {
        this.extraInfo = extraInfo;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isFromSignIn() {
        return isFromSignIn;
    }

    public void setFromSignIn(boolean fromSignIn) {
        isFromSignIn = fromSignIn;
    }

    public String getMbcNumber() {
        return mbcNumber;
    }

    public void setMbcNumber(String mbcNumber) {
        this.mbcNumber = mbcNumber;
    }

    public String getDeviceProdId() {
        return deviceProdId;
    }

    public void setDeviceProdId(String deviceProdId) {
        this.deviceProdId = deviceProdId;
    }

    public boolean isDisableAction() {
        return disableAction;
    }

    public void setDisableAction(boolean disableAction) {
        this.disableAction = disableAction;
    }

    public String getLanguageOption() {
        return languageOption;
    }

    public void setLanguageOption(String languageOption) {
        this.languageOption = languageOption;
    }

    public Map<String, String> getExtraParams() {
        return extraParams;
    }

    public void setExtraParams(Map<String, String> extraParams) {
        this.extraParams = extraParams;
    }

    public void setActionType(String actionType) {
        this.actionType = actionType;
    }

    public String getBrowserUrl() {
        return browserUrl;
    }

    public void setBrowserUrl(String browserUrl) {
        this.browserUrl = browserUrl;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(actionType);
        dest.writeString(pageType);
        dest.writeString(serviceType);
        dest.writeString(title);
        dest.writeString(appContext);
        dest.writeByte((byte) (active ? 1 : 0));
        dest.writeString(imageName);
        dest.writeString(message);
        dest.writeByte((byte) (isFromSignIn ? 1 : 0));
        dest.writeString(mbcNumber);
        dest.writeString(deviceProdId);
        dest.writeByte((byte) (disableAction ? 1 : 0));
        dest.writeString(languageOption);
        dest.writeString(browserUrl);
    }
}
