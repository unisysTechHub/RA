package com.bvifsc.mbc.events;

import com.bvifsc.core.models.response.BaseResponse;

/**
 * Created by Ramesh on 28-11-2018.
 */
public class ServerResponseEvent {
    BaseResponse responseInfo;

    public BaseResponse getRespponseInfo() {
        return responseInfo;
    }

    public void setRespponseInfo(BaseResponse responseInfo) {
        this.responseInfo = responseInfo;
    }
}
