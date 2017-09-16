package com.wurq.routerarch.module.splashpage.presenter;

import com.wurq.base.basemodule.presenter.BasePresenter;
import com.wurq.base.util.HandleUtil;
import com.wurq.routerarch.application.AppProfile;
import com.wurq.routerarch.module.mainpage.activity.MainActivity;
import com.wurq.routerarch.module.splashpage.activity.SplashActivity;

/**
 * Created by wurongqiu on 2017/6/19.
 */

public class SplashPresenter extends BasePresenter<SplashActivity> {
    private static final int WAIT_TIME = 2000;

    public SplashPresenter(SplashActivity target) {
        super(target);
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public void onStart() {
        super.onStart();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    public void init() {
        if (hasBeenOpened()) {
            doDelay();
        } else {
            // 没有打开过,打开引导页

        }
    }

    private void doDelay() {
        // 2秒后强行关闭闪屏
        HandleUtil.doDelay(new Runnable() {
            @Override
            public void run() {

                MainActivity.start(AppProfile.getContext());
                target.finish();
            }
        }, WAIT_TIME);
    }




    /**
     * @return 是否是打开过app
     */
    private boolean hasBeenOpened() {
        return true;
//        return SharePreferenceHelper.getGlobalBoolean(CommonConst.ApplicationInfo.HAS_BEEN_OPENED, false);
    }
}
