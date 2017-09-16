package com.wurq.routerarch.application;

import com.wurq.base.storage.SharePreferenceHelper;

/**
 * Created by wurongqiu on 2017/5/22.
 */

public class GlobalInfo {
    private static final String MST_KKL_ONLINE_INSTALL_CHANNEL = "mst_kkl_online_install_channel";



    public static void setInstallChannel(String installChannel) {
        SharePreferenceHelper.putGlobalString(MST_KKL_ONLINE_INSTALL_CHANNEL, installChannel);
    }

    public static String getInstallChannel() {
        return SharePreferenceHelper.getGlobalString(MST_KKL_ONLINE_INSTALL_CHANNEL, null);
    }
}
