package com.bvifsc.mbc.network.request;

import android.graphics.Bitmap;
import android.util.Base64;

import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Ramesh on 27-11-2018.
 */
public class BaseRequest {
    public JSONObject requestParams;


 public <T> void addParams (String key, T value) throws JSONException {
        if(requestParams == null)
            requestParams = new JSONObject();
        requestParams.put(key,value);

    }
    public JSONObject getRequestParams() {
        return requestParams;
    }



    public String getStringImage(Bitmap bmp){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;

    }

    public void setRequestParams(JSONObject requestParams) {
        this.requestParams = requestParams;
    }
}
