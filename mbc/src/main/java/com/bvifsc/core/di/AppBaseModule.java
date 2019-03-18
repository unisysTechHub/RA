package com.bvifsc.core.di;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.bvifsc.core.common.AppNetworkRequester;
import com.bvifsc.core.network.request.ResourceServiceRequestor;
import com.bvifsc.core.network.request.RequestExecutor;
import com.bvifsc.core.presenters.BasePresenter;
import com.bvifsc.mbc.Common.SharedPreferencesUtils;
import com.bvifsc.mbc.model.UserAuthInfo;
import com.bvifsc.mbc.network.response.signIn.UserInfo;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Ramesh on 16-11-2018.
 */
@Module
@Singleton
public class AppBaseModule {

    Application application;

    AppBaseModule(Application application) {

        this.application = application;
    }

    @Provides
    @Singleton
    public final SharedPreferences providesSharedPreferences() {
        return PreferenceManager.getDefaultSharedPreferences(application);
    }


    @Provides
    @Singleton
    public final EventBus eventBusProvider() {
        return EventBus.getDefault();
    }

    @Provides
    @Singleton
    public final AppNetworkRequester appNetworkRequesterProvider() {
        return AppNetworkRequester.getInstance(application);
    }

    @Provides
    @Singleton
    public final RequestExecutor requestExecutorProvider()
    {
        return new ResourceServiceRequestor(application,appNetworkRequesterProvider(),eventBusProvider());

    }

    @Provides
    @Singleton
    public final BasePresenter basePresenterProvider()
    {

        return  new BasePresenter(requestExecutorProvider(),eventBusProvider());
    }

    @Provides
    public final SharedPreferencesUtils providesSharedPreferencesUtil(SharedPreferences preferences) {
        return new SharedPreferencesUtils(preferences);
    }

    @Provides
    public final UserAuthInfo providesUserAuthInfo()
    {
        return UserAuthInfo.getInstance();
    }

}

