package com.bvifsc.core.di;

import android.content.SharedPreferences;

import com.bvifsc.core.MBCHomeActivity;
import com.bvifsc.core.fragments.NotificationFragment;
import com.bvifsc.core.common.AppNetworkRequester;
import com.bvifsc.core.network.request.RequestExecutor;
import com.bvifsc.mbc.Common.fragments.CommonDialogFragment;
import com.bvifsc.mbc.fragments.KYCDocumentsFragment;
import com.bvifsc.mbc.fragments.MBCLoginFragment;
import com.bvifsc.mbc.fragments.MBCParticipantViewFragment;
import com.bvifsc.mbc.fragments.MBCViewFragment;
import com.bvifsc.mbc.fragments.MBC_SignUpFragment;
import com.bvifsc.mbc.fragments.RegisterMBCFragment;
import com.bvifsc.mbc.fragments.RegisterMBCPayment;
import com.bvifsc.mbc.fragments.RegisterParticipantMBC;
import com.bvifsc.mbc.fragments.UserMBCListFragment;
import com.bvifsc.mbc.pageModels.AmendCharter;
import com.bvifsc.mbc.pageModels.MBCView;
import com.bvifsc.mbc.pageModels.RegisterMBCPage;
import com.bvifsc.mbc.pageModels.SignInPageModel;
import com.bvifsc.mbc.pageModels.KYCDocumentsPage;
import com.bvifsc.mbc.pageModels.SignUpPageModel;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by Ramesh on 08-11-2018.
 */

public interface ApplicationComponents {

    void inject(BVIRA_Application bvira_application);
    void inject(NotificationFragment notificationFragment);

    void inject(MBCHomeActivity mainActivity);

    void inject(MBCLoginFragment mbcLoginFragment);
    void inject(MBC_SignUpFragment mbc_signUpFragment);
    void inject(RegisterMBCFragment registerMBCFragment);
    void inject(SignUpPageModel signUpPageModel);
    void inject(SignInPageModel signInPageModel);
    void inject(RegisterMBCPage registerMBCPage);
    void inject(CommonDialogFragment commonDialogFragment);
    void inject(KYCDocumentsPage kycDocuments);
    void inject(AmendCharter amendCharter);
    void inject(KYCDocumentsFragment kycDocumentsFragment);
    void inject(RegisterMBCPayment registerMBCPayment);
    void inject(RegisterParticipantMBC registerParticipantMBC);
    void inject(MBCParticipantViewFragment participantViewFragment);
    void inject(UserMBCListFragment userMBCListFragment);
    void inject(MBCView mbcView);
    void inject(MBCViewFragment mbcViewFragment);

    SharedPreferences providesSharedPreferences();
    EventBus eventBusProvider();
    AppNetworkRequester apappNetworkRequesterProvider();
    RequestExecutor requestExecutorProvider();


}
