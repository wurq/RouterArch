package com.wurq.routerarch.module.mainpage.presenter;

import android.view.View;

import com.wurq.base.basemodule.presenter.BaseFragmentPresenter;
import com.wurq.routerarch.module.mainpage.activity.ClassroomFragment;

/**
 * Created by wurongqiu on 2017/4/25.
 */

public class ClassroomPresenter extends BaseFragmentPresenter<ClassroomFragment> implements View.OnClickListener{
    public ClassroomPresenter(ClassroomFragment target) {
        super(target);
    }

    @Override
    public void onClick(View v) {
//        Intent intent = new Intent();
//        intent.setClass(target.getActivity(), MainTestActivity.class);
//        intent.setClassName("com.wurq.routerarch.localmodule","com.wurq.routerarch.localmodule.MainTestActivity");
//        target.startActivity(intent);

    }
}

