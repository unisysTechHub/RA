package com.bvifsc.core.network.request;

import com.bvifsc.core.models.response.BaseResponse;
import com.bvifsc.core.network.request.Resource;
import com.bvifsc.mbc.network.response.ResponseInfo;

/**
 * Created by Ramesh on 28-11-2018.
 */
public interface RequestExecutor {
    void executeRequest(Resource resource);
    <R extends ResponseInfo>  void executeRequest(Class<R> input, Resource resource );
    <R extends BaseResponse> void notify(R response);
}
