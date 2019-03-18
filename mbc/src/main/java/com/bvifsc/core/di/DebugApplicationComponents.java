package com.bvifsc.core.di;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Ramesh on 08-11-2018.
 */
@Singleton
@Component(modules = DebugDependencyModuleApplication.class)
public interface DebugApplicationComponents  extends ApplicationComponents{
}
