package com.bvifsc.core.di;

import android.app.Application;
import android.content.Context;

/**
 * Created by Ramesh on 08-11-2018.
 */
public class BVIRA_Application extends Application  {
      public static Context context;
    @Override
    public void onCreate() {
        super.onCreate();
        context=getApplicationContext();
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        getObjectGraph().inject(this);
    }

    public DebugApplicationComponents  getObjectGraph()
    {
        DebugApplicationComponents debugApplicationComponents = DaggerDebugApplicationComponents.builder().
                debugDependencyModuleApplication(new DebugDependencyModuleApplication(this))
                .build();

        return debugApplicationComponents;
    }

    public static DebugApplicationComponents getObjectGraph(Context context) {
        return ((BVIRA_Application) context).getObjectGraph();
    }


}
