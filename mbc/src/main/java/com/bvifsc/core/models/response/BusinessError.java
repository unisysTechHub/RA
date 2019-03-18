package com.bvifsc.core.models.response;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Ramesh on 26-11-2018.
 */
public class BusinessError implements Parcelable{

    private static final String SUCCESS_TYPES = "Success";
    private static final String EXCLUDE_CODES = "80008";
    public static final String FIELD_ERRORS_TYPE = "FieldErrors";
    public static final String EMPTY_STRING = "";
    private String responseCode;
    private String errorCode;
    private final String errorMessage;
    private final String userMessage;
    private final String type;
    private final String messageStyle;
    private String topAlertImageUrl;
    private Action action;
    private boolean hideNotificationLogo;
    private boolean hideXButton;
    private boolean discardBottomNotification;
    private int topAlertTime;
    private String topMessage;
    public BusinessError(String responseCode, String errorMessage, String userMessage, String type, String messageStyle) {

        this.responseCode = responseCode;
        this.errorMessage = errorMessage;
        this.userMessage = userMessage;
        this.type = type;
        this.messageStyle = messageStyle;
    }

    protected BusinessError(Parcel in) {
        errorCode = in.readString();
        errorMessage = in.readString();
        userMessage = in.readString();
        type = in.readString();
        messageStyle = in.readString();
        topAlertImageUrl = in.readString();
        hideNotificationLogo = in.readByte() != 0;
        hideXButton = in.readByte() != 0;
        discardBottomNotification = in.readByte() != 0;
        topAlertTime = in.readInt();
        topMessage = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(errorCode);
        dest.writeString(errorMessage);
        dest.writeString(userMessage);
        dest.writeString(type);
        dest.writeString(messageStyle);
        dest.writeString(topAlertImageUrl);
        dest.writeByte((byte) (hideNotificationLogo ? 1 : 0));
        dest.writeByte((byte) (hideXButton ? 1 : 0));
        dest.writeByte((byte) (discardBottomNotification ? 1 : 0));
        dest.writeInt(topAlertTime);
        dest.writeString(topMessage);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<BusinessError> CREATOR = new Creator<BusinessError>() {
        @Override
        public BusinessError createFromParcel(Parcel in) {
            return new BusinessError(in);
        }

        @Override
        public BusinessError[] newArray(int size) {
            return new BusinessError[size];
        }
    };

    public static String getSuccessTypes() {
        return SUCCESS_TYPES;
    }

    public static String getExcludeCodes() {
        return EXCLUDE_CODES;
    }

    public static String getFieldErrorsType() {
        return FIELD_ERRORS_TYPE;
    }

    public static String getEmptyString() {
        return EMPTY_STRING;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public String getUserMessage() {
        return userMessage;
    }

    public String getType() {
        return type;
    }

    public String getMessageStyle() {
        return messageStyle;
    }

    public String getTopAlertImageUrl() {
        return topAlertImageUrl;
    }

    public void setTopAlertImageUrl(String topAlertImageUrl) {
        this.topAlertImageUrl = topAlertImageUrl;
    }

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    public boolean isHideNotificationLogo() {
        return hideNotificationLogo;
    }

    public void setHideNotificationLogo(boolean hideNotificationLogo) {
        this.hideNotificationLogo = hideNotificationLogo;
    }

    public boolean isHideXButton() {
        return hideXButton;
    }

    public void setHideXButton(boolean hideXButton) {
        this.hideXButton = hideXButton;
    }

    public boolean isDiscardBottomNotification() {
        return discardBottomNotification;
    }

    public void setDiscardBottomNotification(boolean discardBottomNotification) {
        this.discardBottomNotification = discardBottomNotification;
    }

    public int getTopAlertTime() {
        return topAlertTime;
    }

    public void setTopAlertTime(int topAlertTime) {
        this.topAlertTime = topAlertTime;
    }

    public String getTopMessage() {
        return topMessage;
    }

    public void setTopMessage(String topMessage) {
        this.topMessage = topMessage;
    }



    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }
}
