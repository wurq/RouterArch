package com.wurq.routerarch.AbsApplication;

import android.content.Context;

import java.util.HashMap;

/**
 * Created by wurongqiu on 2017/9/17.
 */

public abstract class AbsAction {
    public abstract boolean isAsync(Context context, HashMap<String,String> requestData);
    public abstract AbsActionResult invoke(Context context, HashMap<String,String> requestData);
    public boolean isAsync(Context context, HashMap<String,String> requestData,Object object){
        return false;
    }
    public AbsActionResult invoke(Context context, HashMap<String,String> requestData,Object object){
        return new AbsActionResult
                .Builder()
                .code(AbsActionResult.CODE_NOT_IMPLEMENT)
                .msg("This method has not yet been implemented.")
                .build();
    }
}
