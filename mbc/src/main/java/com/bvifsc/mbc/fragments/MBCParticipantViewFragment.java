package com.bvifsc.mbc.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.bvifsc.core.R;
import com.bvifsc.core.di.BVIRA_Application;
import com.bvifsc.core.fragments.BaseFragment;
import com.bvifsc.mbc.Common.MBC_Constants;
import com.bvifsc.mbc.datamodel.MBCData;
import com.bvifsc.mbc.datamodel.ParticipantViewMBC;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MBCParticipantViewFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MBCParticipantViewFragment extends BaseFragment {
    public int MAX_PARTICIPANTS_PER_PAGE = 5;
    public int second_page_start_seq=3;

    MBCData mbcDataModel;

    int firstParticipantSeqNo;
    int participantSeqNo;
    LinearLayout participantPlaceHolder;

    int participantsInCurrentPage=1;
    LinearLayout participantLinearLayout;
    List<ParticipantViewMBC> participantLayoutsRef;

    int first_page_last_seq_no=2;
    boolean fromLoadParticipantData=false;
    boolean onViewCreated;

    public MBCParticipantViewFragment() {
        // Required empty public constructor
    }


    public static MBCParticipantViewFragment newInstance(MBCData mbcData) {
        MBCParticipantViewFragment fragment = new MBCParticipantViewFragment();
        Bundle args = new Bundle();
        args.putParcelable(MBC_Constants.MBC_PARTICIPANT_VIEW, mbcData);

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
            mbcDataModel = getArguments().getParcelable(MBC_Constants.MBC_PARTICIPANT_VIEW);

        }
    }


    @Override
    protected int getLayout() {
        return R.layout.fragment_participant_mbc;
    }



    @Override
    protected void initFragment(View rootView) {
        Log.d("@MBC","initFragment");


        setUpHeaderAndTitle(rootView);
        setUpToLoadPreviouslyEnteredParticipantsData();
        setUpRegisterMBCPageActions(rootView);
        Log.d("@MBC", "current page count " + participantsInCurrentPage);

    }


    void setUpHeaderAndTitle(View rootView)
    {

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
        firstParticipantSeqNo=mbcDataModel.getFirstParticipantSeqNo();



        participantLayoutsRef= new ArrayList<>();
        Log.d("@MBC", "first participant seq no " + firstParticipantSeqNo);
        Log.d("@MBC","No of participants loaded " + mbcDataModel.getParticipantShareholders().size());

        participantSeqNo=firstParticipantSeqNo;

        if(mbcDataModel.getParticipantShareholders() != null )
        {


            int noOfParticipantsDataProvided=mbcDataModel.getParticipantShareholders().size();

            if(noOfParticipantsDataProvided > firstParticipantSeqNo)
            {
                fromLoadParticipantData=true;
                loadParticipantData();
            }



        }



    }




    void loadParticipantData()
    {
        Log.d("@MBC", "loadParticipantData");
        int index;


        for( index=participantSeqNo;index< mbcDataModel.getParticipantShareholders().size();index++)
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
        ParticipantViewMBC participantMBC = inflateParticipantLayout(participantSeqNo);
        Log.d("@MBC", "Data set On screen" + mbcDataModel.getParticipantShareholders().get(participantSeqNo).getFirstName() );
        participantMBC.professionalDesignation.setText(mbcDataModel.getParticipantShareholders().get(participantSeqNo).getDesignation());
        participantMBC.firstName.setText(mbcDataModel.getParticipantShareholders().get(participantSeqNo).getFirstName());
        participantMBC.lastName.setText(mbcDataModel.getParticipantShareholders().get(participantSeqNo).getLastName());
        participantMBC.middleName.setText(mbcDataModel.getParticipantShareholders().get(participantSeqNo).getMiddleName());
        participantMBC.nationality.setText(mbcDataModel.getParticipantShareholders().get(participantSeqNo).getNationality().getNationality());
        participantMBC.participantRights.setText(mbcDataModel.getParticipantShareholders().get(participantSeqNo).getParticipantRights().getParticipantRights());

        if(mbcDataModel.getParticipantShareholders().get(participantSeqNo).getIsFirstNameOnly().equals("Y"))
            participantMBC.isFirstNameOnlyCheckBox.setChecked(true);
        else
            participantMBC.isFirstNameOnlyCheckBox.setChecked(false);

    }




    ParticipantViewMBC inflateParticipantLayout(int participantSeqNo)
    {
        Log.d("@MBC", "inflateParticipantLayout");

        participantLinearLayout=(LinearLayout) LayoutInflater.from(this.getContext()).inflate(R.layout.ra_mbc_participant_shareholder_view,null);

        ParticipantViewMBC participantMBC= new ParticipantViewMBC();
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

        participantMBC.nationality=participantLinearLayout.findViewById(R.id.participant_nationality);
        participantMBC.nationality.setId(View.generateViewId());


        participantMBC.nationalityCode=participantLinearLayout.findViewById(R.id.participant_nationality_code);
        participantMBC.nationalityCode.setId(View.generateViewId());

        participantMBC.participantRights=participantLinearLayout.findViewById(R.id.participant_rights);
        participantMBC.participantRights.setId(View.generateViewId());


        participantMBC.isFirstNameOnlyCheckBox=participantLinearLayout.findViewById(R.id.fist_name_only_check_box);
        participantMBC.isFirstNameOnlyCheckBox.setVisibility(View.GONE);
        participantMBC.isFirstNameOnlyCheckBox.setId(View.generateViewId());
        participantMBC.isFirstNameOnlyCheckBox.setOnCheckedChangeListener( (view, isChecked) ->{
            if(isChecked)
            {
                participantMBC.middleName.setVisibility(View.GONE);
                participantMBC.lastName.setVisibility(View.GONE);

            }
            else {
                participantMBC.middleName.setVisibility(View.VISIBLE);
                participantMBC.lastName.setVisibility(View.VISIBLE);
            }

        });


        participantLayoutsRef.add(participantMBC);


        participantPlaceHolder.addView(participantLinearLayout);



        return participantMBC;

    }

    void setUpRegisterMBCPageActions(View rootView)
    {
        Log.d("@MBC", "setUpRegisterMBCPageActions");
        Button primaryButton=rootView.findViewById(R.id.primaryButton);
        primaryButton.setEnabled(true);
        if(!(firstParticipantSeqNo == 0 && (participantSeqNo < mbcDataModel.getNoOfShares()-2) ))
        {
            primaryButton.setVisibility(View.GONE);
        }


        Button secondaryButton= rootView.findViewById(R.id.secondaryButton);


                primaryButton.setText("Next");

                primaryButton.setOnClickListener( view ->{



                        if(firstParticipantSeqNo == 0 && (participantSeqNo < mbcDataModel.getNoOfShares()-2) )
                        {
                            Log.d("@MBC", "Remaining participants continue in next Page");
                            mbcDataModel.setFirstParticipantSeqNo(participantSeqNo+1);

                            this.getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, MBCParticipantViewFragment.newInstance(mbcDataModel))
                                    .addToBackStack(null).commit();

                        }
                        else
                        {
                            // load payment summary
                            primaryButton.setVisibility(View.GONE);

                        }

                });


                secondaryButton.setText("Back");

                secondaryButton.setOnClickListener( view -> {

                    onBackPressed();


                });


    }



    public   void onBackPressed()
    {
        Log.d("@MBC", "onBackPressed");


        if(firstParticipantSeqNo == second_page_start_seq ) {

            mbcDataModel.setFirstParticipantSeqNo(0);

        }


        this.getActivity().getSupportFragmentManager().popBackStack();




    }
}
