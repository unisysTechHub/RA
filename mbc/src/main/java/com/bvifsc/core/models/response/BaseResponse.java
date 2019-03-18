package com.bvifsc.core.models.response;

import android.app.Notification;
import android.os.Parcel;
import android.os.Parcelable;

import com.bvifsc.core.events.ResponseHandlingEvent;
import com.bvifsc.mbc.network.response.ResponseInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ramesh on 26-11-2018.
 */
public class BaseResponse implements Parcelable {

    private boolean isSuccess;
    private String message;
    private String pageType;
    private String popBackPageType;
    private String header;
    protected BusinessError businessError;
    protected BasePageModel pageModel;
    private Parcelable extraInfo;
    private String previousPageType;
    private List<BusinessError> internalErrors = new ArrayList();
    private String parentPage;
    private Notification notification;
    public BaseResponse(String pageType, String header)
    {
        this.pageType=pageType;
        this.header=header;
    }

    protected BaseResponse(Parcel in) {
        pageType = in.readString();
        popBackPageType = in.readString();
        header = in.readString();
        businessError = in.readParcelable(BusinessError.class.getClassLoader());
        pageModel = in.readParcelable(BasePageModel.class.getClassLoader());
        previousPageType = in.readString();
        internalErrors = in.createTypedArrayList(BusinessError.CREATOR);
        parentPage = in.readString();
        notification = in.readParcelable(Notification.class.getClassLoader());
    }

    public static final Creator<BaseResponse> CREATOR = new Creator<BaseResponse>() {
        @Override
        public BaseResponse createFromParcel(Parcel in) {
            return new BaseResponse(in);
        }

        @Override
        public BaseResponse[] newArray(int size) {
            return new BaseResponse[size];
        }
    };

    public boolean isSuccess() {
        return isSuccess;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public String getPageType() {
        return pageType;
    }

    public void setPageType(String pageType) {
        this.pageType = pageType;
    }

    public String getPopBackPageType() {
        return popBackPageType;
    }

    public void setPopBackPageType(String popBackPageType) {
        this.popBackPageType = popBackPageType;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public BusinessError getBusinessError() {
        return businessError;
    }

    public void setBusinessError(BusinessError businessError) {
        this.businessError = businessError;
    }

    public BasePageModel getPageModel() {
        return pageModel;
    }

    public void setPageModel(BasePageModel pageModel) {
        this.pageModel = pageModel;
    }

    public Parcelable getExtraInfo() {
        return extraInfo;
    }

    public void setExtraInfo(Parcelable extraInfo) {
        this.extraInfo = extraInfo;
    }

    public ResponseHandlingEvent buildResponseHandlingEvent() {
        return null;
    }

    public String getPreviousPageType() {
        return previousPageType;
    }

    public void setPreviousPageType(String previousPageType) {
        this.previousPageType = previousPageType;
    }

    public List<BusinessError> getInternalErrors() {
        return internalErrors;
    }

    public void setInternalErrors(List<BusinessError> internalErrors) {
        this.internalErrors = internalErrors;
    }

    public String getParentPage() {
        return parentPage;
    }

    public void setParentPage(String parentPage) {
        this.parentPage = parentPage;
    }

    public Notification getNotification() {
        return notification;
    }

    public void setNotification(Notification notification) {
        this.notification = notification;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(pageType);
        dest.writeString(popBackPageType);
        dest.writeString(header);
        dest.writeParcelable(businessError, flags);
        dest.writeParcelable(pageModel, flags);
        dest.writeString(previousPageType);
        dest.writeTypedList(internalErrors);
        dest.writeString(parentPage);
        dest.writeParcelable(notification, flags);
    }
}
