package com.bvifsc.mbc.Common;

import android.text.TextUtils;

/**
 * Created by Ramesh on 11-12-2018.
 */
public class CommonUtils {
    public static boolean checkNullOrEmptyString(String msg) {
        return msg == null || TextUtils.isEmpty(msg.trim());
    }
}
