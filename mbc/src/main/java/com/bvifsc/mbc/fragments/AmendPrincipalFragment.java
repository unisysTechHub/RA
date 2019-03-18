package com.bvifsc.mbc.fragments;


import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.bvifsc.core.R;
import com.bvifsc.core.fragments.BaseFragment;
import com.bvifsc.core.models.response.Action;
import com.bvifsc.core.models.response.PageResponseModel;
import com.bvifsc.mbc.Common.AppFragmentUtils;
import com.bvifsc.mbc.Common.MBC_Constants;
import com.bvifsc.mbc.datamodel.MBCData;
import com.bvifsc.mbc.model.RegisterMBCPageResponseModel;
import com.bvifsc.mbc.model.registerMBC.BusinessPurpose;
import com.bvifsc.mbc.model.registerMBC.Country;
import com.bvifsc.mbc.model.registerMBC.Nationality;
import com.bvifsc.mbc.model.registerMBC.RegisterMBCModel;
import com.google.gson.Gson;


public class AmendPrincipalFragment extends BaseFragment {
    private RegisterMBCPageResponseModel registerMBCPageResponseModel;
    private RegisterMBCModel registerMBCModel;
    private PageResponseModel registerMBCpageModel;
    private MBCData registerMBCDataModel;

    TextView screenHeader;
    boolean onBackStack=false;
    RegisterMBCFragment mbcPrincipalFragment;
    TextView mbcNumber;
    public AmendPrincipalFragment() {
        // Required empty public constructor
    }



    public static AmendPrincipalFragment newInstance(RegisterMBCPageResponseModel registerMBCPageResponseModel) {
        AmendPrincipalFragment fragment = new AmendPrincipalFragment();
        Bundle args = new Bundle();
        args.putParcelable(MBC_Constants.AMEND_PRINCIPAL, registerMBCPageResponseModel);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

            registerMBCPageResponseModel= getArguments().getParcelable(MBC_Constants.AMEND_PRINCIPAL);
            if(registerMBCPageResponseModel != null)
            {

                registerMBCModel=registerMBCPageResponseModel.getRegisterMBCModel();
                registerMBCpageModel=registerMBCPageResponseModel.getPageInfo();
                registerMBCDataModel=registerMBCPageResponseModel.getRegisterMBCDataModel();
            }
        }
    }



    @Override
    protected int getLayout() {
        return R.layout.fragment_amend_principal;
    }

    @Override
    protected void initFragment(View rootView) {
        Log.d("@MBC","initFragment");
        setUpAmendCharterPageTitleAndHeader(rootView);
        setUpToDisplayMBCPrincipalData();
        setUpAmendPrinciplePageActions(rootView);

    }

    void setUpAmendCharterPageTitleAndHeader(View rootView)
    {
        screenHeader=rootView.findViewById(R.id.amend_principal_header);
        screenHeader.setText(registerMBCpageModel.getTitle());
        mbcNumber=rootView.findViewById(R.id.mbc_number);
        mbcNumber.setText(registerMBCDataModel.getMbcNumber());


    }

    void setUpToDisplayMBCPrincipalData()
    {
         mbcPrincipalFragment= RegisterMBCFragment.newInstance(registerMBCPageResponseModel);
        this.getChildFragmentManager().beginTransaction().replace(R.id.amend_principal_frameLayout,mbcPrincipalFragment).commit();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                setUpToEnableDisableToEditMBCPrincipalData(mbcPrincipalFragment);
                disablePrincipalFragmentActions();
            }
        },100);


    }


    void setUpToEnableDisableToEditMBCPrincipalData(RegisterMBCFragment principalFragment)
    {
        Log.d("@MBC", "setUpToEnableDisableToEditMBCPrincipalData");
        principalFragment.mbcRadioButton.setVisibility(View.GONE);
        principalFragment.mbcCompanyRadioButton.setVisibility(View.GONE);
        principalFragment.principalFirstName.setText(registerMBCDataModel.getPrincipalShareholder().getFirstName());
        principalFragment.principalFirstName.setEnabled(false);
        principalFragment.firstNameOnlyCheckBox.setOnCheckedChangeListener( (view, isChecked) -> {
            if(isChecked)
            {
                principalFragment.principalMiddleName.setVisibility(View.GONE);
                principalFragment.principalLastName.setVisibility(View.GONE);
                principalFragment.principalMiddleName.setEnabled(false);
                principalFragment.principalLastName.setEnabled(false);
            }
            else
            {
                principalFragment.principalMiddleName.setVisibility(View.VISIBLE);
                principalFragment.principalLastName.setVisibility(View.VISIBLE);
                principalFragment.principalMiddleName.setEnabled(true);
                principalFragment.principalLastName.setEnabled(true);


            }


        });

        Log.d("@MBC",registerMBCDataModel.getPrincipalShareholder().isFirstNameOnlyCheck()+"");
        if( registerMBCDataModel.getPrincipalShareholder().isFirstNameOnlyCheck() || registerMBCDataModel.getPrincipalShareholder().getIsFirstNameOnly().equals("Y")) {

            principalFragment.firstNameOnlyCheckBox.setChecked(true);


        }
        else
        {
            principalFragment.principalMiddleName.setText(registerMBCDataModel.getPrincipalShareholder().getMiddleName());
            principalFragment.principalLastName.setText(registerMBCDataModel.getPrincipalShareholder().getLastName());
            principalFragment.firstNameOnlyCheckBox.setChecked(false);
        }



        principalFragment.doingBusinessAs.setText(registerMBCDataModel.getDoingBusinessAs());


        principalFragment.professionalDesignation.setText("Mr.");
        //principalFragment.professionalDesignation.setEnabled(false);

        principalFragment.noOfSharesSpinner.setSelection(registerMBCDataModel.getNoOfShares());
        principalFragment.noOfSharesSpinner.setEnabled(false);

        principalFragment.countriesSpinnerPrevPosition=getCountriesSpinnerPosition(registerMBCDataModel.getCountryOfOperation());
        principalFragment.countriesSpinner.setSelection(getCountriesSpinnerPosition(registerMBCDataModel.getCountryOfOperation()));

        principalFragment.nationalitiesSpinnerPrevPosition=getNationalitiesSpinnerPosition(registerMBCDataModel.getPrincipalShareholder().getNationality());
        principalFragment.nationalitiesSpinner.setSelection(getNationalitiesSpinnerPosition(registerMBCDataModel.getPrincipalShareholder().getNationality()));

        principalFragment.businessPurposeSpinnerPrevPosition=getBusinessPurposeSpinnerPosition(registerMBCDataModel.getBusinessPurpose());
        principalFragment.businessPurposeSpinner.setSelection(getBusinessPurposeSpinnerPosition(registerMBCDataModel.getBusinessPurpose()));
        principalFragment.checkBoxTerms.setChecked(true);


        onBackStack=true;

    }

    int getNationalitiesSpinnerPosition(Nationality mbcNationality)
    {
        for(Nationality nationality : registerMBCModel.getNationalities())
        {
            if(registerMBCModel.getNationalities().indexOf(nationality) > 0) {
                if (nationality.getAlpha3Code().equalsIgnoreCase(mbcNationality.getAlpha3Code()) ) {
                    return registerMBCModel.getNationalities().indexOf(nationality);

                }
            }
        }

        return -1;
    }
    int getCountriesSpinnerPosition(Country mbcCountryOfOperation)
    {
        for (Country country : registerMBCModel.getCountries())
        {

            if(registerMBCModel.getCountries().indexOf(country) > 0)
            {
                if (country.getAlpha3Code().equalsIgnoreCase(mbcCountryOfOperation.getAlpha3Code())) {
                    return registerMBCModel.getCountries().indexOf(country);

                }
            }


        }


        return -1;
    }

    int getBusinessPurposeSpinnerPosition(BusinessPurpose mbcBusinessPurpose)
    {
        for(BusinessPurpose businessPurpose : registerMBCModel.getBusinessPurposeList())
        {
            if(registerMBCModel.getBusinessPurposeList().indexOf(businessPurpose)> 0)
            if(businessPurpose.getBusinessPurposeId() == mbcBusinessPurpose.getBusinessPurposeId())
            {
                return registerMBCModel.getBusinessPurposeList().indexOf(businessPurpose);

            }


        }

        return -1;

    }
    void disablePrincipalFragmentActions()
    {
        View footer=mbcPrincipalFragment.getView().findViewById(R.id.principal_mbc_footer);
        View registerMBCHeader  =mbcPrincipalFragment.getView().findViewById(R.id.register_mbc_header);
        footer.setVisibility(View.GONE);
        registerMBCHeader.setVisibility(View.GONE);


    }


    void setUpAmendPrinciplePageActions(View rootView)
    {
        Log.d("@MBC", "setUpAmendPrinciplePageActions");
        Button primaryButton=rootView.findViewById(R.id.primaryButton);
        primaryButton.setEnabled(true);


        Button secondaryButton= rootView.findViewById(R.id.secondaryButton);
        secondaryButton.setVisibility(View.GONE);

        if(registerMBCpageModel.getButtonMap() != null)
        {
            Action primaryAction = registerMBCpageModel.getButtonMap().get(MBC_Constants.PRIMARY_BUTTON);
            if(primaryAction!= null)
            {
                primaryButton.setText("Next");

                primaryButton.setOnClickListener( view ->{


                    if(mbcPrincipalFragment.areValidInput())
                    {
                        mbcPrincipalFragment.isPrincipalDataChanged=false;
                        mbcPrincipalFragment.dataObserver();
                        registerMBCDataModel.setPrincipalDataChanged(mbcPrincipalFragment.isPrincipalDataChanged);

                        mbcPrincipalFragment.getScreenData();
                        Gson gson = new Gson();
                        gson.excluder().excludeFieldsWithoutExposeAnnotation();
                        String gsonString =gson.toJson(registerMBCDataModel);
                        Log.d("@MBC","JSON" + gsonString );

                            Log.d("@MBC", "no of shares" + registerMBCDataModel.getNoOfShares() );
                            registerMBCDataModel.setFirstParticipantSeqNo(0);
                            AppFragmentUtils.loadAmendParticipantFragment(registerMBCPageResponseModel);

                    }


                });

            }

        }
        else
            primaryButton.setVisibility(View.GONE);

    }


}
