package com.wurq.routerarch.application;

import com.wurq.routerarch.AbsApplication.AbsRouterApplication;

/**
 * Created by wurongqiu on 2017/9/20.
 */

public class LocalModuleApplication extends AbsRouterApplication {
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
}
