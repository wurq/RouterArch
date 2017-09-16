package com.wurq.routerarch.application;

import android.app.Application;
import android.content.Context;
import android.os.Handler;

import com.wurq.base.util.ScreenUtil;

/**
 * Created by wurongqiu on 2017/9/16.
 */

public class RouterArchApplication extends Application {
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
    public void onTerminate() {
        super.onTerminate();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }

}
