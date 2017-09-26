package com.wurq.routerarch.module.mainpage.activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.wurq.base.basemodule.activity.BaseFragment;
import com.wurq.routerarch.R;
import com.wurq.routerarch.module.mainpage.presenter.ClassroomPresenter;

/**
 * Created by wurongqiu on 2017/4/25.
 */

public class ClassroomFragment extends BaseFragment<ClassroomPresenter> {
    @Override
    protected void initPresenter() {
        presenter = new ClassroomPresenter(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_classroom, null);
        Button btn = (Button) view.findViewById(R.id.button);
        btn.setOnClickListener(presenter);
        return view;
    }
}
