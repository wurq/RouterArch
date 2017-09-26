package com.wurq.routerarch.AbsApplication;

import java.util.HashMap;

/**
 * Created by wurongqiu on 2017/9/17.
 */

public abstract class AbsProvider {
    private boolean mValid = true;
    private HashMap<String,AbsAction> mActions;
    public AbsProvider(){
        mActions = new HashMap<>();
        registerActions();
    }
    protected void registerAction(String actionName,AbsAction action){
        mActions.put(actionName,action);
    }

    public AbsAction findAction(String actionName){
        return mActions.get(actionName);
    }

    public boolean isValid(){
        return mValid;
    }

    protected abstract void registerActions();
}
