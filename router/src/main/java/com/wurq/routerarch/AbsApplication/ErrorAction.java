package com.wurq.routerarch.AbsApplication;

import android.content.Context;

import java.util.HashMap;

/**
 * Created by wurongqiu on 2017/9/18.
 */

public class ErrorAction extends AbsAction {
    private static final String DEFAULT_MESSAGE = "Something was really wrong!";
    private int mCode;
    private String mMessage;
    private boolean mAsync;
    public ErrorAction() {
        mCode = AbsActionResult.CODE_ERROR;
        mMessage = DEFAULT_MESSAGE;
        mAsync = false;
    }

    public ErrorAction(boolean isAsync, int code, String message) {
        this.mCode = code;
        this.mMessage = message;
        this.mAsync = isAsync;
    }

    @Override
    public boolean isAsync(Context context, HashMap<String, String> requestData) {
        return mAsync;
    }

    @Override
    public AbsActionResult invoke(Context context, HashMap<String, String> requestData) {
        AbsActionResult result = new AbsActionResult.Builder()
                .code(mCode)
                .msg(mMessage)
                .data(null)
                .object(null)
                .build();
        return result;
    }

}
