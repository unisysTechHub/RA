package com.bvifsc.mbc.fragments;


import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.bvifsc.core.R;
import com.bvifsc.core.di.BVIRA_Application;
import com.bvifsc.core.fragments.BaseFragment;
import com.bvifsc.core.models.response.Action;
import com.bvifsc.core.models.response.PageResponseModel;
import com.bvifsc.mbc.Common.MBC_Constants;
import com.bvifsc.mbc.Common.ValidationUtils;
import com.bvifsc.mbc.model.SignUpPageResponseModel;
import com.bvifsc.mbc.datamodel.SignUp;
import com.bvifsc.mbc.model.signUp.SecurityQuestionsModel;
import com.bvifsc.mbc.presenters.SignUpPresenter;

import javax.inject.Inject;


public class MBC_SignUpFragment extends BaseFragment implements TextWatcher {

    PageResponseModel signUpPageModel;
    SecurityQuestionsModel securityQuestionsModel;
    SignUp signUpDataModel;
    TextView signUpHeader;
    TextInputEditText userName;
    TextInputEditText password;
    TextInputEditText  firstName;
    TextInputEditText middleName;
    TextInputEditText lastName;
    TextInputEditText email;
    TextInputEditText city;
    TextInputEditText phoneNumber;
    TextInputEditText fax;
    TextInputEditText streetAddress;
    TextInputEditText zipCode;
    TextInputEditText securityAnswer;
    Spinner securityQsSpinner;
    Button signUp;
    Button cancel;
    
    @Inject
    SignUpPresenter signUpPresenter;


    public MBC_SignUpFragment() {
        // Required empty public constructor
    }



    public static MBC_SignUpFragment newInstance(SignUpPageResponseModel signUpPageResponseModel) {
        MBC_SignUpFragment fragment = new MBC_SignUpFragment();
        Bundle args = new Bundle();
        args.putParcelable(MBC_Constants.signUp, signUpPageResponseModel);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected void loadFragmentArguments() {
        SignUpPageResponseModel signUpPageResponseModel=getArguments().getParcelable(MBC_Constants.signUp);

        if(signUpPageResponseModel != null)
        {
            signUpPageModel = signUpPageResponseModel.getPageInfo();
            securityQuestionsModel=signUpPageResponseModel.getSecurityQuestionsModel();
        }

    }

    @Override
    protected void injectFragment() {
        BVIRA_Application.getObjectGraph(getContext().getApplicationContext()).inject(this);
            }

    @Override
    protected int getLayout() {
        return R.layout.fragment_mbc__sign_up;
    }

    @Override
    protected void initFragment(View rootView) {

        setUpPageTitleAndHeaders(rootView);
        setUpSecurityQuestionsSpinner(rootView);
        setUpToValidateUserInputData(rootView);
        setUpSignUpPageActions(rootView);

    }



    void setUpPageTitleAndHeaders(View rootView)
    {
        signUpHeader=rootView.findViewById(R.id.sign_up_header);
        signUpHeader.setText(signUpPageModel.getScreenHeading());


    }


    void setUpToValidateUserInputData(View rootView)
    {
        userName =rootView.findViewById(R.id.userId);
        password=rootView.findViewById(R.id.password);
        password.addTextChangedListener(this);

        firstName=rootView.findViewById(R.id.firstName);
        middleName=rootView.findViewById(R.id.middleName);
        lastName=rootView.findViewById(R.id.lastName);
        email=rootView.findViewById(R.id.email);
        city=rootView.findViewById(R.id.city);
        phoneNumber=rootView.findViewById(R.id.phone_number);
        fax=rootView.findViewById(R.id.fax);
        streetAddress=rootView.findViewById(R.id.streetAddress);
        zipCode=rootView.findViewById(R.id.zipCode);
        securityAnswer=rootView.findViewById(R.id.security_Q_answer);

    }

    void setUpSecurityQuestionsSpinner(View rootView)
    {
        securityQsSpinner=rootView.findViewById(R.id.securityQsSpinner);
        ArrayAdapter<String> questionsSpinnerAdapter= new ArrayAdapter<>(getContext().getApplicationContext(),android.R.layout.simple_spinner_item,securityQuestionsModel.getSecurityQuestionList());
        questionsSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        securityQsSpinner.setAdapter(questionsSpinnerAdapter);
        securityQsSpinner.setPrompt("Select Security Question");
        signUpDataModel= new SignUp();

        securityQsSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
              Log.d("@BVIRA", "selected question" + securityQuestionsModel.getSecurityQuestionList().indexOf(((TextView)view).getText()));
                signUpDataModel.setSelectedSecurityQId(position);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    void setUpSignUpPageActions(View rootView)
    {
            signUp = rootView.findViewById(R.id.primaryButton);
            cancel=rootView.findViewById(R.id.secondaryButton);

            if(signUpPageModel.getButtonMap() != null)
            {
                if(signUpPageModel.getButtonMap().get(MBC_Constants.PRIMARY_BUTTON) != null)
                {
                    Action primaryAction = signUpPageModel.getButtonMap().get(MBC_Constants.PRIMARY_BUTTON);

                    signUp.setText(primaryAction.getTitle());

                    signUp.setOnClickListener( view -> {

                            if(areValidInput())
                            {
                                getInputData();

                                signUpPresenter.signUp(signUpDataModel,primaryAction);

                            }
                    } );


                }
                else
                    signUp.setVisibility(View.GONE);

                if(signUpPageModel.getButtonMap().get(MBC_Constants.SECONDARY_BUTTON) != null)
                {

                    Action secondaryAction = signUpPageModel.getButtonMap().get(MBC_Constants.SECONDARY_BUTTON);

                    cancel.setText(secondaryAction.getTitle());

                    cancel.setOnClickListener( view -> {
                        onBackPressed();

                    } );

                }


            }
            else {
                signUp.setVisibility(View.GONE);
                cancel.setVisibility(View.GONE);

            }


    }

    void getInputData()
    {
        signUpDataModel.setUserName(userName.getText().toString());
        signUpDataModel.setPassword(password.getText().toString());
        signUpDataModel.setFirstName(firstName.getText().toString());
        signUpDataModel.setMiddleName(middleName.getText().toString());
        signUpDataModel.setLastName(lastName.getText().toString());
        signUpDataModel.setEmail(email.getText().toString());
        signUpDataModel.setCity(city.getText().toString());
        signUpDataModel.setPhoneNumber(phoneNumber.getText().toString());
        signUpDataModel.setFax(fax.getText().toString());
        signUpDataModel.setStreetAddress(streetAddress.getText().toString());
        signUpDataModel.setZipCode(zipCode.getText().toString());
        signUpDataModel.setSecurityAnswer(securityAnswer.getText().toString());


    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        validatePassword();

    }


        void validatePassword()
    {

        if(!ValidationUtils.isValidPassword(password.getText().toString()))
        {
            password.setError("Password must be \n  Alphanumeric \n at least one capital letter \n one special character ");


        }


    }

    boolean areValidInput() {

        boolean isValid = true;

        if(!ValidationUtils.isValidUserName(userName.getText().toString())) {
            userName.setError("Invalid User Name");
            isValid=false;

        }

        if(!ValidationUtils.isValidPassword(password.getText().toString()))
        {
            password.setError("Invalid Password");
            isValid=false;

        }


        if(!ValidationUtils.isValidName(firstName.getText().toString()))
        {
            firstName.setError("Invalid First Name");
            isValid=false;

        }

        if(!ValidationUtils.isValidName(middleName.getText().toString()))
        {
            middleName.setError("Invalid Middle Name");
            isValid=false;

        }
        if(!ValidationUtils.isValidName(lastName.getText().toString()))
        {
            lastName.setError("Invalid Last Name");
            isValid=false;

        }

        if(!ValidationUtils.isValidEmail(email.getText().toString()))
        {
            email.setError("Invalid Email id ");
            isValid=false;

        }

        if(!ValidationUtils.isValidCityName(city.getText().toString()))
        {
            city.setError("Invalid City Name");
            isValid=false;

        }

        if(!ValidationUtils.isValidPhoneNumber(phoneNumber.getText().toString()))
        {
            phoneNumber.setError("Invalid Phone Number");
            isValid=false;

        }



        if(TextUtils.isEmpty(streetAddress.getText().toString()))
        {
            streetAddress.setError("Invalid Address");
            isValid=false;
        }


        if(!ValidationUtils.isValidZipCode(zipCode.getText().toString()))
        {
            zipCode.setError("Invalid Zip Code");
            isValid=false;

        }

        if(!ValidationUtils.isValidSecurityAnswer(securityAnswer.getText().toString()))
        {
            securityAnswer.setError("Invalid Security Answer");
            isValid=false;

        }

        if(( securityQsSpinner.getPositionForView(securityQsSpinner.getSelectedView())== 0))
        {
           //   if(isValid)
         //       securityQsSpinner.performClick();
            TextView selectedView =(TextView) securityQsSpinner.getSelectedView();
            selectedView.setError(securityQuestionsModel.getSecurityQuestionList().get(0));
            selectedView.setTextColor(getResources().getColor(R.color.red));

            isValid=false;

        }

        return isValid;

    }


    public void onBackPressed() {

            this.getActivity().getSupportFragmentManager().popBackStackImmediate();


    }


}
