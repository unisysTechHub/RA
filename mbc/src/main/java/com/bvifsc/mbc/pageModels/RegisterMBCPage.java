package com.bvifsc.mbc.pageModels;

import com.bvifsc.core.R;
import com.bvifsc.core.di.BVIRA_Application;
import com.bvifsc.core.models.response.Action;
import com.bvifsc.core.models.response.BaseResponse;
import com.bvifsc.core.models.response.PageResponseModel;
import com.bvifsc.core.presenters.BasePresenter;
import com.bvifsc.mbc.Common.Callback;
import com.bvifsc.mbc.Common.FragmentLoader;
import com.bvifsc.mbc.Common.MBC_Constants;
import com.bvifsc.mbc.datamodel.PrincipalShareholder;
import com.bvifsc.mbc.datamodel.MBCData;
import com.bvifsc.mbc.datamodel.RegisteredAgent;
import com.bvifsc.mbc.model.RegisterMBCPageResponseModel;
import com.bvifsc.mbc.model.registerMBC.BusinessPurposeResponseModel;
import com.bvifsc.mbc.model.registerMBC.CountriesResponseModel;
import com.bvifsc.mbc.model.registerMBC.NationalitiesResponseModel;
import com.bvifsc.mbc.model.registerMBC.ParticipantRightsResponseModel;
import com.bvifsc.mbc.model.registerMBC.PaymentSummaryResponseModel;
import com.bvifsc.mbc.model.registerMBC.RegisterMBCModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

/**
 * Created by Ramesh on 03-01-2019.
 */
public class RegisterMBCPage implements FragmentLoader{
    private final String pageType= MBC_Constants.REGISTER_MBC;
    private final String  screenHeading= "Register MBC";
    private Map<String,Action> buttonMap =setButtonMap();
    private final String pageTitle="Register MBC";
    RegisterMBCModel registerMBCModel;
    @Inject
    BasePresenter basePresenter;

    RegisterMBCPageResponseModel registerMBCPageResponseModel;
    public RegisterMBCPage()
    {
        inject();

    }

    private  void inject(){
        BVIRA_Application.getObjectGraph(BVIRA_Application.context).inject(this);
    }

    public String getPageType() {
        return pageType;
    }

    public String getScreenHeading() {
        return screenHeading;
    }

    public Map<String, Action> getButtonMap() {
        return buttonMap;
    }

    public Map<String, Action> setButtonMap() {
        Action primaryAction = new Action("registerMBC","BVI_RA_MBC","bviFscMBC","POST");
        primaryAction.setTitle("Register");
        primaryAction.setBrowserUrl(buildUrl());
        Map<String,Action> buttonMap = new HashMap<>();
        buttonMap.put("PrimaryButton",primaryAction);

        Action secondaryAction = new Action("homePage","BVI_RA_MBC","bviFscMBC",null);
        secondaryAction.setTitle("Back");
        buttonMap.put("SecondaryButton",secondaryAction);

        return buttonMap;
    }


    public String getPageTitle() {
        return pageTitle;
    }
    String buildUrl()
    { String url =
            BVIRA_Application.context.getResources().getString(R.string.http)
                    + "://"
                    + BVIRA_Application.context.getResources().getString(R.string.ip_address) + ":"
                    + BVIRA_Application.context.getResources().getString(R.string.port)
                    + BVIRA_Application.context.getResources().getString(R.string.version)
                    + BVIRA_Application.context.getResources().getString(R.string.registerMBC);

        return url;

    }

    void getCountriesList()
    {
        Action getCountryCodesAction = new Action(MBC_Constants.COUNTRIES,"BVI_RA_MBC","bviFscMBC","GET");
        getCountryCodesAction.setTitle("Countries");
        getCountryCodesAction.setBrowserUrl(buildCountryCodesUrl());
        basePresenter.showProgressbar();
        basePresenter.executeAction(getCountryCodesAction,basePresenter.getResourceToConsume(getCountryCodesAction, onActionSuccessCallback()));


    }
    void getNationalities()
    {
        Action getnatinaltiesAction = new Action(MBC_Constants.NATIONALITIES,"BVI_RA_MBC","bviFscMBC","GET");
        getnatinaltiesAction.setTitle("Nationalities");
        getnatinaltiesAction.setBrowserUrl(buildNationalitiesUrl());

        basePresenter.executeAction(getnatinaltiesAction,basePresenter.getResourceToConsume(getnatinaltiesAction, onActionSuccessCallback()));
    }

    void getParticipantRights()
    {
        Action getParticipantRightsAction = new Action(MBC_Constants.PARTICIPANT_RIGHTS,"BVI_RA_MBC","bviFscMBC","GET");
        getParticipantRightsAction.setTitle("Nationalities");
        getParticipantRightsAction.setBrowserUrl(buildParticipantsRightsUrl());

        basePresenter.executeAction(getParticipantRightsAction,basePresenter.getResourceToConsume(getParticipantRightsAction, onActionSuccessCallback()));


    }

    void getBusinessPurposeList()
    {
        Action getBusinessPurposeListAction =new Action(MBC_Constants.BUSINESS_PURPOSE,"BVI_RA_MBC","bviFscMBC","GET");
        getBusinessPurposeListAction.setTitle("BusinessPurposes");
        getBusinessPurposeListAction.setBrowserUrl(buildBusinessPurposeListUrl());

        basePresenter.executeAction(getBusinessPurposeListAction,basePresenter.getResourceToConsume(getBusinessPurposeListAction, onActionSuccessCallback()));

    }

    void getPaymentSummary()
    {
        Action getPaymentSummaryAction =new Action(MBC_Constants.PAYMENT_SUMMARY,"BVI_RA_MBC","bviFscMBC","GET");
        getPaymentSummaryAction.setTitle("paymentSummary");
        getPaymentSummaryAction.setBrowserUrl(buildPaymentSummaryUrl());

        basePresenter.executeAction(getPaymentSummaryAction,basePresenter.getResourceToConsume(getPaymentSummaryAction, onActionSuccessCallback()));



    }

    <T extends BaseResponse> Callback<T> onActionSuccessCallback()
    {
        return  response -> {
                    registerMBCPageResponseModel= null;

                if(response.getPageType().equalsIgnoreCase(MBC_Constants.COUNTRIES))
                {


                    registerMBCModel= new RegisterMBCModel();
                    CountriesResponseModel countriesResponseModel=(CountriesResponseModel) response;

                    registerMBCModel.setCountries(countriesResponseModel.getCountries());
                    getNationalities();





                }
                if(response.getPageType().equalsIgnoreCase(MBC_Constants.NATIONALITIES))
                {

                    NationalitiesResponseModel nationalitiesResponseModel= (NationalitiesResponseModel) response;

                    registerMBCModel.setNationalities(nationalitiesResponseModel.getNationalities());

                    getParticipantRights();






                }

                if(response.getPageType().equalsIgnoreCase(MBC_Constants.PARTICIPANT_RIGHTS))
                {
                    ParticipantRightsResponseModel participantRightsResponseModel=(ParticipantRightsResponseModel)response;

                    registerMBCModel.setParticipantRightsList(participantRightsResponseModel.getParticipantRightsList());

                    getBusinessPurposeList();


                }

                if(response.getPageType().equalsIgnoreCase(MBC_Constants.BUSINESS_PURPOSE))
                {
                    BusinessPurposeResponseModel businessPurposeResponseModel = (BusinessPurposeResponseModel) response;
                    registerMBCModel.setBusinessPurposeList(businessPurposeResponseModel.getBusinessPurposeList());

                    getPaymentSummary();

                }

                if(response.getPageType().equalsIgnoreCase(MBC_Constants.PAYMENT_SUMMARY))
                {
                    PaymentSummaryResponseModel paymentSummaryResponseModel =(PaymentSummaryResponseModel) response;

                    registerMBCPageResponseModel=new RegisterMBCPageResponseModel(MBC_Constants.REGISTER_MBC,screenHeading);
                    registerMBCModel.setPaymentSummary(paymentSummaryResponseModel.getPaymentSummary());

                    PageResponseModel pageInfo = new PageResponseModel(MBC_Constants.REGISTER_MBC,screenHeading);
                    pageInfo.setTitle(pageTitle);
                    pageInfo.setButtonMap(getButtonMap());
                    registerMBCPageResponseModel.setPageInfo(pageInfo);
                    registerMBCPageResponseModel.setRegisterMBCModel(registerMBCModel);

                    MBCData registerMBCDataModel = new MBCData();
                    PrincipalShareholder principalShareholder= new PrincipalShareholder();
                    registerMBCDataModel.setPrincipalShareholder(principalShareholder);
                    registerMBCDataModel.setParticipantShareholders(new ArrayList<>());
                    registerMBCDataModel.setRegisteredAgent(new RegisteredAgent());
                    registerMBCPageResponseModel.setRegisterMBCDataModel(registerMBCDataModel);

                    basePresenter.publishResponseEvent(registerMBCPageResponseModel);
                    basePresenter.hideProgressbar();

                }




        };

    }



    String buildCountryCodesUrl()
    {String url =
            BVIRA_Application.context.getResources().getString(R.string.http)
                    + "://"
                    + BVIRA_Application.context.getResources().getString(R.string.bb_ip_address) + ":"
                    + BVIRA_Application.context.getResources().getString(R.string.port)
                    + BVIRA_Application.context.getResources().getString(R.string.bbVersion)
                    + BVIRA_Application.context.getResources().getString(R.string.countries);

        return url;


    }

    String buildNationalitiesUrl()
    {
        String url =
                BVIRA_Application.context.getResources().getString(R.string.http)
                        + "://"
                        + BVIRA_Application.context.getResources().getString(R.string.bb_ip_address) + ":"
                        + BVIRA_Application.context.getResources().getString(R.string.port)
                        + BVIRA_Application.context.getResources().getString(R.string.bbVersion)
                        + BVIRA_Application.context.getResources().getString(R.string.nationalities);


        return url;
    }

    String buildParticipantsRightsUrl()
    {
        String url =
                BVIRA_Application.context.getResources().getString(R.string.http)
                        + "://"
                        + BVIRA_Application.context.getResources().getString(R.string.bb_ip_address) + ":"
                        + BVIRA_Application.context.getResources().getString(R.string.port)
                        + BVIRA_Application.context.getResources().getString(R.string.bbVersion)
                        + BVIRA_Application.context.getResources().getString(R.string.participantRights);


        return url;


    }

    String buildBusinessPurposeListUrl()
    {
        String url =
                BVIRA_Application.context.getResources().getString(R.string.http)
                        + "://"
                        + BVIRA_Application.context.getResources().getString(R.string.bb_ip_address) + ":"
                        + BVIRA_Application.context.getResources().getString(R.string.port)
                        + BVIRA_Application.context.getResources().getString(R.string.bbVersion)
                        + BVIRA_Application.context.getResources().getString(R.string.businessPurposeList);


        return url;


    }
    String buildPaymentSummaryUrl()
    {
        String url =
                BVIRA_Application.context.getResources().getString(R.string.http)
                        + "://"
                        + BVIRA_Application.context.getResources().getString(R.string.ip_address) + ":"
                        + BVIRA_Application.context.getResources().getString(R.string.port)
                        + BVIRA_Application.context.getResources().getString(R.string.version)
                        + BVIRA_Application.context.getResources().getString(R.string.paymentSummary)
                        +"PROD123400";


        return url;

    }

    @Override
    public <T> void load() {
        getCountriesList();

    }

}
