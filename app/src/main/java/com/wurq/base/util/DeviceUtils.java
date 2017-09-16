package com.wurq.base.util;

import android.content.Context;
import android.os.Build;
import android.telephony.TelephonyManager;

import java.lang.reflect.InvocationTargetException;

public class DeviceUtils {
	/**
	 * 获取手机的IMEI
	 * 
	 * @param context
	 * @return
	 */
	public static String getIMEI(Context context) {
		TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
		String imei = tm.getDeviceId();
		if(imei == null) {
			imei = "";
		}
		return imei;
	}
	
	/**
	 * 获取IMSI
	 * 
	 * @param context
	 * @return
	 */
	private static String getDefaultIMSI(Context context) {
		TelephonyManager tm = (TelephonyManager) context
				.getSystemService(Context.TELEPHONY_SERVICE);
		String imsi = null;
		imsi = tm.getSubscriberId();
		return imsi;
	}
	
	/**
	 * 获取IMSI(优先使用卡1，这个方式使用了一个android5.0的非公开API，使用反射机制调用)
	 * 
	 * @param context
	 * @return
	 */
	public static String getIMSI(Context context) {
		TelephonyManager tm = (TelephonyManager) context
				.getSystemService(Context.TELEPHONY_SERVICE);
		String imsi = null;
		//如果是5.0及以上，用反射方式调用非公开的API，去调用卡1的IMSI信息
		if (Build.VERSION.SDK_INT >= 20) {
			try {
				Class<?> classTypeSubscriptionManager = Class
						.forName("android.telephony.SubscriptionManager");
				Object result = classTypeSubscriptionManager.getMethod(
						"getSubId", int.class).invoke(
						classTypeSubscriptionManager.newInstance(),
						Integer.valueOf(0));
				if (null != result) {
					long[] subId = (long[]) result;
					if (subId.length > 0) {
						Class<TelephonyManager> classTypeTelephonyManager = TelephonyManager.class;
						Object _imsi = classTypeTelephonyManager.getMethod(
								"getSubscriberId", long.class).invoke(tm,
								Long.valueOf(subId[0]));
						if (null != _imsi) {
							imsi = _imsi.toString();
						}
					}
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
			imsi = (null == imsi)?getDefaultIMSI(context):imsi;
		}else{
			imsi = getDefaultIMSI(context);
		}
		
		if(imsi == null) {
			imsi = "";
		}
		
		return imsi;
	}
}
