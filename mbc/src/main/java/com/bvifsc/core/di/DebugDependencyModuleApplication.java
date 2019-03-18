package com.bvifsc.core.di;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Module;

/**
 * Created by Ramesh on 08-11-2018.
 */
@Module
@Singleton
public class DebugDependencyModuleApplication extends AppBaseModule {
    DebugDependencyModuleApplication(Application application) {
        super(application);
    }
}
