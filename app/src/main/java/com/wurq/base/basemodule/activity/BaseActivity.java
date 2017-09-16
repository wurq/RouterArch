package com.wurq.base.basemodule.activity;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;

import com.wurq.base.basemodule.presenter.BasePresenter;


/**
 * Created by wurongqiu on 2017/4/25.
 */

public abstract class BaseActivity<T extends BasePresenter> extends AppCompatActivity {

    private static final String TAG = "BaseActivity";
    protected T presenter;
    /**
     * 用于监听滑动的view
     */
    protected FrameLayout contentView;
    /**
     * 用于监听inflater view
     */
    protected LayoutInflater layoutInflater;
    //    protected View touchView;
//    protected ViewGroup rootView;
//    protected SystemBarTintManager mTintManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        initTintManager();
        layoutInflater = LayoutInflater.from(this);
        initPresenter();
        if (presenter != null) {
            presenter.onCreate();
        }
    }
//    //初始化沉浸栏
//    private void initTintManager() {
//        if (null == mTintManager) {
//            mTintManager = new SystemBarTintManager(this);
//        }
//        mTintManager.setStatusBarTintEnabled(true);
//        mTintManager.setStatusBarTintResource(R.color.blue_main_color);
//    }



    /**
     * Inflate a content view for the activity.
     *
     * @param resId ID for an XML layout resource as the content view
     */
    public void setRealContentView(@LayoutRes int resId) {
        getLayoutInflater().inflate(resId, contentView);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(newBase);
    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
    }

    @Override
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }

    @TargetApi(19)
    private void setTranslucentStatus(boolean on) {
        Window win = getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }

    protected void disableScreenCapture() {
        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_SECURE);
    }

    @Override
    public void onStart() {
        super.onStart();
        if (presenter != null) {
            presenter.onStart();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (presenter != null) {
            presenter.onResume();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (presenter != null) {
            presenter.onPause();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (presenter != null) {
            presenter.onStop();
        }
    }

    @Override
    public void onDestroy() {
        if (presenter != null) {
            presenter.onDestroy();
        }
        super.onDestroy();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_MENU) {
            //监控/拦截菜单键
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }



    abstract protected void initPresenter();

}
