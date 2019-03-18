package com.bvifsc.mbc.fragments;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.bvifsc.core.R;
import com.bvifsc.core.di.BVIRA_Application;
import com.bvifsc.core.fragments.BaseFragment;
import com.bvifsc.core.models.response.Action;
import com.bvifsc.mbc.Common.FragmentLoader;
import com.bvifsc.mbc.Common.MBC_Constants;
import com.bvifsc.mbc.assemblers.MBC_PageLoder;
import com.bvifsc.mbc.datamodel.SignIn;
import com.bvifsc.mbc.model.SignInResponseModel;
import com.bvifsc.mbc.presenters.MBCLoginPresenter;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;


public class MBCLoginFragment extends BaseFragment  {

    SignInResponseModel signInResponseModel;

    TextView header;
    EditText userName;
    EditText password;
    SignIn signInDataModel;
    Button signIn;
    Button signUP;
    TextInputLayout userIdInputLayout;
    TextInputLayout passwordInputLayout;
    @Inject
    MBCLoginPresenter mbcLoginPresenter;



    public MBCLoginFragment() {
        // Required empty public constructor
    }

    public static MBCLoginFragment newInstance(SignInResponseModel signInResponseModel) {
        MBCLoginFragment fragment = new MBCLoginFragment();
        Bundle args = new Bundle();
        args.putParcelable(MBC_Constants.MBC_LOG_IN_FRAGMENT, signInResponseModel);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void injectFragment() {
        Log.d("@RAMBCAPP", "inject Fragment");
        BVIRA_Application.getObjectGraph(getContext().getApplicationContext()).inject(this);

    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_mbclogin;
    }

    @Override
    protected void initFragment(View rootView) {
        setUpHeaders(rootView);
        setUpPageActions(rootView);
        setUpToEnterCredentials(rootView);




    }

    public void setUpHeaders(View rootView)
    {
        header=rootView.findViewById(R.id.header);
        header.setText(signInResponseModel.getHeader());
    }
    public void setUpPageActions(View rootView)
    {
        signIn=rootView.findViewById(R.id.signIn);
        signIn.setText(signInResponseModel.getPageInfo().getButtonMap().get(MBC_Constants.PRIMARY_BUTTON).getTitle());
        signUP=rootView.findViewById(R.id.signUp);
        signIn.setText(signInResponseModel.getPageInfo().getButtonMap().get(MBC_Constants.PRIMARY_BUTTON).getTitle());

        if(signInResponseModel.getPageInfo().getButtonMap().get(MBC_Constants.PRIMARY_BUTTON) != null)
        {
            Action primaryAction = signInResponseModel.getPageInfo().getButtonMap().get(MBC_Constants.PRIMARY_BUTTON);

            signIn.setOnClickListener(view -> {
                getScreenData();
              Map<String,String> extraPrarams = new HashMap<>();
                extraPrarams.put("userName",userName.getText().toString());
              extraPrarams.put("password",password.getText().toString());

                primaryAction.setExtraParams(extraPrarams);
              mbcLoginPresenter.verifyCredentials(primaryAction);

            });

            signIn.setVisibility(View.VISIBLE);

        }
        else
            signIn.setVisibility(View.GONE);

        if(signInResponseModel.getPageInfo().getButtonMap().get(MBC_Constants.SECONDARY_BUTTON) != null)
        {
            Action  secondaryAction = signInResponseModel.getPageInfo().getButtonMap().get(MBC_Constants.SECONDARY_BUTTON);

            signUP.setOnClickListener(view -> {
                if(secondaryAction.getActionType().equalsIgnoreCase("LOAD"))
                {
                    try {
                        FragmentLoader fragmentLoader= (FragmentLoader) MBC_PageLoder.PAGE_MAPPING.get(secondaryAction.getPageType()).newInstance();
                        fragmentLoader.load();

                    } catch (IllegalAccessException e) {

                        e.printStackTrace();
                    } catch (java.lang.InstantiationException e) {
                        e.printStackTrace();
                    }

                }


            });
            signUP.setVisibility(View.VISIBLE);

        }
        else
            signUP.setVisibility(View.GONE
            );

    }
    void getScreenData()
    {
        signInDataModel = new SignIn();
        signInDataModel.setUserName(userName.getText().toString());
        signInDataModel.setPassword(password.getText().toString());

    }

    void setUpToEnterCredentials(View rootView)
    {
        userIdInputLayout= rootView.findViewById(R.id.userIdInputLayout);
        userName=rootView.findViewById(R.id.userName);
        passwordInputLayout=rootView.findViewById(R.id.passwordInputLayout);
        password=rootView.findViewById(R.id.password);



    }
    @Override
    protected void loadFragmentArguments() {
                signInResponseModel=  getArguments().getParcelable(MBC_Constants.MBC_LOG_IN_FRAGMENT);

        }


}
