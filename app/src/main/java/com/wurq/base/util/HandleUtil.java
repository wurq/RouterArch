package com.wurq.base.util;

import android.os.Handler;

/**
 * Created by wurongqiu on 2017/6/19.
 */

public class HandleUtil {
    public static void doDelay(Runnable runable, long delayMillis) {
        Handler handler = new Handler();
        handler.postDelayed(runable, delayMillis);
    }

    public static void doIm(Runnable runable) {
        Handler handler = new Handler();
        handler.post(runable);
    }

}
