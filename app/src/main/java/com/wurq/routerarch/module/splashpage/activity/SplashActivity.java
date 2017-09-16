package com.wurq.routerarch.module.splashpage.activity;

import android.os.Bundle;
import android.view.WindowManager;

import com.wurq.base.basemodule.activity.BaseActivity;
import com.wurq.routerarch.R;
import com.wurq.routerarch.module.splashpage.presenter.SplashPresenter;

public class SplashActivity extends BaseActivity<SplashPresenter> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_splash);
        presenter.init();
    }

    @Override
    protected void initPresenter() {
        presenter = new SplashPresenter(this);
    }
}
