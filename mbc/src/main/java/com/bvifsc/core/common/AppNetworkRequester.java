package com.bvifsc.core.common;

import android.content.Context;
import android.graphics.Bitmap;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;


/**
 * Created by Ramesh on 16-11-2018.
 */
public class AppNetworkRequester {
    private static RequestQueue requestQueue=null;
    private static ImageLoader imageLoader;
    private static AppNetworkRequester appNetworkRequester;
    private Context mContext;

    private AppNetworkRequester(Context context) {
        mContext=context;
        requestQueue =getRequestQueue();
        imageLoader= new ImageLoader(requestQueue, new ImageLoader.ImageCache() {
            @Override
            public Bitmap getBitmap(String url) {
                return null;
            }

            @Override
            public void putBitmap(String url, Bitmap bitmap) {

            }
        });

    }

    public static AppNetworkRequester getInstance(Context context)
    {
        if(appNetworkRequester == null) {
            appNetworkRequester = new AppNetworkRequester(context);
        }


        return  appNetworkRequester;
    }

    public RequestQueue getRequestQueue() {
        if (requestQueue == null) {

            requestQueue = Volley.newRequestQueue(mContext);

        }

        return requestQueue;
    }

    public ImageLoader getImageLoader()
    {

        return imageLoader;
    }

    public void addToRequestQueue(Request request){

    }
}
