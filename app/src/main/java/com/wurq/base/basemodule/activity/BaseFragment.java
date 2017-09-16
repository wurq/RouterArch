package com.wurq.base.basemodule.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.wurq.base.basemodule.presenter.BaseFragmentPresenter;


/**
 * Created by wurongqiu on 2017/4/25.
 */

public abstract class BaseFragment<T extends BaseFragmentPresenter> extends Fragment {

    public String mFragmentTitle = "";
    public boolean mIsCreated = false;
    //    protected FrameLayout rootView;
//    protected WeakReference<View> rootViewRef;
    protected T presenter;
    /**
     * 用于监听滑动的view
     */
    protected FrameLayout contentView;
    protected View touchView;
    //加载进度条
    private Activity mActivity;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (presenter != null) {
            presenter.onAttach();
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initPresenter();
        if (presenter != null) {
            presenter.onCreate();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        inflateRootView(inflater);
        if (presenter != null) {
            presenter.onCreateView();
        }
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mIsCreated = true;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (presenter != null) {
            presenter.onActivityCreated(savedInstanceState);
        }
    }

//    /**
//     * Inflate a content view for the activity.
//     *
//     * @param resId ID for an XML layout resource as the content view
//     */
//    public void setRealContentView(@LayoutRes int resId) {
//        getActivity().getLayoutInflater().inflate(resId, contentView);
//    }

    @Override
    public void onStart() {
        super.onStart();
        if (presenter != null) {
            presenter.onStart();
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mActivity = activity;
    }

    @Override
    public void onResume() {
        super.onResume();

        if(getUserVisibleHint()){
            onVisibilityChangedToUser(true, false);
        }
        if (presenter != null) {
            presenter.onResume();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if(getUserVisibleHint()){
            onVisibilityChangedToUser(false, false);
        }
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
    public void onDestroyView() {
        super.onDestroyView();
        if (presenter != null) {
            presenter.onDestroyView();
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
    public void onDetach() {
        super.onDetach();
        if (presenter != null) {
            presenter.onDetach();
        }
    }

    /**
     * 当Fragment对用户的可见性发生了改变的时候就会回调此方法
     *
     * @param isVisibleToUser                      true：用户能看见当前Fragment；false：用户看不见当前Fragment
     * @param isHappenedInSetUserVisibleHintMethod true：本次回调发生在setUserVisibleHintMethod方法里；false：发生在onResume或onPause方法里
     */
    public void onVisibilityChangedToUser(final boolean isVisibleToUser, final boolean isHappenedInSetUserVisibleHintMethod) {
        if (isVisibleToUser) {
        } else {
        }
    }

    /*此函数相当于onResume*/
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isResumed()) {
            onVisibilityChangedToUser(isVisibleToUser, true);
        }
    }


    abstract protected void initPresenter();

    protected void inflateRootView(LayoutInflater inflater) {
    }

    /**
     * 显示TOAST  id
     */
    public void showToast(final int resId) {
        if (resId > 0) {
            if (mActivity != null) {
                mActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
//                        new DialogBundle.ToastDialog.Builder(AppProfile.getContext()).setAlert(resId).build().show();
                    }
                });
            }

        }
    }

    /**
     * 显示TOAST  string
     */
    public void showToast(final String resStr) {
        if (!TextUtils.isEmpty(resStr)) {
            if (mActivity != null) {
                mActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
//                        new DialogBundle.ToastDialog.Builder(AppProfile.getContext()).setAlert(resStr).build().show();
                    }
                });
            }
        }
    }


}
