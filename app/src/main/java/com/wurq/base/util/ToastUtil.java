package com.wurq.base.util;

import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;

/**
 * Created by Anson on 2017/6/30.
 */

public class ToastUtil {

    private static Handler handler;

    // 显示短提示
    public static void makeShortToast(final String content) {
        if (Looper.getMainLooper() == Looper.myLooper()) {
            stringToast(content);
        }else {
            if (handler == null) {
                handler = new Handler(Looper.getMainLooper());
            }
            handler.post(new Runnable() {
                @Override
                public void run() {
                    stringToast(content);
                }
            });
        }

    }

    // 显示短提示
    public static void makeShortToast(final int stringId) {
        if (Looper.getMainLooper() == Looper.myLooper()) {
            idToast(stringId);
        }else {
            if (handler == null) {
                handler = new Handler(Looper.getMainLooper());
            }
            handler.post(new Runnable() {
                @Override
                public void run() {
                    idToast(stringId);
                }
            });
        }
    }


    // 显示长提示
    public static void makeLongToast(final String content) {
        if (Looper.getMainLooper() == Looper.myLooper()) {
            stringToast(content);
        }else {
            if (handler == null) {
                handler = new Handler(Looper.getMainLooper());
            }
            handler.post(new Runnable() {
                @Override
                public void run() {
                    stringToast(content);
                }
            });
        }
    }

    // 显示长提示
    public static void makeLongToast(final int stringId) {
        if (Looper.getMainLooper() == Looper.myLooper()) {
            idToast(stringId);
        }else {
            if (handler == null) {
                handler = new Handler(Looper.getMainLooper());
            }
            handler.post(new Runnable() {
                @Override
                public void run() {
                    idToast(stringId);
                }
            });
        }
    }

    private static void stringToast(String content) {
        if (!TextUtils.isEmpty(content)) {
//            new DialogBundle.ToastDialog.Builder(AppProfile.getContext()).setAlert(content).build().show();
        }
    }

    private static void idToast(int stringId) {
        if (stringId > 0) {
//            new DialogBundle.ToastDialog.Builder(AppProfile.getContext()).setAlert(stringId).build().show();
        }
    }
}
