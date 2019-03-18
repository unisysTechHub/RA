package com.bvifsc.mbc.network.response.signUp;

import com.bvifsc.mbc.network.response.ResponseInfo;
import com.bvifsc.mbc.network.response.signIn.UserInfo;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Ramesh on 12-12-2018.
 */
public class SignUpResponseMap extends ResponseInfo {


    @SerializedName("responsePayload")
    UserInfo userInfo;

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }
}
