package com.bvifsc.mbc.fragments;


import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.bvifsc.core.R;
import com.bvifsc.core.fragments.BaseFragment;
import com.bvifsc.mbc.Common.MBC_Constants;
import com.bvifsc.mbc.datamodel.MBCData;


public class MBCPrincipalViewFragment extends BaseFragment {

    MBCData mbcDataModel;
    TextView mbcNumber;
    TextView mbcCountryOfOperation;
    TextView doingBusinessAs;
    TextView principalFirstName;
    TextView principalNationality;
    TextView businessPurpose;
    TextView noOfShares;
    TextView userName;
    TextView RAName;


    @Override
    protected int getLayout() {
        return R.layout.fragment_mbcbasic_info;
    }

    public MBCPrincipalViewFragment() {
        // Required empty public constructor
    }


    public static MBCPrincipalViewFragment newInstance(MBCData mbcDataModel) {
        MBCPrincipalViewFragment fragment = new MBCPrincipalViewFragment();
        Bundle args = new Bundle();
        args.putParcelable(MBC_Constants.MBC_BASIC_INFO, mbcDataModel);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
                 mbcDataModel=getArguments().getParcelable(MBC_Constants.MBC_BASIC_INFO);
        }
    }

    @Override
    protected void initFragment(View rootView) {

        setUpToDisplayMBCInfo(rootView);
            }

    void setUpToDisplayMBCInfo(View rootView)
    {
         mbcNumber= rootView.findViewById(R.id.mbc_number);
         mbcNumber.setText(mbcDataModel.getMbcNumber());

        mbcCountryOfOperation=rootView.findViewById(R.id.mbc_country_of_operation);
        mbcCountryOfOperation.setText(mbcDataModel.getCountryOfOperation().getMbcCountryOfOperation());

         doingBusinessAs=rootView.findViewById(R.id.mbc_doing_business_as);
         doingBusinessAs.setText(mbcDataModel.getDoingBusinessAs());

        mbcCountryOfOperation=rootView.findViewById(R.id.mbc_country_of_operation);
        mbcCountryOfOperation.setText(mbcDataModel.getCountryOfOperation().getMbcCountryOfOperation());

         principalFirstName=rootView.findViewById(R.id.mbc_principal_first_name);
         String fullname=mbcDataModel.getPrincipalShareholder().getFirstName() + MBC_Constants.SPACES+mbcDataModel.getPrincipalShareholder().getMiddleName()
                 +MBC_Constants.SPACES+mbcDataModel.getPrincipalShareholder().getLastName();
         principalFirstName.setText(fullname);

         principalNationality=rootView.findViewById(R.id.mbc_principal_nationality);
         principalNationality.setText(mbcDataModel.getPrincipalShareholder().getNationality().getNationality());

         businessPurpose=rootView.findViewById(R.id.mbc_business_purpose);
         businessPurpose.setText(mbcDataModel.getBusinessPurpose().getBusinessPurpose());

         noOfShares=rootView.findViewById(R.id.mbc_shares);
         noOfShares.setText(mbcDataModel.getNoOfShares()+"");

         userName=rootView.findViewById(R.id.user_name);
         userName.setText(mbcDataModel.getRaUserName());

         RAName=rootView.findViewById(R.id.ra_name);
         RAName.setText(mbcDataModel.getRegisteredAgent().getEntityName());


    }
}
