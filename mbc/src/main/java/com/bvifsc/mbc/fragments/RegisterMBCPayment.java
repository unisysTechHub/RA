package com.bvifsc.mbc.fragments;


import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import com.bvifsc.core.R;
import com.bvifsc.core.di.BVIRA_Application;
import com.bvifsc.core.fragments.BaseFragment;
import com.bvifsc.core.models.response.Action;
import com.bvifsc.core.models.response.PageResponseModel;
import com.bvifsc.mbc.Common.MBC_Constants;
import com.bvifsc.mbc.datamodel.MBCData;
import com.bvifsc.mbc.model.RegisterMBCPageResponseModel;
import com.bvifsc.mbc.model.registerMBC.RegisterMBCModel;
import com.bvifsc.mbc.presenters.AmendCharterPresenter;
import com.bvifsc.mbc.presenters.RegisterMBCPresenter;

import javax.inject.Inject;


public class RegisterMBCPayment extends BaseFragment implements View.OnClickListener {

    RegisterMBCPageResponseModel registerMBCPageResponseModel;
    RegisterMBCModel registerMBCModel;
    MBCData registerMBCDataModel;
    PageResponseModel registerMBCpageModel;
    TextView availBalance;
    TextView transactionFee;
    TextView transactionName;
    RadioButton wallet;
    RadioButton payPal;
    TextView pageHeader;

    @Inject
    RegisterMBCPresenter registerMBCPresenter;

    @Inject
    AmendCharterPresenter amendCharterPresenter;

    public RegisterMBCPayment() {
        // Required empty public constructor
    }
    public static RegisterMBCPayment newInstance(RegisterMBCPageResponseModel registerMBCPageResponseModel) {
        RegisterMBCPayment fragment = new RegisterMBCPayment();
        Bundle args = new Bundle();
        args.putParcelable(MBC_Constants.REGISTER_MBC_PAYMENT_SUMMARY, registerMBCPageResponseModel);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
          registerMBCPageResponseModel   = getArguments().getParcelable(MBC_Constants.REGISTER_MBC_PAYMENT_SUMMARY);
            registerMBCModel=registerMBCPageResponseModel.getRegisterMBCModel();
            registerMBCpageModel=registerMBCPageResponseModel.getPageInfo();
            registerMBCDataModel = registerMBCPageResponseModel.getRegisterMBCDataModel();
        }
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_register_mbcpayment;
    }

    @Override
    protected void injectFragment() {
        BVIRA_Application.getObjectGraph(BVIRA_Application.context).inject(this);
    }


    @Override
    protected void initFragment(View rootView) {
        Log.d("@MBC", "initFragment");

        setUpPaymentPageHeadersAndTitle(rootView);
        setUpToDisplayPaymentSummary(rootView);
        setUpRegisterMBCPageActions(rootView);
    }
    void setUpPaymentPageHeadersAndTitle(View rootView)
    {
        pageHeader=rootView.findViewById(R.id.payment_header);
        pageHeader.setText(registerMBCpageModel.getTitle() );
    }

    void setUpToDisplayPaymentSummary(View rootView)
    {
        Log.d("@MBC", "setUpToDisplayPaymentSummary");
         availBalance=rootView.findViewById(R.id.avail_balance);
         availBalance.setText(String.valueOf(registerMBCModel.getPaymentSummary().getBalance()) );
         transactionFee=rootView.findViewById(R.id.transaction_fee);
         transactionFee.setText(String.valueOf(registerMBCModel.getPaymentSummary().getTransactionFee()));
         transactionName=rootView.findViewById(R.id.transaction_name);
         transactionName.setText(registerMBCModel.getPaymentSummary().getTransactionName());

         wallet = rootView.findViewById(R.id.wallet);
         wallet.setOnClickListener(this);
         payPal =rootView.findViewById(R.id.payPal);
         payPal.setOnClickListener(this);




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
                primaryButton.setText(registerMBCpageModel.getButtonMap().get(MBC_Constants.PRIMARY_BUTTON).getTitle());

                primaryButton.setOnClickListener( view ->{


                    if(areValidInput())
                    {

                        if(primaryAction.getPageType().equalsIgnoreCase(MBC_Constants.AMEND_CHARTER))
                            amendCharterPresenter.amendCharter(primaryAction,registerMBCDataModel);
                        else
                            if(primaryAction.getPageType().equalsIgnoreCase(MBC_Constants.REGISTER_MBC))
                        registerMBCPresenter.registerMBC(primaryAction,registerMBCDataModel);




                    }


                });

            }

            Action secondaryAction   = registerMBCpageModel.getButtonMap().get(MBC_Constants.SECONDARY_BUTTON);

            if(secondaryAction!= null)
            {
                secondaryButton.setText("back");

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

    boolean areValidInput()
    {
        boolean isValid=true;

        if(!(wallet.isChecked() || payPal.isChecked()))
            isValid=false;

        return isValid;

    }

    void onBackPressed()
    {

        Log.d("@MBC", "onBackPressed");
          this.getActivity().getSupportFragmentManager().popBackStackImmediate();


    }

    @Override
    public void onClick(View v) {

        boolean checked=((RadioButton) v).isChecked();

        if(v.getId() == R.id.wallet)
        {
            if(checked)
            {
            registerMBCDataModel.setPaymentMode(MBC_Constants.WALLET);

            }

        }
        if(v.getId() == R.id.payPal)
        {
            if(checked)
            {
                registerMBCDataModel.setPaymentMode(MBC_Constants.PAY_PAL);

            }

        }


    }

}
