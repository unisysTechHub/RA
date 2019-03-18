package com.bvifsc.mbc.fragments;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import com.bvifsc.core.R;
import com.bvifsc.core.di.BVIRA_Application;
import com.bvifsc.core.fragments.BaseFragment;
import com.bvifsc.core.models.response.Action;
import com.bvifsc.core.models.response.PageResponseModel;
import com.bvifsc.core.presenters.BasePresenter;
import com.bvifsc.mbc.Common.AppFragmentUtils;
import com.bvifsc.mbc.Common.MBC_Constants;
import com.bvifsc.mbc.Common.ValidationUtils;
import com.bvifsc.mbc.adapters.NationalitiesSpinnerAdapter;
import com.bvifsc.mbc.adapters.ParticipantRightsSpinnerAdapter;
import com.bvifsc.mbc.datamodel.ParticipantMBC;
import com.bvifsc.mbc.datamodel.ParticipantShareholder;
import com.bvifsc.mbc.datamodel.MBCData;
import com.bvifsc.mbc.model.RegisterMBCPageResponseModel;
import com.bvifsc.mbc.model.registerMBC.Nationality;
import com.bvifsc.mbc.model.registerMBC.ParticipantRights;
import com.bvifsc.mbc.model.registerMBC.RegisterMBCModel;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class RegisterParticipantMBC extends BaseFragment {

    public int MAX_PARTICIPANTS_PER_PAGE = 3;
    public int second_page_start_seq=3;

    MBCData registerMBCDataModel;
    RegisterMBCModel registerMBCModel;
    PageResponseModel registerMBCpageModel;
    RegisterMBCPageResponseModel registerMBCPageResponseModel;
    int firstParticipantSeqNo;
    int participantSeqNo;
    LinearLayout participantPlaceHolder;
    int noOfParticipantsAdded;
    int participantsInCurrentPage=1;
    LinearLayout participantLinearLayout;
    List<ParticipantMBC> participantLayoutsRef;

    TextView pageHeader;
    TextView transactionFee;
    TextView transactionFeeHeader;
    CheckBox checkBoxTerms;
    TextView mbcalegislativeref;
    boolean isFirstTimeAddParticipant=true;
    int first_page_last_seq_no=2;


    @Inject
    BasePresenter basePresenter;

    boolean isOnBackStack=false;
    boolean fromLoadParticipantData=false;
    boolean onViewCreated;

    public RegisterParticipantMBC() {
        // Required empty public constructor
    }


    public static RegisterParticipantMBC newInstance(RegisterMBCPageResponseModel registerMBCPageResponseModel) {
        RegisterParticipantMBC fragment = new RegisterParticipantMBC();
        Bundle args = new Bundle();
        args.putParcelable(MBC_Constants.REGISTER_PARTICIPANT_MBC, registerMBCPageResponseModel);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void injectFragment() {
        BVIRA_Application.getObjectGraph(BVIRA_Application.context).inject(this);

            }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            registerMBCPageResponseModel = getArguments().getParcelable(MBC_Constants.REGISTER_PARTICIPANT_MBC);
            registerMBCModel=registerMBCPageResponseModel.getRegisterMBCModel();
            registerMBCpageModel=registerMBCPageResponseModel.getPageInfo();
            registerMBCDataModel=registerMBCPageResponseModel.getRegisterMBCDataModel();
        }
    }


    @Override
    protected int getLayout() {
        return R.layout.fragment_participant_mbc;
    }



    @Override
    protected void initFragment(View rootView) {
        Log.d("@MBC","initFragment");

        Gson gson = new Gson();
        gson.excluder().excludeFieldsWithoutExposeAnnotation();
        String gsonString =gson.toJson(registerMBCDataModel);
        Log.d("@MBC", "RegisterMBCData" + gsonString);


        setUpHeaderAndTitle(rootView);
        setUpToLoadPreviouslyEnteredParticipantsData();
        Log.d("@MBC", "current page count " + participantsInCurrentPage);
        if(participantsInCurrentPage < MAX_PARTICIPANTS_PER_PAGE ) {
            setUpToInputParticipantMBCData();
        }
        setUpToShowTransactionFeeAndTerms(rootView);
        setUpRegisterMBCPageActions(rootView);
        isOnBackStack=true;






    }


    void setUpHeaderAndTitle(View rootView)
    {
        Log.d("@MBC","setUpHeaderAndTitle");
                    pageHeader=rootView.findViewById(R.id.register_participant_MBC_header);
                    pageHeader.setText(registerMBCpageModel.getTitle() + "...cnt");

        participantPlaceHolder = rootView.findViewById(R.id.participant_place_holder);


    }

   public void setMaxParticipantsPerPage(int maxParticpanterPerPage)
    {
        MAX_PARTICIPANTS_PER_PAGE=maxParticpanterPerPage;


    }

    void setUpToLoadPreviouslyEnteredParticipantsData()
    {
        Log.d("@MBC","setUpToLoadPreviouslyEnteredParticipantsData");
        participantsInCurrentPage=1;
        firstParticipantSeqNo=registerMBCDataModel.getFirstParticipantSeqNo();



        participantLayoutsRef= new ArrayList<>();
        Log.d("@MBC", "first participant seq no " + firstParticipantSeqNo);
        Log.d("@MBC","No of participants loaded " + registerMBCDataModel.getParticipantShareholders().size());

        participantSeqNo=firstParticipantSeqNo;

        if(registerMBCDataModel.getParticipantShareholders() != null )
        {


            int noOfParticipantsDataProvided=registerMBCDataModel.getParticipantShareholders().size();

            if(noOfParticipantsDataProvided > firstParticipantSeqNo)
            {
                fromLoadParticipantData=true;
                loadParticipantData();
            }



        }
        else
            registerMBCDataModel.setParticipantShareholders(new ArrayList<>());


    }


    void setUpRegisterMBCPageActions(View rootView)
    {
        Log.d("@MBC", "setUpRegisterMBCPageActions");
        Button primaryButton=rootView.findViewById(R.id.primaryButton);
        primaryButton.setEnabled(true);


        Button secondaryButton= rootView.findViewById(R.id.secondaryButton);


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
                        noOfParticipantsAdded=registerMBCDataModel.getParticipantShareholders().size();
                        Log.d("@MBC", "noOfParticipantsAdded :" + noOfParticipantsAdded );

                        if(firstParticipantSeqNo == 0 && (participantSeqNo < registerMBCDataModel.getNoOfShares()-2) )
                        {
                            Log.d("@MBC", "Remaining participants continue in next Page");
                            registerMBCDataModel.setFirstParticipantSeqNo(participantSeqNo+1);

                            registerMBCPageResponseModel.setRegisterMBCDataModel(registerMBCDataModel);

                            AppFragmentUtils.loadRegisterParticipantMBCFragment(registerMBCPageResponseModel,MBC_Constants.PARTICPANT_MBC_SECOND_PAGE);

                        }
                        else
                            {
                           // load payment summary
                            AppFragmentUtils.loadRegisterMBCPaymentFragment(registerMBCPageResponseModel);

                        }




                    }


                });

            }

            Action secondaryAction   = registerMBCpageModel.getButtonMap().get(MBC_Constants.SECONDARY_BUTTON);

            if(secondaryAction!= null)
            {
                secondaryButton.setText("Back");

                secondaryButton.setOnClickListener( view -> {


                    onBackPressed();


                });

            }



        }
        else {
            primaryButton.setVisibility(View.GONE);
            secondaryButton.setVisibility(View.GONE);
        }






    }

    void setFirstParticipantSeqNo()
    {
        registerMBCDataModel.setFirstParticipantSeqNo(participantSeqNo+1);

        registerMBCPageResponseModel.setRegisterMBCDataModel(registerMBCDataModel);

    }

    public boolean isNextPaymentScreen()
    {
        boolean isNextPaymentScreen=false;


            if(firstParticipantSeqNo == 0 && (participantSeqNo < registerMBCDataModel.getNoOfShares()-2) )
            {
                Log.d("@MBC", "Remaining participants continue in next Page");
                registerMBCDataModel.setFirstParticipantSeqNo(participantSeqNo+1);

                registerMBCPageResponseModel.setRegisterMBCDataModel(registerMBCDataModel);


            }
            else
            {
                // load payment summary
                isNextPaymentScreen=true;

            }





        return isNextPaymentScreen;

    }


    void setUpToShowTransactionFeeAndTerms(View rootView)
    {

        Log.d("@MBC", "setUpToShowTransactionFeeAndTerms");

        transactionFeeHeader=rootView.findViewById(R.id.transaction_fee_header);
        transactionFee = rootView.findViewById(R.id.transaction_fee);
        checkBoxTerms=rootView.findViewById(R.id.checkBox_terms);
        mbcalegislativeref=rootView.findViewById(R.id.mbca_legislative_ref);

        if(participantSeqNo == (registerMBCDataModel.getNoOfShares()-2)) {
            transactionFee.setVisibility(View.VISIBLE);
            checkBoxTerms.setVisibility(View.VISIBLE);
            mbcalegislativeref.setVisibility(View.VISIBLE);
            transactionFeeHeader.setVisibility(View.VISIBLE);

            transactionFee.setText(String.valueOf(registerMBCModel.getPaymentSummary().getTransactionFee()));
            checkBoxTerms.setOnCheckedChangeListener((view, isChecked) ->   registerMBCDataModel.setCheckBoxTerms(isChecked) );

        }
        else
        {
            transactionFee.setVisibility(View.GONE);
            checkBoxTerms.setVisibility(View.GONE);
            mbcalegislativeref.setVisibility(View.GONE);
            transactionFeeHeader.setVisibility(View.GONE);

        }




    }


    void loadParticipantData()
    {
        Log.d("@MBC", "loadParticipantData");
        int index;


        for( index=participantSeqNo;index< registerMBCDataModel.getParticipantShareholders().size();index++)
        {

            if(index == participantSeqNo) {



                addViewAndLoadData(index);
            }
            else
            {
                participantsInCurrentPage=participantsInCurrentPage+1;
                Log.d("@MBC", " current page count" + participantsInCurrentPage);
                addViewAndLoadData(index);

            }


            if(participantsInCurrentPage == MAX_PARTICIPANTS_PER_PAGE) {
                Log.d("@MBC", "for loop break");
                participantSeqNo=index;
                break;
            }

        }


        if(participantsInCurrentPage != MAX_PARTICIPANTS_PER_PAGE)
        participantSeqNo=index-1;
        if(index < 0)
            participantSeqNo=0;

        Log.d("@MBC","index participant seq " + participantSeqNo);


    }

    void addViewAndLoadData(int participantSeqNo)
    {
        Log.d("@MBC", "addViewAndLoadData " + participantSeqNo);
        ParticipantMBC participantMBC = inflateParticipantLayout(participantSeqNo);
        Log.d("@MBC", "Data set On screen" + registerMBCDataModel.getParticipantShareholders().get(participantSeqNo).getFirstName() );
        participantMBC.professionalDesignation.setText(registerMBCDataModel.getParticipantShareholders().get(participantSeqNo).getDesignation());
        participantMBC.firstName.setText(registerMBCDataModel.getParticipantShareholders().get(participantSeqNo).getFirstName());
        participantMBC.lastName.setText(registerMBCDataModel.getParticipantShareholders().get(participantSeqNo).getLastName());
        participantMBC.middleName.setText(registerMBCDataModel.getParticipantShareholders().get(participantSeqNo).getMiddleName());
        participantMBC.nationalitiesSpinner.setSelection(
                getNationalitiesSpinnerPosition(registerMBCDataModel.getParticipantShareholders().get(participantSeqNo).getNationality()));

        participantMBC.participantRights.setSelection(
                getParticipantRightsSpinnerPosition(registerMBCDataModel.getParticipantShareholders().get(participantSeqNo).getParticipantRights()));

        if(registerMBCDataModel.getParticipantShareholders().get(participantSeqNo).getIsFirstNameOnly().equals("Y")) {
            participantMBC.isFirstNameOnlyCheckBox.setChecked(true);

        }
        else {
            participantMBC.isFirstNameOnlyCheckBox.setChecked(false);
        }

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

    int getParticipantRightsSpinnerPosition(ParticipantRights mbcparticipantRights)
    {
        for(ParticipantRights participantRights : registerMBCModel.getParticipantRightsList())
        {
            if(registerMBCModel.getParticipantRightsList().indexOf(participantRights) > 0) {
                if (participantRights.getParticipantRightsId() == mbcparticipantRights.getParticipantRightsId()) {
                    return registerMBCModel.getParticipantRightsList().indexOf(participantRights);


                }
            }

        }
        return -1;
    }
    ParticipantMBC inflateParticipantLayout(int participantSeqNo)
    {
        Log.d("@MBC", "inflateParticipantLayout");


         participantLinearLayout=(LinearLayout) LayoutInflater.from(this.getContext()).inflate(R.layout.ra_register_mbc_participant_shareholder,null);

        ParticipantMBC participantMBC= new ParticipantMBC();
        participantMBC.participantHeader=participantLinearLayout.findViewById(R.id.participant_share_holder_header);
        participantMBC.participantHeader.setId(View.generateViewId());
        participantMBC.participantHeader.setText(MBC_Constants.PARTICIPANT_SHARE_HOLDER_HEADER + " ");

        participantMBC.participantNo=participantLinearLayout.findViewById(R.id.participant_no);
        participantMBC.participantNo.setId(View.generateViewId());
        participantMBC.participantNo.setText(String.valueOf(participantSeqNo+1));
        participantMBC.participantNo.setTag(participantSeqNo);



        participantMBC.professionalDesignation= participantLinearLayout.findViewById(R.id.professional_designation);
        participantMBC.professionalDesignation.setId(View.generateViewId());

        participantMBC.firstName=participantLinearLayout.findViewById(R.id.participant_first_name);
        participantMBC.firstName.setId(View.generateViewId());

        participantMBC.lastName=participantLinearLayout.findViewById(R.id.participant_last_name);
        participantMBC.lastName.setId(View.generateViewId());

        participantMBC.middleName=participantLinearLayout.findViewById(R.id.participant_middle_name);
        participantMBC.middleName.setId(View.generateViewId());

        participantMBC.nationalitiesSpinner=participantLinearLayout.findViewById(R.id.nationalities_spinner);
        participantMBC.nationalitiesSpinner.setId(View.generateViewId());
        setUpNationalitiesSpinner(participantMBC,participantSeqNo);

        participantMBC.nationalityCode=participantLinearLayout.findViewById(R.id.participant_nationality_code);
        participantMBC.nationalityCode.setId(View.generateViewId());

        participantMBC.participantRights=participantLinearLayout.findViewById(R.id.participant_rights);
        participantMBC.participantRights.setId(View.generateViewId());
        setUpParticipantRightsSpinner(participantMBC,participantSeqNo);

        participantMBC.isFirstNameOnlyCheckBox=participantLinearLayout.findViewById(R.id.fist_name_only_check_box);
        participantMBC.isFirstNameOnlyCheckBox.setId(View.generateViewId());
        participantMBC.isFirstNameOnlyCheckBox.setTag(participantSeqNo);
        participantMBC.isFirstNameOnlyCheckBox.setOnCheckedChangeListener( (view, isChecked) ->{
            if(isChecked)
            {
                Log.d("@MBC", "isFirstName only Checked Register participant");
                participantMBC.middleName.setEnabled(false);
                participantMBC.lastName.setEnabled(false);

            }
            else {
                participantMBC.middleName.setEnabled(true);
                participantMBC.lastName.setEnabled(true);

            }

        });



        participantLayoutsRef.add(participantMBC);


        participantPlaceHolder.addView(participantLinearLayout);



        return participantMBC;

    }

    void setUpNationalitiesSpinner(ParticipantMBC participantMBC, int participantSeqNo)
    {

        Log.d("@MBC","setUpNationalitiesSpinner" );

        SpinnerAdapter nationalitiesAdapter = new NationalitiesSpinnerAdapter(this.getContext(),registerMBCModel.getNationalities());
        participantMBC.nationalitiesSpinner.setAdapter(nationalitiesAdapter);


        participantMBC.nationalitiesSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if(position > 0) {

                    registerMBCDataModel.getParticipantShareholders().get(participantSeqNo).setNationality(registerMBCModel.getNationalities().get(position));
                    participantMBC.nationalityCode.setText(registerMBCModel.getNationalities().get(position).getAlpha3Code());

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    void setUpParticipantRightsSpinner(ParticipantMBC participantMBC, int participantSeqNo)
    {
        Log.d("@MBC", "setUpParticipantRightsSpinner");
        SpinnerAdapter participantRightsAdapter= new ParticipantRightsSpinnerAdapter(this.getContext(),registerMBCModel.getParticipantRightsList());
        participantMBC.participantRights.setAdapter(participantRightsAdapter);

        participantMBC.participantRights.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position > 0)
                {
                    registerMBCDataModel.getParticipantShareholders().get(participantSeqNo).setParticipantRights(registerMBCModel.getParticipantRightsList().get(position));

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



    }


    void setUpToInputParticipantMBCData()
    {

        Log.d("@MBC","setUpToInputParticipantMBCData");
        Log.d("@MBC", "Particpant seq no " + participantSeqNo);

        int index;
        if(fromLoadParticipantData )
        {
            participantSeqNo=participantSeqNo+1;

        }
        for(index=participantSeqNo;index<registerMBCDataModel.getNoOfShares()-1;index++)
        {
            Log.d("@MBC", "current page count " + participantsInCurrentPage);
            ParticipantShareholder participantShareholder = new ParticipantShareholder();
            registerMBCDataModel.getParticipantShareholders().add(participantSeqNo,participantShareholder);

                if(index==participantSeqNo)
                {

                inflateParticipantLayout(index);
                    if(participantSeqNo != firstParticipantSeqNo)
                    {
                        participantsInCurrentPage = participantsInCurrentPage + 1;
                    }
                    //initialize spinners position to zero
                 registerMBCDataModel.getParticipantShareholders().get(participantSeqNo).setNationality(registerMBCModel.getNationalities().get(0));
                 registerMBCDataModel.getParticipantShareholders().get(participantSeqNo).setParticipantRights(registerMBCModel.getParticipantRightsList().get(0));
                }
                else {
                    participantsInCurrentPage = participantsInCurrentPage + 1;
                    inflateParticipantLayout(index);
                }

            if(participantsInCurrentPage == MAX_PARTICIPANTS_PER_PAGE) {
                    participantSeqNo=index;
                break;
            }
        }

        if(index == (registerMBCDataModel.getNoOfShares()-1 ))
           participantSeqNo=index-1;
        Log.d("@MBC", "Participant seq no " + participantSeqNo);

    }

     boolean addParticipant()
    {
        Log.d("@MBC", "addParticipant seq no   : " +participantSeqNo );

        boolean isParticipantAdded=false;

            if (participantsInCurrentPage < MAX_PARTICIPANTS_PER_PAGE) {

                participantsInCurrentPage = participantsInCurrentPage + 1;
                participantSeqNo = participantSeqNo + 1;
                isFirstTimeAddParticipant=false;
                ParticipantShareholder participantShareholder = new ParticipantShareholder();
                registerMBCDataModel.getParticipantShareholders().add(participantSeqNo, participantShareholder);

                inflateParticipantLayout(participantSeqNo);
                registerMBCDataModel.getParticipantShareholders().get(participantSeqNo).setNationality(registerMBCModel.getNationalities().get(0));
                registerMBCDataModel.getParticipantShareholders().get(participantSeqNo).setParticipantRights(registerMBCModel.getParticipantRightsList().get(0));

                //participantSeqNo = participantSeqNo + 1;
                isParticipantAdded=true;
            }


        return  isParticipantAdded;

    }

        void removeParticipantLayoutFromMemory(int participantSeqNo)
        {


            participantLayoutsRef.remove(participantSeqNo);
            registerMBCDataModel.getParticipantShareholders().remove(participantSeqNo);

            getScreenData();
            participantLayoutsRef.removeAll(participantLayoutsRef);
            this.participantSeqNo=firstParticipantSeqNo;
            participantsInCurrentPage=0;
           participantPlaceHolder.removeAllViews();
            setUpToLoadPreviouslyEnteredParticipantsData();
            if(registerMBCDataModel.getParticipantShareholders().size() == 0 || participantSeqNo == firstParticipantSeqNo)
                this.participantSeqNo=-1;


        }

        boolean noParticipantsInMemory()
        {
            if(participantLayoutsRef.size() > 0)
            {
                return false;
            }
            else
                return true;

        }

    boolean areValidInput()
    {
        Log.d("@MBC", "areValidInput");
        boolean isValid=true;
        for(int i=0;i<participantLayoutsRef.size();i++) {

            ParticipantMBC participantMBC=participantLayoutsRef.get(i);

            if (participantMBC.participantRights.getPositionForView(participantMBC.participantRights.getSelectedView()) == 0) {
                isValid = false;
                TextView selectedView = (TextView) participantMBC.participantRights.getSelectedView();
                selectedView.setText(registerMBCModel.getParticipantRightsList().get(0).getParticipantRights());
                selectedView.setTextColor(this.getContext().getApplicationContext().getResources().getColor(R.color.red));

            }


            if (!ValidationUtils.isValidProfessionalDesignation(participantMBC.professionalDesignation.getText().toString())) {
                isValid = false;
                participantMBC.professionalDesignation.setError("Invalid  Designation");

            }

            if (!ValidationUtils.isValidName(participantMBC.firstName.getText().toString())) {
                isValid = false;
                participantMBC.firstName.setError("Invalid First Name");

            }

            if (participantMBC.middleName.isEnabled()) {
                if (!ValidationUtils.isValidName(participantMBC.middleName.getText().toString())) {
                    isValid = false;
                    participantMBC.middleName.setError("Invalid Middle Name");

                }


            }

            if (participantMBC.lastName.isEnabled()) {

                if (!ValidationUtils.isValidName(participantMBC.lastName.getText().toString())) {
                    isValid = false;
                    participantMBC.lastName.setError("Invalid Last Name");
                }


            }

            if (participantMBC.nationalitiesSpinner.getPositionForView(participantMBC.nationalitiesSpinner.getSelectedView()) == 0) {
                isValid = false;

                TextView selectedView = (TextView) participantMBC.nationalitiesSpinner.getSelectedView();
                selectedView.setTextColor(this.getContext().getApplicationContext().getResources().getColor(R.color.red));
                selectedView.setText(registerMBCModel.getNationalities().get(0).getNationality());

            }

            if(checkBoxTerms.getVisibility() == View.VISIBLE)
            {
                if(!checkBoxTerms.isChecked())
                    isValid=false;


            }

        }

        return isValid;


    }

    void getScreenData() {

        Log.d("@MBC","getScreenData()" );
        Log.d("@Ramesh", "no of participants loaded" + registerMBCDataModel.getParticipantShareholders().size());
        int participantSeqNo=0;
        for (int i = 0; i < participantLayoutsRef.size(); i++) {

            Log.d("@MBC", "no of Layouts added " + participantLayoutsRef.size());
            ParticipantMBC participantMBC = participantLayoutsRef.get(i);
            participantSeqNo=firstParticipantSeqNo+i;



            ParticipantShareholder participantShareholder = registerMBCDataModel.getParticipantShareholders().get(participantSeqNo);



            int participantRightsPosition = participantMBC.participantRights.getPositionForView(participantMBC.participantRights.getSelectedView());


            participantShareholder.setParticipantRights(registerMBCModel.getParticipantRightsList().get(participantRightsPosition));

            participantShareholder.setDesignation(participantMBC.professionalDesignation.getText().toString());
            participantShareholder.setFirstName(participantMBC.firstName.getText().toString());

            if(participantMBC.isFirstNameOnlyCheckBox.isChecked())
            {
                participantShareholder.setFirstNameOnlyCheck(true);
                participantShareholder.setIsFirstNameOnly("Y");
                participantShareholder.setMiddleName(null);
                participantShareholder.setLastName(null);


            }
            else
            {
                participantShareholder.setFirstNameOnlyCheck(false);
                participantShareholder.setIsFirstNameOnly("N");
                participantShareholder.setMiddleName(participantMBC.middleName.getText().toString());
                participantShareholder.setLastName(participantMBC.lastName.getText().toString());

            }



            int nationalityPosition = participantMBC.nationalitiesSpinner.getPositionForView(participantMBC.nationalitiesSpinner.getSelectedView());

            participantShareholder.setNationality(registerMBCModel.getNationalities().get(nationalityPosition));


              //  registerMBCDataModel.getParticipantShareholders().add(participantSeqNo,participantShareholder);

            Log.d("@MBC", "Get input data " + participantSeqNo + " " + registerMBCDataModel.getParticipantShareholders().get(participantSeqNo).getFirstName() );

        }

        Log.d("@Ramesh", "no of participants loaded " + registerMBCDataModel.getParticipantShareholders().size());

    }


  public   void onBackPressed()
    {
        Log.d("@MBC", "onBackPressed");

        getScreenData();

        if(firstParticipantSeqNo == second_page_start_seq ) {

            registerMBCDataModel.setFirstParticipantSeqNo(0);

        }


            this.getActivity().getSupportFragmentManager().popBackStack();




    }



}
