package com.wurq.routerarch.module.mainpage.activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wurq.base.basemodule.activity.BaseFragment;
import com.wurq.routerarch.R;
import com.wurq.routerarch.module.mainpage.presenter.RecommendPresenter;

/**
 * Created by wurongqiu on 2017/4/25.
 */

public class RecommendFragment  extends BaseFragment<RecommendPresenter> {
    @Override
    protected void initPresenter() {
        presenter = new RecommendPresenter(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_recommend, null);
        return view;
    }
}
