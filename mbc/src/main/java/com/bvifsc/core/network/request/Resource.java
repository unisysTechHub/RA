package com.bvifsc.core.network.request;

import com.bvifsc.core.models.response.BaseResponse;
import com.bvifsc.mbc.Common.Callback;
import com.bvifsc.mbc.network.request.BaseRequest;
import com.bvifsc.mbc.network.request.FormData;
import com.bvifsc.mbc.network.response.ResponseInfo;

import java.util.Map;

/**
 * Created by Ramesh on 28-11-2018.
 */
public class Resource {
    String resourceToConsume;
    String service;
    boolean isService;
    String appContext;
    BaseRequest bodyRequest;
    FormData formData;
    Map<String ,String> extraParamas;
    ResultCallback resultCallback;
    String browseUrl;
    int actionType;
    public interface Method {
        int GET = 0;
        int POST = 1;
        int LOAD=2;
    }

    public Resource(String resourceToConsume, String appContext, ResultCallback resultCallback) {
        this.resourceToConsume = resourceToConsume;
        this.appContext = appContext;
        this.resultCallback = resultCallback;
    }

    public static class  ResultCallback
    {
        Callback<BaseResponse> onSuccess;
        Callback<Exception> onException;
        Callback<BaseResponse> onBusinessError;


        public ResultCallback(Callback<BaseResponse> onSuccess, Callback<Exception> onException, Callback<BaseResponse> onBusinessError) {
            this.onSuccess = onSuccess;
            this.onException = onException;
            this.onBusinessError = onBusinessError;
        }

        public Callback<BaseResponse> getOnSucess() {
            return onSuccess;
        }


        public Callback<Exception> getOnException() {
            return onException;
        }


        public Callback<BaseResponse> getOnBusinessError() {
            return onBusinessError;
        }



    }

    public static class ResourceBuilder implements Builder<Resource>
    {
        private String resourceToConsume;
        private String appContext;
        private Map<String ,String> extraParamas;
        private  Callback<BaseResponse> onSuccess;
        private  Callback<Exception> onException;
        private  Callback<BaseResponse> onBusinessError;
        private BaseRequest bodyRequest;
        private String browseUrl;
        private String actionType;
        private FormData formData;


        public ResourceBuilder(String resourceToConsume, String appContext, Callback<BaseResponse> onSuccess, Callback<Exception> onException)
        {

            this.resourceToConsume = resourceToConsume;
            this.appContext = appContext;
            this.onSuccess=onSuccess;
            this.onException=onException;

        }

        public void onBusinessError(Callback<BaseResponse> onBusinessError)
        {
            this.onBusinessError=onBusinessError;

        }
        public void extraParameters(Map<String, String> extraParameters) {
            this.extraParamas = extraParameters;

        }

        public void bodyRequest(BaseRequest bodyRequest) {
            this.bodyRequest = bodyRequest;

        }

        public void formData(FormData formData)
        {
            this.formData=formData;

        }
        public void browseUrl(String browseUrl)
        {
            this.browseUrl=browseUrl;
        }

        public void actionType(String actionType){this.actionType=actionType;}


        @Override
        public Resource build() {
            ResultCallback resultCallback = new ResultCallback(onSuccess,onException,onBusinessError);
            Resource resource = new Resource(resourceToConsume,appContext,resultCallback);
            resource.browseUrl=this.browseUrl;
            if(this.actionType.equals("GET"))
                resource.actionType=Method.GET;
            else
                if(this.actionType.equals("POST"))
                    resource.actionType=Method.POST;

            resource.extraParamas=this.extraParamas;
            resource.bodyRequest = this.bodyRequest;
            resource.formData=this.formData;

            return resource;
        }
    }



}
