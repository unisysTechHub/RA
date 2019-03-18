package com.bvifsc.core.network.request;

import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import android.provider.MediaStore;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.bvifsc.core.R;
import com.bvifsc.core.common.CORE_CONSTANTS;
import com.bvifsc.core.di.BVIRA_Application;
import com.bvifsc.mbc.Common.MBC_Constants;
import com.bvifsc.mbc.model.UserAuthInfo;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Ramesh on 28-11-2018.
 */
public class ResourceRequest<T> extends Request<T> {
    public static String TAG = ResourceServiceRequestor.class.getName() + CORE_CONSTANTS.SPACES;

    private final Gson gson = new Gson();
    private  Class<T> clazz;
    private final Map<String, String> headers;
    private final Resource  resource;
    private final Response.Listener<T> listener;


    public ResourceRequest(String url, Class<T > clazz, Map<String, String> headers,Resource resource,
                        Response.Listener<T> listener, Response.ErrorListener errorListener) {
        super(resource.actionType, url, errorListener);
        this.clazz = clazz;
        this.headers = headers;
        this.resource=resource;
        this.listener = listener;
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        Log.i(CORE_CONSTANTS.APP_TAG,"getHeaders");
        Map<String, String> headers = new HashMap<String, String>();

        if(resource.bodyRequest != null) {

                headers.put("Content-Type", "application/json");
        }
        else
           if(resource.formData != null)
           {

                  //headers = resource.formData.getHeaders();
                headers = new HashMap<>();
               headers.put("Content-Type", "multipart/form-data;boundary=ARCFormBoundaryvgrvnph55ewmi");



           }
           else
            headers.put("Content-Type", "application/json");


        if(resource.resourceToConsume.equals("kycDocumentTypes")
                || resource.resourceToConsume.equals("kycDocsDetails")

                || resource.actionType == Resource.Method.GET ){
            headers.put("authCode", UserAuthInfo.getInstance().getAuthCode());
            Log.d("@MBC", "authCode " + UserAuthInfo.getInstance().getAuthCode());

        }

        if(resource.resourceToConsume.equals("registerMBC") ||  resource.resourceToConsume.equals("amendCharter"))
        {
            headers.put("authCode", UserAuthInfo.getInstance().getAuthCode());
            headers.put("apiKey",UserAuthInfo.getInstance().getApiKey());

        }

        for(Map.Entry entry :headers.entrySet()) {
            Log.d("@MBC", entry.getKey() + " " + entry.getValue());
        }


        return (headers != null) ? headers : super.getHeaders();

    }

    @Override
    protected void deliverResponse(T response) {
        Log.i(CORE_CONSTANTS.APP_TAG,"delivery Response");
        listener.onResponse(response);
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        //Log.d(CORE_CONSTANTS.APP_TAG, resource.extraParamas.get("username") + " " + resource.extraParamas.get("password"));
        Log.d("@MBC", "getParams" + resource.extraParamas.keySet() + "" + resource.extraParamas.values());

        return  resource.extraParamas;
    }


    @Override
    public byte[] getBody() throws AuthFailureError {
        Log.d("@MBC", "getBody");
         String requestBody;

        if(resource.bodyRequest != null)
         {
             if (resource.bodyRequest.getRequestParams() != null) {
                 requestBody = resource.bodyRequest.getRequestParams().toString();
                 Log.d("@MBC","request Body params" + requestBody.toString());
                 return requestBody.getBytes();
             }

         }
         else
          if(resource.formData != null)
          {
              Log.d("@MBC", "form Data");
              return  resource.formData.getBytes();

          }

        Log.d("@MBC", "getBody  : null");

            return  null;


    }



    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {
        try {
            Log.d(CORE_CONSTANTS.APP_TAG,"network response" + response.data.toString());
            String json = new String(
                    response.data,
                    HttpHeaderParser.parseCharset(response.headers));
            Log.d(CORE_CONSTANTS.APP_TAG,json);
            return Response.success(
                    gson.fromJson(json, clazz),
                    HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        } catch (JsonSyntaxException e) {
            return Response.error(new ParseError(e));
        }
    }


}
