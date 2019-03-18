package com.bvifsc.core.common;

import com.bvifsc.core.models.response.BaseResponse;

/**
 * Created by Ramesh on 26-11-2018.
 */
public interface Converter {
    <R extends BaseResponse,T> R convert(T var1);

}
