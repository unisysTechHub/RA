package com.bvifsc.mbc.Common;

/**
 * Created by Ramesh on 27-11-2018.
 */
public interface Callback<T> {
    void notify(T response);
}
