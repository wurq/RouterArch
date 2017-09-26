package com.wurq.routerarch.AbsApplication;

import android.content.res.Configuration;
import android.support.annotation.NonNull;

/**
 * Created by wurongqiu on 2017/9/20.
 */

public class BaseApplicationLogic {
    protected AbsRouterApplication mApplication;
    public BaseApplicationLogic() {
    }

    public void setApplication(@NonNull AbsRouterApplication application) {
        mApplication = application;
    }

    public void onCreate() {
    }

    public void onTerminate() {
    }

    public void onLowMemory() {
    }

    public void onTrimMemory(int level) {
    }

    public void onConfigurationChanged(Configuration newConfig) {
    }
}
