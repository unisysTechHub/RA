package com.bvifsc.mbc.assemblers;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import com.bvifsc.core.models.response.BusinessError;
import com.bvifsc.core.models.response.PageResponseModel;
import com.bvifsc.mbc.model.UserAuthInfo;

/**
 * Created by Ramesh on 27-11-2018.
 */
public class CommonModelConverter {

    public static Bitmap converter(String imageEncodeSring)
    {

        byte[] byteArray = Base64.decode(imageEncodeSring, Base64.DEFAULT);
        Bitmap profileImageBitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
        return  profileImageBitmap;
    }

}
