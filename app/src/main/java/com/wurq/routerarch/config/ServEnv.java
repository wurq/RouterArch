package com.wurq.routerarch.config;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.text.TextUtils;
import android.util.Log;

import com.wurq.routerarch.application.GlobalInfo;
import com.wurq.routerarch.BuildConfig;
import com.wurq.routerarch.application.AppProfile;

import java.io.IOException;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;


/**
 * Created by wurongqiu on 17/4/11.
 */

public class ServEnv {

    private static final String TAG = "ServEnv";
    private static final String sBaseUrlDev =  "https://ww"; //开发服务器环境 https

    private static final String sBaseUrlTest = "https://www"; //测试服务器 https
    private static final String sBaseUrlTestHttp = "http://www"; //测试服务器 http

    private static final String sBaseUrlPre = "http://www"; //预发布服务器 http
    private static final String sBaseUrlOnline = "https://www"; //线上服务器环境 https

    public static String getAppBaseUrl() {
        if (BuildConfig.FLAVOR.equals("")) {
            return sBaseUrlOnline;
        } else if (BuildConfig.FLAVOR.equals("Pre")) {
            return sBaseUrlPre;
        } else if (BuildConfig.FLAVOR.equals("Dev")) {
            return sBaseUrlDev;
        } else {
            return sBaseUrlOnline;
        }
    }

    private static String sChannel;

    public static boolean isDebug() {
        return BuildConfig.DEBUG;
    }

    public static boolean isNotOnLine() {
        return false;
//        return !BuildConfig.FLAVOR.equals("_360.."); ///
    }

    public static String getChannel() {
        if(isDebug())
            return BuildConfig.FLAVOR;

        if (sChannel != null) return sChannel;
        sChannel = "";

        String channel = GlobalInfo.getInstallChannel();
        if (TextUtils.isEmpty(channel)) {
            channel = ServEnv.getChannel(AppProfile.getContext());
            GlobalInfo.setInstallChannel(channel);
        }

        sChannel = channel;

        if(isNotOnLine())  {
            return BuildConfig.FLAVOR+channel;
        }
        else {
            return channel;
        }
    }


    private static String getChannel(Context context) {
        ApplicationInfo appinfo = context.getApplicationInfo();
        String sourceDir = appinfo.sourceDir;

        Log.d(TAG, "getChannel (context) sourceDir = " + sourceDir);

        String ret = "";
        ZipFile zipfile = null;
        try {
            zipfile = new ZipFile(sourceDir);

            Enumeration<?> entries = zipfile.entries();
            while (entries.hasMoreElements()) {
                ZipEntry entry = ((ZipEntry) entries.nextElement());
                String entryName = entry.getName();

                if (entryName.startsWith("META-INF/opencoursechannel")) {
                    ret = entryName;
                    break;
                }
            }
        } catch (IOException e) {
            Log.d(TAG, e.toString());
        } finally {
            if (zipfile != null) {
                try {
                    zipfile.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        String[] split = ret.split("_");
        if (split != null && split.length >= 2) {
            sChannel = ret.substring(split[0].length() + 1);
        }

        return sChannel;
    }
}
