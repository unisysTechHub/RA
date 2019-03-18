package com.bvifsc.mbc.pageModels;

import android.util.Log;

import com.bvifsc.core.R;
import com.bvifsc.core.di.BVIRA_Application;
import com.bvifsc.core.models.response.Action;
import com.bvifsc.core.models.response.BaseResponse;
import com.bvifsc.core.models.response.PageResponseModel;
import com.bvifsc.core.presenters.BasePresenter;
import com.bvifsc.mbc.Common.Callback;
import com.bvifsc.mbc.Common.FragmentLoader;
import com.bvifsc.mbc.Common.MBC_Constants;
import com.bvifsc.mbc.model.kycDocuments.KYCDocumentsListResponseModel;
import com.bvifsc.mbc.model.KYCDocumentsPageResponseModel;
import com.bvifsc.mbc.model.kycDocuments.KYCDocsDetailsResponseModel;
import com.bvifsc.mbc.model.UserAuthInfo;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

/**
 * Created by Ramesh on 14-12-2018.
 */
public class KYCDocumentsPage implements FragmentLoader{
    private final String pageType= MBC_Constants.KYC_DOCUMENTS;
    private final String  screenHeading= "Customer Information";
    private Map<String,Action> buttonMap =setButtonMap();
    private final String pageTitle="Customer Information  \n \t KYC DOCUMENTS \n   \t UPLOAD";
    @Inject
    BasePresenter basePresenter;
    KYCDocumentsPageResponseModel kycDocumentsPageResponseModel=null;
    public KYCDocumentsPage()
    {
        inject();

    }

    private  void inject(){
        BVIRA_Application.getObjectGraph(BVIRA_Application.context).inject(this);
    }

    public String getPageTitle() {
        return pageTitle;
    }

    public String getPageType() {
        return pageType;
    }

    public String getScreenHeading() {
        return screenHeading;
    }

    public Map<String, Action> getButtonMap() {
        return this.buttonMap;
    }


    private   Map<String,Action>  setButtonMap() {
        Action primaryAction = new Action("uploadKYC","BVI_RA_MBC","bviFscMBC","POST");
        primaryAction.setTitle("Submit");
//        primaryAction.setBrowserUrl( "http://10.0.2.2:8880/RAPORTAL-1.0/raportal/api/user/userkyc");
         //primaryAction.setBrowserUrl( "http://182.74.133.91:8080/RAPORTAL-1.0/raportal/api/user/userkyc");
        primaryAction.setBrowserUrl(buildUrl());
        Map<String,Action> buttonMap = new HashMap<>();
        buttonMap.put("PrimaryButton",primaryAction);

        Action secondaryAction = new Action("homePage","BVI_RA_MBC","bviFscMBC",null);
        secondaryAction.setTitle("Later");
        buttonMap.put("SecondaryButton",secondaryAction);

        return buttonMap;

    }
    String buildUrl()
    { String url =
            BVIRA_Application.context.getResources().getString(R.string.http)
                    + "://"
            + BVIRA_Application.context.getResources().getString(R.string.ip_address) + ":"
                  + BVIRA_Application.context.getResources().getString(R.string.port)
                    + BVIRA_Application.context.getResources().getString(R.string.version)
                    + BVIRA_Application.context.getResources().getString(R.string.uploadKYC);

        return url;


    }


    private void  getListOfKYCDocumentTypes()
    {
        Action kycDocumentTypesListAction = new Action("kycDocumentTypes","BVI_RA_MBC","bviFscMBC","GET");
        kycDocumentTypesListAction.setTitle("kycDocumentTypes");
        //kycDocumentTypesListAction.setBrowserUrl( "http://182.74.133.91:8080/RAPORTAL-1.0/raportal/api/user/documentTypes");
        kycDocumentTypesListAction.setBrowserUrl(buildKycDocumentTypeUrl());
        basePresenter.showProgressbar();
        basePresenter.executeAction(kycDocumentTypesListAction,basePresenter.getResourceToConsume(kycDocumentTypesListAction, onActionSuccessCallback()));

    }

    private void getUserKYCDocsDeails()
    {
        Action userKycDocsDetailsAction= new Action("kycDocsDetails","BVI_RA_MBC","bviFscMBC","GET");
        userKycDocsDetailsAction.setTitle("userKycDocsDetails");
        userKycDocsDetailsAction.setBrowserUrl(buildUserKycDocsDetailsUrl());
        basePresenter.showProgressbar();
        basePresenter.executeAction(userKycDocsDetailsAction,basePresenter.getResourceToConsume(userKycDocsDetailsAction,onActionSuccessCallback()));



    }
    String buildUserKycDocsDetailsUrl()
    {
        String url =
                BVIRA_Application.context.getResources().getString(R.string.http)
                        + "://"
                        + BVIRA_Application.context.getResources().getString(R.string.ip_address) + ":"
                        + BVIRA_Application.context.getResources().getString(R.string.port)
                        + BVIRA_Application.context.getResources().getString(R.string.version)
                        + BVIRA_Application.context.getResources().getString(R.string.useKycDocsDetails)
                + "userId=" + UserAuthInfo.getInstance().getUserId();



        return url;
    }

    String buildKycDocumentTypeUrl()
    {
        String url =
                BVIRA_Application.context.getResources().getString(R.string.http)
                        + "://"
                        + BVIRA_Application.context.getResources().getString(R.string.ip_address) + ":"
                        + BVIRA_Application.context.getResources().getString(R.string.port)
                        + BVIRA_Application.context.getResources().getString(R.string.version)
                        + BVIRA_Application.context.getResources().getString(R.string.kycDocumentTypes);

        return url;


    }
    <T extends BaseResponse> Callback<T> onActionSuccessCallback()
    {
        return response -> {

            Log.d("@MBC", "KYC documents page onAction call back");

            if(response instanceof KYCDocumentsListResponseModel ) {

                KYCDocumentsListResponseModel kycDocumentsListResponseModel = (KYCDocumentsListResponseModel) response;

                kycDocumentsPageResponseModel = new KYCDocumentsPageResponseModel(getPageType(), getScreenHeading());
                kycDocumentsPageResponseModel.setPageInfo(new PageResponseModel(getPageType(), getScreenHeading()));
                kycDocumentsPageResponseModel.getPageInfo().setTitle(getPageTitle());
                kycDocumentsPageResponseModel.getPageInfo().setButtonMap(getButtonMap());
                kycDocumentsPageResponseModel.setKycDocumentsListModel(kycDocumentsListResponseModel.getKycDocumentsListModel());

                //basePresenter.publishResponseEvent(kycDocumentsPageResponseModel);
                basePresenter.hideProgressbar();
                getUserKYCDocsDeails();


            }
            else
             if(response instanceof KYCDocsDetailsResponseModel)
             {
                 KYCDocsDetailsResponseModel kycDocsDetailsResponseModel= (KYCDocsDetailsResponseModel)response;


                 if(kycDocsDetailsResponseModel.getUserKycDocsDetailsList()!= null)
                 kycDocumentsPageResponseModel.setKycDocsDetails(kycDocsDetailsResponseModel.getUserKycDocsDetailsList());


                   basePresenter.publishResponseEvent(kycDocumentsPageResponseModel);
                   basePresenter.hideProgressbar();


             }

        };

    }

    @Override
    public <T> void load() {

        Log.d("@MBC", "load KYC Documents Page");
        getListOfKYCDocumentTypes();
        //onActionSuccessCallback().notify(new SecurityQuestionResponseModel(null,null));

    }
}
