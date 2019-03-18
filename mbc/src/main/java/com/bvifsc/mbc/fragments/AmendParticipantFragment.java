package com.bvifsc.mbc.fragments;


import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bvifsc.core.R;
import com.bvifsc.core.fragments.BaseFragment;
import com.bvifsc.core.models.response.Action;
import com.bvifsc.core.models.response.PageResponseModel;
import com.bvifsc.mbc.Common.MBC_Constants;
import com.bvifsc.mbc.datamodel.ParticipantMBC;
import com.bvifsc.mbc.datamodel.MBCData;
import com.bvifsc.mbc.model.RegisterMBCPageResponseModel;
import com.bvifsc.mbc.model.registerMBC.RegisterMBCModel;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AmendParticipantFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AmendParticipantFragment extends BaseFragment {

    RegisterParticipantMBC participantMBCFragment;
    MBCData registerMBCDataModel;
    RegisterMBCModel registerMBCModel;
    PageResponseModel registerMBCpageModel;
    RegisterMBCPageResponseModel registerMBCPageResponseModel;
    LinearLayout participantPlaceHolder;
    FloatingActionButton floatingActionButton;
    TextView noOfShares;
    FloatingActionButton addParticipantFloatingButton;
    View fragmetnView;

    public AmendParticipantFragment() {
        // Required empty public constructor
    }


    public static AmendParticipantFragment newInstance(RegisterMBCPageResponseModel registerMBCPageResponseModel) {
        AmendParticipantFragment fragment = new AmendParticipantFragment();
        Bundle args = new Bundle();
        args.putParcelable(MBC_Constants.AMEND_PARTICIPANT, registerMBCPageResponseModel);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            registerMBCPageResponseModel = getArguments().getParcelable(MBC_Constants.AMEND_PARTICIPANT);
            registerMBCModel=registerMBCPageResponseModel.getRegisterMBCModel();
            registerMBCpageModel=registerMBCPageResponseModel.getPageInfo();
            registerMBCDataModel=registerMBCPageResponseModel.getRegisterMBCDataModel();
        }
    }

    @Override
    protected void initFragment(View rootView) {
        setUpAmendParticipantHeaderAndTitle(rootView);
          setUpToDisplayParticipantsToAmend(rootView);
        setUpAmendParticipantsPageActions(rootView);


    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_amend_participant;
    }

    void setUpAmendParticipantHeaderAndTitle(View rootView)
    {
        noOfShares=rootView.findViewById(R.id.no_of_shares);
        noOfShares.setText(String.valueOf(registerMBCDataModel.getNoOfShares()));
        addParticipantFloatingButton=rootView.findViewById(R.id.add_participant_button);

        addParticipantFloatingButton.setOnClickListener( view ->

        {
            participantMBCFragment.participantsInCurrentPage=0;
            addParticipant();

        }


        );



    }

    void showHideAddParticipantButton()
    {
        if(participantMBCFragment.noParticipantsInMemory() )
        {
            addParticipantFloatingButton.show();

        }
        else
            addParticipantFloatingButton.hide();

    }
    void setUpToDisplayParticipantsToAmend(View rootView)
    {


         participantMBCFragment = RegisterParticipantMBC.newInstance(registerMBCPageResponseModel);
         participantMBCFragment.setMaxParticipantsPerPage(3);
         this.getChildFragmentManager().beginTransaction().replace(R.id.amendParticipantFrameLayout,participantMBCFragment).commit();
         //getActivity().ge().beginTransaction().replace(R.id.amendParticipantFrameLayout,participantMBCFragment).commit();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                setUpAmendRemoveParticipantsAction();
                disableParticipantFragmentActions();
                showHideAddParticipantButton();
                enableDisableToEditParticipantData();

            }
        },100);




    }

    void enableDisableToEditParticipantData()
    {
        Log.d("@MBC", "enableDisableToEditParticipantData");
        for(ParticipantMBC participantMBC:participantMBCFragment.participantLayoutsRef) {
            participantMBC.firstName.setEnabled(false);
            participantMBC.professionalDesignation.setEnabled(false);
            participantMBC.isFirstNameOnlyCheckBox.setOnCheckedChangeListener((view, isChecked) -> {
                if (isChecked) {
                    Log.d("@MBC", "isFirstName only Checked amend participant" );
                    participantMBC.middleName.setVisibility(View.GONE);
                    participantMBC.lastName.setVisibility(View.GONE);
                    participantMBC.middleName.setEnabled(false);
                    participantMBC.lastName.setEnabled(false);
                    registerMBCDataModel.getParticipantShareholders().get(Integer.valueOf(view.getTag().toString())).setFirstNameOnlyCheck(true);
                    registerMBCDataModel.getParticipantShareholders().get(Integer.valueOf(view.getTag().toString())).setIsFirstNameOnly("Y");

                } else {
                    participantMBC.middleName.setVisibility(View.VISIBLE);
                    participantMBC.lastName.setVisibility(View.VISIBLE);
                    participantMBC.middleName.setEnabled(true);
                    participantMBC.lastName.setEnabled(true);
                    registerMBCDataModel.getParticipantShareholders().get(Integer.valueOf(view.getTag().toString())).setFirstNameOnlyCheck(false);
                    registerMBCDataModel.getParticipantShareholders().get(Integer.valueOf(view.getTag().toString())).setIsFirstNameOnly("N");

                }

            });

            int participantSeqNo = Integer.valueOf(participantMBC.isFirstNameOnlyCheckBox.getTag().toString());

            if ( registerMBCDataModel.getParticipantShareholders().get(participantSeqNo).getIsFirstNameOnly().equals("Y")) {

                participantMBC.isFirstNameOnlyCheckBox.setChecked(true);
              participantMBC.middleName.setVisibility(View.GONE);
               participantMBC.lastName.setVisibility(View.GONE);


            }


        }
    }
    void disableParticipantFragmentActions()
    {
        View footer=participantMBCFragment.getView().findViewById(R.id.footer);
        View amendParticipantScreenHeader=participantMBCFragment.getView().findViewById(R.id.register_participant_MBC_header);
        amendParticipantScreenHeader.setVisibility(View.GONE);
        footer.setVisibility(View.GONE);


    }

    void setUpAmendParticipantsPageActions(View rootView)
    {

        //setUpAmendRemoveParticipantsAction();
        Button primaryButton=rootView.findViewById(R.id.primaryButton);
        Button secondaryButton= rootView.findViewById(R.id.secondaryButton);



        if(registerMBCpageModel.getButtonMap() != null)
        {
            Action primaryAction = registerMBCpageModel.getButtonMap().get(MBC_Constants.PRIMARY_BUTTON);
            if(primaryAction!= null)
            {
                //primaryButton.setText(registerMBCpageModel.getButtonMap().get(MBC_Constants.PRIMARY_BUTTON).getTitle());
                primaryButton.setText("Next");

                primaryButton.setOnClickListener( view ->{

                    if(participantMBCFragment.areValidInput())
                    {
                        participantMBCFragment.getScreenData();
                        //second page
                        if(participantMBCFragment.firstParticipantSeqNo == participantMBCFragment.second_page_start_seq)
                        {
                            getActivity().getSupportFragmentManager().beginTransaction().addToBackStack(null).
                                    replace(R.id.frameLayout, RegisterMBCPayment.newInstance(registerMBCPageResponseModel)).commit();


                        }
                        else
                            //first page
                        if (participantMBCFragment.firstParticipantSeqNo == 0 && participantMBCFragment.participantSeqNo == participantMBCFragment.first_page_last_seq_no )
                        {
                            participantMBCFragment.setFirstParticipantSeqNo();
                            getActivity().getSupportFragmentManager().beginTransaction().addToBackStack(null)
                                    .replace(R.id.frameLayout, AmendParticipantFragment.newInstance(registerMBCPageResponseModel)).addToBackStack(null).commit();


                        } else {
                            getActivity().getSupportFragmentManager().beginTransaction().addToBackStack(null).
                                    replace(R.id.frameLayout, RegisterMBCPayment.newInstance(registerMBCPageResponseModel)).commit();


                        }

                    }
                });

            }

            Action secondaryAction   = registerMBCpageModel.getButtonMap().get(MBC_Constants.SECONDARY_BUTTON);

            if(secondaryAction!= null)
            {
                secondaryButton.setText("Back");

                secondaryButton.setOnClickListener( view -> {


                    participantMBCFragment.onBackPressed();


                });

            }

        }
        else {
            primaryButton.setVisibility(View.GONE);
            secondaryButton.setVisibility(View.GONE);
        }




    }
    void setUpAmendRemoveParticipantsAction()
    {


         participantPlaceHolder = participantMBCFragment.getView().findViewById(R.id.participant_place_holder);

        addRemoveButtonTOParticipantsLayouts(participantPlaceHolder);




    }
    void setUpToAmendAddParticipantAction(LinearLayout  participantLayout)
    {
        floatingActionButton = new FloatingActionButton(participantLayout.getContext());
        floatingActionButton.setImageResource(R.drawable.plus_sign);
        LinearLayout.LayoutParams layoutParams= new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.gravity= Gravity.RIGHT;
        participantLayout.addView(floatingActionButton,layoutParams);

            floatingActionButton.setOnClickListener( view -> {

                    addParticipant();

            });

    }

    void addParticipant()
    {
        if(registerMBCDataModel.getNoOfShares()+1 <=6) {

            if (participantMBCFragment.addParticipant()) {
                int currentlyAddedParticipantIndex =  participantPlaceHolder.getChildCount()-1;
                addRemoveButtonOnParticipantHeader((LinearLayout) participantPlaceHolder.getChildAt(currentlyAddedParticipantIndex),currentlyAddedParticipantIndex);
                registerMBCDataModel.setNoOfShares(registerMBCDataModel.getNoOfShares()+1);
                noOfShares.setText(String.valueOf(registerMBCDataModel.getNoOfShares()));
                if(floatingActionButton == null)
                    setUpToAmendAddParticipantAction((LinearLayout) participantPlaceHolder.getChildAt(currentlyAddedParticipantIndex));
                else
                moveAddParticipantButtonToEndOfLastParticipant(floatingActionButton);
                showHideAddParticipantButton();

            }

        }

    }

    void addRemoveButtonTOParticipantsLayouts(LinearLayout participantPlaceHolder)
    {
        LinearLayout participantLayout=null;
        for(int index=0;index<participantPlaceHolder.getChildCount();index++)
        {
            participantLayout =(LinearLayout) participantPlaceHolder.getChildAt(index);

                 addRemoveButtonOnParticipantHeader(participantLayout,index);


        }

          if(participantPlaceHolder.getChildCount() > 0)
            setUpToAmendAddParticipantAction(participantLayout);


    }

    void addRemoveButtonOnParticipantHeader(LinearLayout participantLayout, int index)
    {
        RelativeLayout participantHeader =participantLayout.findViewById(R.id.participant_header_layout);
        TextView textView = new TextView(participantLayout.getContext());
        textView.setText("Remove");
        textView.setTextColor(this.getContext().getApplicationContext().getResources().getColor(R.color.red));
        textView.setTypeface(Typeface.DEFAULT_BOLD);
        textView.setTag(index);
        RelativeLayout.LayoutParams layoutParams= new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        participantHeader.addView(textView,layoutParams);
        textView.setOnClickListener( view -> {
            Log.d("@MBC", "Remove View clicked" );

            removeParticipant(view);

        });



    }

    void removeParticipant(View view)
    {

        LinearLayout participantLayout =(LinearLayout) view.getParent().getParent();
        Log.d("@MBC", participantLayout.getTag().toString());
        int participantSeqNo= Integer.valueOf(view.getTag().toString());

        if(participantLayout.indexOfChild(floatingActionButton ) != -1 ) {

            OnRemoveMoveAddParticipantButtonToEndOfPreviousParticipant(floatingActionButton);
        }

        registerMBCDataModel.setNoOfShares(registerMBCDataModel.getNoOfShares()-1);
        noOfShares.setText(String.valueOf(registerMBCDataModel.getNoOfShares()));
        participantPlaceHolder.removeView(participantLayout);
        participantMBCFragment.removeParticipantLayoutFromMemory(participantSeqNo);
        addRemoveButtonTOParticipantsLayouts(participantPlaceHolder);
        showHideAddParticipantButton();



    }

    void moveAddParticipantButtonToEndOfLastParticipant(View addParticipantButton)
    {LinearLayout participantLayout=null;
        if(registerMBCDataModel.getNoOfShares() > 1)
            {
            int prevParticipantIndex = participantPlaceHolder.getChildCount() - 2;
            if(prevParticipantIndex >=0) {
                participantLayout = (LinearLayout) participantPlaceHolder.getChildAt(prevParticipantIndex);
                Log.d("@MBC", "removeView");
                participantLayout.removeView(addParticipantButton);
            }

            int currentParticipantIndex = participantPlaceHolder.getChildCount() - 1;

            if(currentParticipantIndex >= 0) {
                Log.d("@MBC", "AddView");
                participantLayout = (LinearLayout) participantPlaceHolder.getChildAt(currentParticipantIndex);
                participantLayout.addView(addParticipantButton);
            }
        }



    }

    void OnRemoveMoveAddParticipantButtonToEndOfPreviousParticipant(FloatingActionButton addParticipantButton)
    {
        LinearLayout participantLayout=null;
        if(registerMBCDataModel.getNoOfShares() > 1)
         {
            int prevParticipantIndex = participantPlaceHolder.getChildCount() - 1;
            if(prevParticipantIndex >= 0) {
                participantLayout = (LinearLayout) participantPlaceHolder.getChildAt(prevParticipantIndex);
                Log.d("@MBC", "remove view ");

                participantLayout.removeView(addParticipantButton);
            }


            int currentParticipantIndex = participantPlaceHolder.getChildCount() - 2;
            if(currentParticipantIndex >= 0) {
                participantLayout = (LinearLayout) participantPlaceHolder.getChildAt(currentParticipantIndex);
                Log.d("@MBC", "add view ");
                participantLayout.addView(addParticipantButton);
            }
        }



    }



}
