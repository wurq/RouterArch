package com.wurq.base.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;

import com.wurq.routerarch.application.AppProfile;

/**
 * Created by windows on 2017/7/14 0014.
 */

public class Utils {

    /**
     * 获取网络连接状态
     *
     * @return NETWORK_STATE
     */
    public static NETWORK_STATE getNetworkState() {
        ConnectivityManager cm = (ConnectivityManager) AppProfile.getContext()
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm == null) {
            return NETWORK_STATE.OFFLINE;
        }
        NetworkInfo network = cm.getActiveNetworkInfo();
        if (network == null || !network.isAvailable()) {
            return NETWORK_STATE.OFFLINE;
        }
        NetworkInfo mobile = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        NetworkInfo wifi = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        boolean mobileOK = (mobile != null && mobile.isConnected());
        boolean wifiOK = (wifi != null && wifi.isConnected());
        if (!mobileOK && !wifiOK) {
            return NETWORK_STATE.OFFLINE;
        }
        if (mobileOK) {
            return NETWORK_STATE.MOBILE;
        } else {
            return NETWORK_STATE.WIFI;
        }
    }

    /**
     * 网络状态是否离线
     */
    public static boolean offline() {
        return NETWORK_STATE.OFFLINE == getNetworkState();
    }

    //获取网络类型  2g 3g 4g wifi  nowifi
    public static String getNetworkType() {
        String strNetworkType = "";
        NetworkInfo networkInfo = ((ConnectivityManager) AppProfile.getContext().getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            if (networkInfo.getType() == ConnectivityManager.TYPE_WIFI) {
                strNetworkType = "WIFI";
            } else if (networkInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
                String _strSubTypeName = networkInfo.getSubtypeName();
                int networkType = networkInfo.getSubtype();
                switch (networkType) {
                    case TelephonyManager.NETWORK_TYPE_GPRS:
                    case TelephonyManager.NETWORK_TYPE_EDGE:
                    case TelephonyManager.NETWORK_TYPE_CDMA:
                    case TelephonyManager.NETWORK_TYPE_1xRTT:
                    case TelephonyManager.NETWORK_TYPE_IDEN: //api<8 : replace by 11
                        strNetworkType = "2G";
                        break;
                    case TelephonyManager.NETWORK_TYPE_UMTS:
                    case TelephonyManager.NETWORK_TYPE_EVDO_0:
                    case TelephonyManager.NETWORK_TYPE_EVDO_A:
                    case TelephonyManager.NETWORK_TYPE_HSDPA:
                    case TelephonyManager.NETWORK_TYPE_HSUPA:
                    case TelephonyManager.NETWORK_TYPE_HSPA:
                    case TelephonyManager.NETWORK_TYPE_EVDO_B: //api<9 : replace by 14
                    case TelephonyManager.NETWORK_TYPE_EHRPD:  //api<11 : replace by 12
                    case TelephonyManager.NETWORK_TYPE_HSPAP:  //api<13 : replace by 15
                        strNetworkType = "3G";
                        break;
                    case TelephonyManager.NETWORK_TYPE_LTE:    //api<11 : replace by 13
                        strNetworkType = "4G";
                        break;
                    default:
                        // http://baike.baidu.com/item/TD-SCDMA 中国移动 联通 电信 三种3G制式
                        if (_strSubTypeName.equalsIgnoreCase("TD-SCDMA") || _strSubTypeName.equalsIgnoreCase("WCDMA") || _strSubTypeName.equalsIgnoreCase("CDMA2000")) {
                            strNetworkType = "3G";
                        } else {
                            strNetworkType = _strSubTypeName;
                        }

                        break;
                }

            }
        } else {
            strNetworkType = "NO WIFI";
        }
        return strNetworkType;
    }

    public static enum NETWORK_STATE {
        OFFLINE, MOBILE, WIFI,
    }

}
