package com.wurq.base.util;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

/**
 * Created by anson on 2017/7/26.
 */

public class DialogUtils {
    /**
     * 展示标准双按钮弹框
     * @param context
     * @param titleId 标题
     * @param messageId 内容
     * @param negativeBtnId 左边按钮文字(取消)
     * @param positiveBtnId 右边按钮文字(确定)
     * @param negativeListener 取消监听
     * @param positiveListener 确定监听
     */
    public static void showAlertDialog(Context context, int titleId, int messageId, int negativeBtnId, int positiveBtnId, DialogInterface.OnClickListener negativeListener, DialogInterface.OnClickListener positiveListener, boolean isCancle) {
        if (context == null) {
            return;
        }
        AlertDialog alertDialog = new AlertDialog.Builder(context)
                .setTitle(titleId)
                .setMessage(messageId)
                .setPositiveButton(positiveBtnId,positiveListener)
                .setNegativeButton(negativeBtnId,negativeListener)
                .setCancelable(isCancle)
                .create();
        alertDialog.show();
        /*DialogBundle.AlertDialog callDialog = new DialogBundle.AlertDialog.Builder(context)
                .setTitle(titleId)
                .setMessage(messageId)
                .setNegativeButton(negativeBtnId,negativeListener).setPositiveButton(positiveBtnId, positiveListener).build();
        callDialog.show();*/
    }


}
