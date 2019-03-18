package com.bvifsc.mbc.pageModels;

import android.util.Log;

import com.bvifsc.core.R;
import com.bvifsc.core.di.BVIRA_Application;
import com.bvifsc.core.models.response.Action;
import com.bvifsc.core.models.response.PageResponseModel;
import com.bvifsc.core.presenters.BasePresenter;
import com.bvifsc.mbc.Common.Callback;
import com.bvifsc.mbc.Common.FragmentLoader;
import com.bvifsc.mbc.Common.MBC_Constants;
import com.bvifsc.mbc.assemblers.converters.MBCGenericDataResponseConverter;
import com.bvifsc.mbc.model.RegisterMBCPageResponseModel;
import com.bvifsc.mbc.model.SignInResponseModel;
import com.bvifsc.mbc.model.mbcDataView.MBCGenericDataResponseModel;
import com.bvifsc.mbc.model.mbcDataView.MBCViewPageResponseModel;
import com.bvifsc.mbc.model.registerMBC.MBCDataResponseModel;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

/**
 * Created by Ramesh on 28-01-2019.
 */
public class MBCView implements FragmentLoader {
    private final String pageType = "mbcView";
    private final String screenHeading = "VIEW MBC DATA";
    private Map<String, Action> buttonMap = setButtonMap();
    private final String pageTitle = "VIEW MBC DATA";
    String mbcNumber;
    @Inject
    BasePresenter basePresenter;

    public MBCView() {
        this.inject();

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

    public String getPageTitle() {
        return pageTitle;
    }

    void inject() {
        BVIRA_Application.getObjectGraph(BVIRA_Application.context).inject(this);
    }

    private Map<String, Action> setButtonMap() {
        Action primaryAction = new Action("ParticipantViewFragment", "BVI_RA_MBC", "bviFscMBC", "POST");
        primaryAction.setTitle("Next");
      //  primaryAction.setBrowserUrl(buildUrl());


        Map<String, Action> buttonMap = new HashMap<>();
        buttonMap.put("PrimaryButton", primaryAction);

        Action secondaryAction = new Action("back", "BVI_RA_MBC", "bviFscMBC", "LOAD");
        secondaryAction.setTitle("Back");
        buttonMap.put("SecondaryButton", secondaryAction);

        return buttonMap;

    }

    public String getMbcNumber() {
        return mbcNumber;
    }

    public void setMbcNumber(String mbcNumber) {
        this.mbcNumber = mbcNumber;
    }


    String buildUrl()
    {

        String url =
                BVIRA_Application.context.getResources().getString(R.string.http)
                        + "://"
                        + BVIRA_Application.context.getResources().getString(R.string.ip_address) + ":"
                        + BVIRA_Application.context.getResources().getString(R.string.port)
                        + BVIRA_Application.context.getResources().getString(R.string.version)
                        + BVIRA_Application.context.getResources().getString(R.string.mbcData)
                        +getMbcNumber();

        return url;

    }

    String buildGetMBCDataUrl(String mbcNumber)
    {
        String url =
                BVIRA_Application.context.getResources().getString(R.string.http)
                        + "://"
                        + BVIRA_Application.context.getResources().getString(R.string.ip_address) + ":"
                        + BVIRA_Application.context.getResources().getString(R.string.port)
                        + BVIRA_Application.context.getResources().getString(R.string.version)
                        + BVIRA_Application.context.getResources().getString(R.string.mbcData)
                        +mbcNumber
                        +"&isDashboard=false";

        return url;

    }

    void getMBCData()
    {
        String MBCNumber =getMbcNumber();

        Log.d("@MBC", "MBC Number :" + MBCNumber);

        Action getMBCDataAction =new Action(MBC_Constants.MBC_DATA,"BVI_RA_MBC","bviFscMBC","GET");
        getMBCDataAction.setTitle("mbcData");
        getMBCDataAction.setBrowserUrl(buildGetMBCDataUrl(mbcNumber));
        basePresenter.showProgressbar();

        basePresenter.executeAction(getMBCDataAction,basePresenter.getResourceToConsume(getMBCDataAction, onActionSuccessCallback()));


    }

    <R> Callback<R> onActionSuccessCallback()
    {
        return  response -> {

            MBCDataResponseModel mbcDataResponseModel= (MBCDataResponseModel) response;
            MBCViewPageResponseModel mbcViewPageResponseModel = new MBCViewPageResponseModel(MBC_Constants.MBC_VIEW,getScreenHeading());

            PageResponseModel pageInfo = new PageResponseModel(MBC_Constants.AMEND_CHARTER,screenHeading);
            pageInfo.setTitle(getPageTitle());
            pageInfo.setButtonMap(getButtonMap());

            mbcViewPageResponseModel.setPageInfo(pageInfo);
            mbcViewPageResponseModel.setMbcDataModel(mbcDataResponseModel.getMbcDataModel());

            basePresenter.publishResponseEvent(mbcViewPageResponseModel);
            basePresenter.hideProgressbar();

        };

    }


    @Override
    public <T> void load() {

       getMBCData();

    }
}
