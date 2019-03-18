package com.bvifsc.mbc.network.response.signIn;

import com.bvifsc.mbc.network.response.ResponseInfo;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Ramesh on 27-11-2018.
 */
public class SignInResponseMap extends ResponseInfo{

    @SerializedName("responsePayload")
    UserInfo signedInUserMap;

    public UserInfo getSignedInUserMap() {
        return signedInUserMap;
    }

    public void setSignedInUserMap(UserInfo signedInUserMap) {
        this.signedInUserMap = signedInUserMap;
    }
}
