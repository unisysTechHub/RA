package com.bvifsc.core.network.request;

import android.content.Context;
import android.service.carrier.CarrierMessagingService;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.bvifsc.core.common.AppNetworkRequester;
import com.bvifsc.core.common.CORE_CONSTANTS;
import com.bvifsc.core.common.Converter;
import com.bvifsc.core.models.response.BaseResponse;
import com.bvifsc.mbc.Common.MBC_Constants;
import com.bvifsc.mbc.assemblers.MBCServerResponseConverterLoader;
import com.bvifsc.mbc.assemblers.MBCServerResponseLoader;
import com.bvifsc.mbc.network.response.ResponseInfo;


import org.greenrobot.eventbus.EventBus;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by Ramesh on 28-11-2018.
 */
public class ResourceServiceRequestor implements RequestExecutor {
    public static String TAG = ResourceServiceRequestor.class.getName() + MBC_Constants.SPACES;
    AppNetworkRequester appNetworkRequester;
    Resource.ResultCallback resultCallback;
    EventBus eventBus;
    Context context;


    @Inject
    public ResourceServiceRequestor(Context context,AppNetworkRequester appNetworkRequester, EventBus eventBus)
    {

          this.appNetworkRequester=appNetworkRequester;
          this.eventBus=eventBus;
          this.context=context;
    }

    @Override
    public void executeRequest(Resource resource) {
        Log.d(MBC_Constants.APP_TAG, "resource to consume " + resource.resourceToConsume);
        Resource.ResultCallback resultCallback=resource.resultCallback;
        this.resultCallback=resultCallback;

        executeRequest(MBCServerResponseLoader.RESPONSE_MAPPING.get(resource.resourceToConsume),resource);

    }

    @Override
    public <R extends ResponseInfo> void executeRequest(Class<R> input, Resource resource) {



          String url=resource.browseUrl;
          Log.d(CORE_CONSTANTS.APP_TAG , TAG +  url);

        final ResourceRequest<R> resourceRequest = new ResourceRequest<>(url, input, null,resource, new Response.Listener<R>() {
            @Override
            public void onResponse(R response) {


                ResourceServiceRequestor.this.notify(buildresponseModel(response,resource));



            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                if( error instanceof NoConnectionError) {

                    Log.d("@Ramesh", "NO connection Error");
                    resultCallback.onException.notify(error);
                }
                if (error instanceof TimeoutError ) {
                    Log.d("@Ramesh","Timeout Error" );
                    resultCallback.onException.notify(error);

                } else if (error instanceof AuthFailureError) {
                    Log.d("@Ramesh","AuthFailure Error");
                    resultCallback.onException.notify(error);
                } else if (error instanceof ServerError) {
                    Log.d("@Ramesh","Server Error" + error.getMessage());
                    resultCallback.onException.notify(error);
                } else if (error instanceof NetworkError) {
                    Log.d("@Ramesh","NetworkError");
                    resultCallback.onException.notify(error);
                } else if (error instanceof ParseError) {
                    Log.d("@Ramesh","ParseError" + error.getMessage());
                    resultCallback.onException.notify(error);
                }
            }
        });

        resourceRequest.setRetryPolicy(new RetryPolicy() {
            @Override
            public int getCurrentTimeout() {
                return 100000;
            }

            @Override
            public int getCurrentRetryCount() {
                return 1;
            }

            @Override
            public void retry(VolleyError error) throws VolleyError {

            }
        });



        appNetworkRequester.getRequestQueue().add(resourceRequest);

    }
    <R extends BaseResponse,T extends ResponseInfo>  R   buildresponseModel(T response,Resource resource)
    {
                try {

                    if (response!=null ) {
                        {
                            Converter  converter=MBCServerResponseConverterLoader.CONVERTER_MAPPING.get(resource.resourceToConsume).newInstance();
                              return converter.convert(response);
                        }
                    }


                    return null;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return  null;
        } catch (InstantiationException e) {
            e.printStackTrace();
            return  null;
        }

    }


    @Override
    public <R extends BaseResponse> void notify(R response) {

            if(response != null) {
                if (response.getPageType() != null)
                    if(response.isSuccess())
                    resultCallback.onSuccess.notify(response);
                    else
                    resultCallback.onBusinessError.notify(response);

                }

        }


}
