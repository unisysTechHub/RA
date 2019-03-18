package com.bvifsc.mbc.fragments;


import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import com.bvifsc.core.R;
import com.bvifsc.core.di.BVIRA_Application;
import com.bvifsc.core.fragments.BaseFragment;
import com.bvifsc.core.models.response.Action;
import com.bvifsc.core.models.response.PageResponseModel;
import com.bvifsc.mbc.Common.AppFragmentUtils;
import com.bvifsc.mbc.Common.MBC_Constants;
import com.bvifsc.mbc.Common.ValidationUtils;
import com.bvifsc.mbc.adapters.BusinessPurposeListSpinnerAdapter;
import com.bvifsc.mbc.adapters.CountriesSpinnerAdapter;
import com.bvifsc.mbc.adapters.NationalitiesSpinnerAdapter;
import com.bvifsc.mbc.datamodel.MBCData;
import com.bvifsc.mbc.model.RegisterMBCPageResponseModel;
import com.bvifsc.mbc.model.registerMBC.RegisterMBCModel;
import com.google.gson.Gson;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RegisterMBCFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RegisterMBCFragment extends BaseFragment implements View.OnClickListener{

    private RegisterMBCPageResponseModel registerMBCPageResponseModel;
    private RegisterMBCModel registerMBCModel;
    private PageResponseModel registerMBCpageModel;
    private MBCData registerMBCDataModel;

    TextView registerMBCHeader;
    RadioButton mbcRadioButton;
    RadioButton mbcCompanyRadioButton;
    Spinner countriesSpinner;
    int countriesSpinnerPrevPosition;
    TextView countryCode;
    TextInputEditText doingBusinessAs;
    CheckBox firstNameOnlyCheckBox;
    TextInputEditText professionalDesignation;
    TextInputEditText principalMiddleName;

    TextInputEditText principalFirstName;
    TextInputEditText principalLastName;
    Spinner nationalitiesSpinner;
    int nationalitiesSpinnerPrevPosition;
    Spinner businessPurposeSpinner;
    int businessPurposeSpinnerPrevPosition;
    TextView principalNationalityCode;
    Spinner noOfSharesSpinner;

    TextView transaction_fee;
    CheckBox checkBoxTerms;
    boolean isPrincipalDataChanged=false;

    String[] noOfSharesList={"0","1","2","3","4","5","6"};
    boolean isAlreadyLoaded=false;
    LinearLayout transfeeTerms;

    public RegisterMBCFragment() {
        // Required empty public constructor
    }


    public static RegisterMBCFragment newInstance(RegisterMBCPageResponseModel registerMBCPageResponseModel) {
        RegisterMBCFragment fragment = new RegisterMBCFragment();
        Bundle args = new Bundle();
        args.putParcelable(MBC_Constants.REGISTER_MBC, registerMBCPageResponseModel);
                fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            registerMBCPageResponseModel = getArguments().getParcelable(MBC_Constants.REGISTER_MBC);
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
        return R.layout.fragment_register_mbc;
    }

    @Override
    protected void injectFragment() {
        BVIRA_Application.getObjectGraph(BVIRA_Application.context).inject(this);
    }


    @Override
    protected void initFragment(View rootView)
    {
            setUpScreenHeaderAndTitle(rootView);
            setUpToInputRegisterMBCData(rootView);
            setUpRegisterMBCPageActions(rootView);
            isAlreadyLoaded=true;

            }

    void setUpScreenHeaderAndTitle(View rootView)
    {
            registerMBCHeader=  rootView.findViewById(R.id.register_mbc_header);
            registerMBCHeader.setText(registerMBCPageResponseModel.getPageInfo().getScreenHeading());



       }


    void setUpToInputRegisterMBCData(View rootView)
    {
        Log.d("@MBC","setUpToInputRegisterMBCData");

        mbcRadioButton = rootView.findViewById(R.id.mbc_radioButton);
        mbcRadioButton.setChecked(true);
        registerMBCDataModel.setNamePrefix(mbcRadioButton.getText().toString());

        mbcRadioButton.setOnClickListener(this);
        mbcCompanyRadioButton=rootView.findViewById(R.id.mbc_company_radio_button);
        mbcCompanyRadioButton.setOnClickListener(this);

        countryCode = rootView.findViewById(R.id.country_code);
        doingBusinessAs= rootView.findViewById(R.id.doing_business_as);

        firstNameOnlyCheckBox=rootView.findViewById(R.id.fist_name_only_check_box);
        principalMiddleName=rootView.findViewById(R.id.principal_middle_name);

        principalFirstName=rootView.findViewById(R.id.principal_first_name);
        principalLastName=rootView.findViewById(R.id.principal_last_name);

        firstNameOnlyCheckBox.setOnCheckedChangeListener( (view, isChecked) -> {
            if(isChecked)
            {
                principalMiddleName.setEnabled(false);
                principalLastName.setEnabled(false);

            }
            else
            {

                principalMiddleName.setEnabled(true);
                principalLastName.setEnabled(true);
            }


        });

        professionalDesignation=rootView.findViewById(R.id.professional_designation);

        principalNationalityCode=rootView.findViewById(R.id.principal_nationality_code);
        principalNationalityCode.setEnabled(false);
        checkBoxTerms=rootView.findViewById(R.id.checkBox_terms);
        checkBoxTerms.setOnCheckedChangeListener((view,isChecked) -> {

            if(isChecked)
            {
                registerMBCDataModel.setCheckBoxTerms(true);
            }
            else
                registerMBCDataModel.setCheckBoxTerms(false);

        });

        transaction_fee=rootView.findViewById(R.id.transaction_fee);
        transaction_fee.setText(String.valueOf(registerMBCModel.getPaymentSummary().getTransactionFee()));

         transfeeTerms = rootView.findViewById(R.id.transaction_fee_and_terms_checkbox);

        setUpCountriesSpinner(rootView);
        setUpNationalitiesSpinner(rootView);
        setUpBusinessPurposeSpinner(rootView);
        setUpNoOfSharesSpinner(rootView);


    }


    void setUpRegisterMBCPageActions(View rootView)
    {
        Log.d("@MBC", "setUpRegisterMBCPageActions");
        Button primaryButton=rootView.findViewById(R.id.primaryButton);
        primaryButton.setEnabled(true);


        Button secondaryButton= rootView.findViewById(R.id.secondaryButton);
        secondaryButton.setVisibility(View.GONE);

        if(registerMBCpageModel.getButtonMap() != null)
        {
            Action primaryAction = registerMBCpageModel.getButtonMap().get(MBC_Constants.PRIMARY_BUTTON);
            if(primaryAction!= null)
            {
               // primaryButton.setText(registerMBCpageModel.getButtonMap().get(MBC_Constants.PRIMARY_BUTTON).getTitle());
                primaryButton.setText("Next");

                primaryButton.setOnClickListener( view ->{


                    if(areValidInput())
                    {

                        getScreenData();
                        Gson gson = new Gson();
                        gson.excluder().excludeFieldsWithoutExposeAnnotation();

                        String gsonString =gson.toJson(registerMBCDataModel);
                        Log.d("@MBC","JSON" + gsonString );


                        if(registerMBCDataModel.getNoOfShares() > 1) {
                            Log.d("@MBC", "no of shares" + registerMBCDataModel.getNoOfShares() );
                            registerMBCDataModel.setFirstParticipantSeqNo(0);
                           AppFragmentUtils.loadRegisterParticipantMBCFragment(registerMBCPageResponseModel, MBC_Constants.REGISTER_PARTICIPANT_MBC);
                          //  AppFragmentUtils.loadAmendParticipantFragment(registerMBCPageResponseModel);
                        }
                        else
                        AppFragmentUtils.loadRegisterMBCPaymentFragment(registerMBCPageResponseModel);

                    }


                });

            }

        }
        else
            primaryButton.setVisibility(View.GONE);

    }

    void getScreenData()
    {

        registerMBCDataModel.setDoingBusinessAs(doingBusinessAs.getText().toString());
        registerMBCDataModel.getPrincipalShareholder().setProfessionalDesignation(professionalDesignation.getText().toString());

        registerMBCDataModel.getPrincipalShareholder().setFirstName(principalFirstName.getText().toString());

        if(firstNameOnlyCheckBox.isChecked())
        {
            registerMBCDataModel.getPrincipalShareholder().setFirstNameOnlyCheck(true);
            registerMBCDataModel.getPrincipalShareholder().setIsFirstNameOnly("Y");
            registerMBCDataModel.getPrincipalShareholder().setMiddleName(null);
            registerMBCDataModel.getPrincipalShareholder().setLastName(null);


        }
        else {
            registerMBCDataModel.getPrincipalShareholder().setFirstNameOnlyCheck(false);
            registerMBCDataModel.getPrincipalShareholder().setIsFirstNameOnly("N");

            registerMBCDataModel.getPrincipalShareholder().setMiddleName(principalMiddleName.getText().toString());
            registerMBCDataModel.getPrincipalShareholder().setLastName(principalLastName.getText().toString());

        }


    }
    boolean areValidInput()
    {
        Log.d("@MBC", "areValidInput");
         boolean isValid=true;

            if(!(mbcRadioButton.isChecked() || mbcCompanyRadioButton.isChecked()) ) {
                isValid = false;


            }

             if(countriesSpinner.getPositionForView(countriesSpinner.getSelectedView()) == 0)
             {
                 isValid=false;
                TextView selectedView= (TextView) countriesSpinner.getSelectedView();
                selectedView.setText(registerMBCModel.getCountries().get(0).getMbcCountryOfOperation());
                selectedView.setTextColor(this.getContext().getApplicationContext().getResources().getColor(R.color.red));


             }

             if(!ValidationUtils.isValidName(doingBusinessAs.getText().toString())) {
                 isValid = false;
                 doingBusinessAs.setError("Invalid characters  ");

             }

             if(!ValidationUtils.isValidProfessionalDesignation(professionalDesignation.getText().toString()))
             {
                 isValid=false;
                 professionalDesignation.setError("MR. Mrs. Ms. etc");

             }

             if(!ValidationUtils.isValidName(principalFirstName.getText().toString()))
             {
                 isValid=false;
                 principalFirstName.setError("Invalid First Name");

             }


             if(principalMiddleName.isEnabled() )
             {
                 if(!ValidationUtils.isValidName(principalMiddleName.getText().toString()))
                 {
                     isValid=true;
                     principalMiddleName.setError("Invalid Middle Name");

                 }


             }


                 if (principalLastName.isEnabled()) {

                     if (!ValidationUtils.isValidName(principalLastName.getText().toString())) {
                         isValid = false;
                         principalLastName.setError("Invalid Last Name");
                     }


                 }


             if(nationalitiesSpinner.getPositionForView(nationalitiesSpinner.getSelectedView())== 0)
             {
                 isValid=false;

                 TextView selectedView =(TextView) nationalitiesSpinner.getSelectedView();
                 selectedView.setTextColor(this.getContext().getApplicationContext().getResources().getColor(R.color.red));
                 selectedView.setText(registerMBCModel.getNationalities().get(0).getNationality());

             }


             if(noOfSharesSpinner.getPositionForView(noOfSharesSpinner.getSelectedView()) == 0)
             {
                 isValid=false;

                 TextView selectedView = (TextView) noOfSharesSpinner.getSelectedView();
                 selectedView.setTextColor(this.getContext().getApplicationContext().getResources().getColor(R.color.red));
                 selectedView.setText(noOfSharesList[0]);

             }
             if(businessPurposeSpinner.getPositionForView(businessPurposeSpinner.getSelectedView()) == 0)
             {
                 isValid=false;
                 TextView selectedView = (TextView) businessPurposeSpinner.getSelectedView();
                 selectedView.setTextColor(this.getContext().getApplicationContext().getResources().getColor(R.color.red));
                 selectedView.setText(registerMBCModel.getBusinessPurposeList().get(0).getBusinessPurpose());


             }

             if(transfeeTerms.getVisibility() == View.VISIBLE)
             {
                 if(!checkBoxTerms.isChecked())
                    isValid=false;



             }




        return isValid;





    }

    void setUpCountriesSpinner(View rootView)
    {
        Log.d("@MBC","setUpCountriesSpinner");
         countriesSpinner = rootView.findViewById(R.id.countries_spinner);

        SpinnerAdapter countriesAdapter = new CountriesSpinnerAdapter(this.getContext(),registerMBCModel.getCountries());
        countriesSpinner.setAdapter(countriesAdapter);

        countriesSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if(countriesSpinnerPrevPosition != position)
                {
                    isPrincipalDataChanged=true;
                }
                if(position > 0) {
                    registerMBCDataModel.setCountryOfOperation(registerMBCModel.getCountries().get(position));
                    countryCode.setText(registerMBCModel.getCountries().get(position).getAlpha3Code());

                }

                countriesSpinnerPrevPosition=position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }

    void setUpNationalitiesSpinner(View rootView)
    {
        Log.d("@MBC","setUpNationalitiesSpinner");
            nationalitiesSpinner= rootView.findViewById(R.id.nationalities_spinner);
            SpinnerAdapter nationalitiesAdapter = new NationalitiesSpinnerAdapter(this.getContext(),registerMBCModel.getNationalities());
            nationalitiesSpinner.setAdapter(nationalitiesAdapter);
            nationalitiesSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    if(nationalitiesSpinnerPrevPosition != position)
                    {
                        isPrincipalDataChanged=true;
                    }
                    if(position > 0) {
                        registerMBCDataModel.getPrincipalShareholder().setNationality(registerMBCModel.getNationalities().get(position));
                        principalNationalityCode.setText(registerMBCModel.getNationalities().get(position).getAlpha3Code());

                    }

                    nationalitiesSpinnerPrevPosition=position;


                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

    }

    void setUpBusinessPurposeSpinner(View rootView)
    {
        Log.d("@MBC", "setUpBusinessPurposeSpinner");
        businessPurposeSpinner = rootView.findViewById(R.id.business_purpose_spinner);

        SpinnerAdapter businessPurposeAdapter = new BusinessPurposeListSpinnerAdapter(this.getContext(),registerMBCModel.getBusinessPurposeList());
        businessPurposeSpinner.setAdapter(businessPurposeAdapter);
        businessPurposeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(businessPurposeSpinnerPrevPosition != position)
                {
                    isPrincipalDataChanged=true;
                }
                if(position > 0)
                registerMBCDataModel.setBusinessPurpose(registerMBCModel.getBusinessPurposeList().get(position));

                businessPurposeSpinnerPrevPosition=position;

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }

    void setUpNoOfSharesSpinner(View rootView)

    {

        Log.d("@MBC","setUpNoOfSharesSpinner");
        noOfSharesSpinner= rootView.findViewById(R.id.mbc_no_shares_spinner);

        SpinnerAdapter noOfSharesAdapter = new ArrayAdapter<>(this.getContext(),android.R.layout.simple_spinner_item,android.R.id.text1,noOfSharesList);

        noOfSharesSpinner.setAdapter(noOfSharesAdapter);
        noOfSharesSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int noOfSharesSelected = Integer.valueOf(noOfSharesList[position]);
                if(position >0) {

                    Log.d("@MBC", "No of Shares " + noOfSharesSelected );
                    registerMBCDataModel.setNoOfShares(noOfSharesSelected);

                    if(noOfSharesSelected == 1)
                    {

                        transfeeTerms.setVisibility(View.VISIBLE);

                    }
                    else
                        transfeeTerms.setVisibility(View.GONE);

                    if(registerMBCDataModel.getParticipantShareholders() != null)
                    {
                        int  noParticipantShareHoldersPrevSelected=registerMBCDataModel.getParticipantShareholders().size();
                        Log.d("@MBC", "No of prev participants loaded " + registerMBCDataModel.getParticipantShareholders().size());
                        int currentNoOffParticipantShareholders=noOfSharesSelected-1;

                        if(currentNoOffParticipantShareholders < noParticipantShareHoldersPrevSelected )
                        {
                                  int prevselectindex = noParticipantShareHoldersPrevSelected-1;
                            for(int index=prevselectindex;index>=currentNoOffParticipantShareholders;index--)
                            {
                                Log.d("@MBC", "removed index " + index + "   " + registerMBCDataModel.getParticipantShareholders().get(index).getFirstName());
                                registerMBCDataModel.getParticipantShareholders().remove(index);

                            }

                        }


                    }

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }



    @Override
    public void onClick(View view) {
        boolean checked=((RadioButton) view).isChecked();

        if (view.getId() == R.id.mbc_radioButton) {
            if(checked)
                registerMBCDataModel.setNamePrefix(mbcRadioButton.getText().toString());
        }
        else
        if(view.getId() == R.id.mbc_company_radio_button)
        {
            if(checked)
                registerMBCDataModel.setNamePrefix(mbcCompanyRadioButton.getText().toString());

        }

    }



    void dataObserver()
    {



        if(!registerMBCDataModel.getDoingBusinessAs().equals(doingBusinessAs.getText().toString())) {
            isPrincipalDataChanged=true;


        }
        else



        if(!registerMBCDataModel.getPrincipalShareholder().getFirstName().equals(principalFirstName.getText().toString()))
        {

                isPrincipalDataChanged=true;

        }
        else
        if(principalMiddleName.isEnabled() )
        {
            if(registerMBCDataModel.getPrincipalShareholder().getMiddleName()!= null) {
                if (!registerMBCDataModel.getPrincipalShareholder().getMiddleName().equals(principalMiddleName.getText().toString())) {

                        isPrincipalDataChanged = true;

                }

            }
            else
                isPrincipalDataChanged = true;

        }
        else
        if(principalLastName.isEnabled())
        {
            if(registerMBCDataModel.getPrincipalShareholder().getLastName() != null)
            {
            if(!registerMBCDataModel.getPrincipalShareholder().getLastName().equals(principalLastName.getText().toString()))
            {

                    isPrincipalDataChanged=true;

            }
            }
            else
                isPrincipalDataChanged=true;


        }



    }
}
