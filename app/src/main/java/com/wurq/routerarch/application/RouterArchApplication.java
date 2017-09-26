package com.wurq.routerarch.application;

import android.content.Context;
import android.os.Handler;

import com.wurq.base.util.ScreenUtil;
import com.wurq.routerarch.AbsApplication.AbsRouterApplication;

/**
 * Created by wurongqiu on 2017/9/16.
 */

public class RouterArchApplication extends AbsRouterApplication {
    static Handler sMainThreadHandler;

    @Override
    public void onCreate() {
        super.onCreate();

        Context context = getApplicationContext();
        ScreenUtil.GetInfo(context);
        AppProfile.setContext(context);
        AppProfile.setStartTime(System.currentTimeMillis() );
        sMainThreadHandler = new Handler();

    }

    @Override
    public void initializeAllProcessRouter() {

    }

    @Override
    protected void initializeLogic() {

    }

    @Override
    public boolean needMultipleProcess() {
        return false;
    }


    @Override
    public void onTerminate() {
        super.onTerminate();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }

}
