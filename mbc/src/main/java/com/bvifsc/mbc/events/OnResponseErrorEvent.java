package com.bvifsc.mbc.events;

import android.support.v4.app.Fragment;

/**
 * Created by Ramesh on 30-11-2018.
 */
public class OnResponseErrorEvent {
     String pageType;
     String errorMessage;
     Fragment ErrorFragment;
     String messageStyle;

    public String getPageType() {
        return pageType;
    }

    public void setPageType(String pageType) {
        this.pageType = pageType;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public Fragment getErrorFragment() {
        return ErrorFragment;
    }

    public void setErrorFragment(Fragment errorFragment) {
        ErrorFragment = errorFragment;
    }

    public String getMessageStyle() {
        return messageStyle;
    }

    public void setMessageStyle(String messageStyle) {
        this.messageStyle = messageStyle;
    }
}
