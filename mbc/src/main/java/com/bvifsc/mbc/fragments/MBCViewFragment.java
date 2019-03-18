package com.bvifsc.mbc.fragments;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.bvifsc.core.R;
import com.bvifsc.core.di.BVIRA_Application;
import com.bvifsc.core.fragments.BaseFragment;
import com.bvifsc.core.models.response.Action;
import com.bvifsc.core.models.response.PageResponseModel;
import com.bvifsc.mbc.Common.MBC_Constants;
import com.bvifsc.mbc.datamodel.MBCData;
import com.bvifsc.mbc.model.mbcDataView.MBCViewPageResponseModel;


public class MBCViewFragment extends BaseFragment {

    MBCViewPageResponseModel mbcViewPageResponseModel;
    MBCData mbcDataModel;
    PageResponseModel mbcViePageModel;
    TextView mbcHeader;
    public MBCViewFragment() {
        // Required empty public constructor
    }

    public static MBCViewFragment newInstance(MBCViewPageResponseModel mbcViewPageResponseModel) {
        MBCViewFragment fragment = new MBCViewFragment();
        Bundle args = new Bundle();
        args.putParcelable(MBC_Constants.MBC_VIEW, mbcViewPageResponseModel);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mbcViewPageResponseModel = getArguments().getParcelable(MBC_Constants.MBC_VIEW);
            mbcDataModel=mbcViewPageResponseModel.getMbcDataModel();
            mbcViePageModel=mbcViewPageResponseModel.getPageInfo();
        }
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_mbcview;
    }

    @Override
    protected void injectFragment() {
        BVIRA_Application.getObjectGraph(BVIRA_Application.context).inject(this);
    }

    @Override
    protected void initFragment(View rootView) {
        setUpHeadersAndTitle(rootView);
        loadMBCPrincipalViewFragment();
        loadMBCPrincipalViewFragment();
        setUpMBCViewPageActions(rootView);
    }

    void setUpHeadersAndTitle(View rootView)
    {
            mbcHeader=rootView.findViewById(R.id.mbc_view_header);
            mbcHeader.setText(mbcViePageModel.getTitle());


    }

    void loadMBCPrincipalViewFragment()
    {

        getChildFragmentManager().beginTransaction().replace(R.id.mbc_view_frame_layout,MBCPrincipalViewFragment.newInstance(mbcDataModel)).commit();
        if(mbcDataModel.getNoOfShares() -1 > 0)
        {
            getChildFragmentManager().beginTransaction().replace(R.id.mbc_view_participant_frame_layout,MBCParticipantViewFragment.newInstance(mbcDataModel)).commit();


        }


    }
    void setUpMBCViewPageActions(View rootView)
    {
        Log.d("@MBC", "setUpMBCViewPageActions");
        Button primaryButton=rootView.findViewById(R.id.primaryButton);
        primaryButton.setEnabled(true);


        Button secondaryButton= rootView.findViewById(R.id.secondaryButton);
        secondaryButton.setVisibility(View.VISIBLE);
        primaryButton.setVisibility(View.GONE);
        secondaryButton.setText("Back");
        if(mbcDataModel.getNoOfShares()-1 != 0)
        {
            primaryButton.setVisibility(View.VISIBLE);

        }
        else {
            primaryButton.setVisibility(View.GONE);

        }

        if(mbcViePageModel.getButtonMap() != null)
        {
            Action primaryAction = mbcViePageModel.getButtonMap().get(MBC_Constants.PRIMARY_BUTTON);
            if(primaryAction!= null)
            {
                primaryButton.setText("Next");

                primaryButton.setOnClickListener( view ->{
                        if(mbcDataModel.getNoOfShares()-1 != 0) {
                            mbcDataModel.setFirstParticipantSeqNo(0);
                            this.getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, MBCParticipantViewFragment.newInstance(mbcDataModel))
                                    .addToBackStack(null).commit();
                        }

                });

            }

        }
        else
            primaryButton.setVisibility(View.GONE);

        secondaryButton.setOnClickListener( view -> {
            getActivity().getSupportFragmentManager().popBackStack();
        });

    }


}
