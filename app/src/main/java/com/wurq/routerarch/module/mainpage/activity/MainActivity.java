package com.wurq.routerarch.module.mainpage.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;

import com.wurq.base.basemodule.activity.BaseActivity;
import com.wurq.base.util.ResourcesUtil;
import com.wurq.base.view.FragmentAdapter;
import com.wurq.base.view.fragmenttabhost.FragmentTabHost;
import com.wurq.routerarch.R;
import com.wurq.routerarch.module.mainpage.presenter.MainPresenter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity<MainPresenter>
        implements
        ViewPager.OnPageChangeListener, TabHost.OnTabChangeListener{

    private static final String TAG = MainActivity.class.getName();

    private ViewPager mViewPager;
//    @ViewInject(R.id.pager)
//    private ViewPager mViewPager;

    private List<Fragment> list = new ArrayList<Fragment>();
    private FragmentTabHost mFragmentTabHost;

    private Class mFragmentClasses[] = {
            RecommendFragment.class,
            ClassroomFragment.class,
    };

    private final String[] sTabTexts = new String[]{
            ResourcesUtil.getString(R.string.fragment_recommend),
            ResourcesUtil.getString(R.string.fragment_classname)
    };

    private final int mTabIcons[] = {
            R.drawable.tab_home_btn,
            R.drawable.tab_view_btn
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initPage();
        initMonitor();
    }

    @Override
    protected void initPresenter() {
        presenter = new MainPresenter(this);
    }

    private final BroadcastReceiver mBatInfoReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(final Context context, final Intent intent) {
            final String action = intent.getAction();
            if (Intent.ACTION_SCREEN_OFF.equals(action)) {
                Log.d(TAG,"BroadcastReceiver ACTION_SCREEN_OFF");
            }
            if (Intent.ACTION_SCREEN_ON.equals(action)) {
                Log.d(TAG,"BroadcastReceiver ACTION_SCREEN_ON");
            }
            if (Intent.ACTION_CLOSE_SYSTEM_DIALOGS.equals(action)) {
                Log.d(TAG,"BroadcastReceiver ACTION_CLOSE_SYSTEM_DIALOGS");
            }
        }
    };
    private void initMonitor() {
        IntentFilter filter = new IntentFilter(Intent.ACTION_SCREEN_OFF);
        registerReceiver(mBatInfoReceiver, filter);

        IntentFilter onFilter = new IntentFilter(Intent.ACTION_SCREEN_ON);
        registerReceiver(mBatInfoReceiver, onFilter);

        IntentFilter homeFilter = new IntentFilter(Intent.ACTION_CLOSE_SYSTEM_DIALOGS);
        registerReceiver(mBatInfoReceiver, homeFilter);
    }
    private void removeMonitor() {
        if(mBatInfoReceiver != null){
            try {
                unregisterReceiver(mBatInfoReceiver);
            } catch (Exception e) {
                Log.e(TAG, "unregisterReceiver mBatInfoReceiver failure :"+e.getCause());
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
       removeMonitor();
    }
    public static void start(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    //    控件初始化控件
    private void initView() {
        mViewPager = (ViewPager) findViewById(R.id.pager);

        mViewPager.addOnPageChangeListener(this);

        mFragmentTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        mFragmentTabHost.setup(this, getSupportFragmentManager(), R.id.pager);
        mFragmentTabHost.setOnTabChangedListener(this);

        int count = sTabTexts.length;
        for (int i = 0; i < count; i++) {
            TabHost.TabSpec tabSpec = mFragmentTabHost.newTabSpec(sTabTexts[i])
                    .setIndicator(getTabItemView(i));
            mFragmentTabHost.addTab(tabSpec, mFragmentClasses[i], null);
            mFragmentTabHost.setTag(i);
            mFragmentTabHost.getTabWidget()
                    .getChildAt(i)
                    .setBackgroundResource(R.drawable.selector_tab_background);
        }
    }

    private void initPage() {
//        RecommendFragment recommendFragment = new RecommendFragment();
//        ClassroomFragment classroomFragment = new ClassroomFragment();

        list.add(new RecommendFragment());
        list.add(new ClassroomFragment());

        mViewPager.setAdapter(new FragmentAdapter(getSupportFragmentManager(), list));
        mFragmentTabHost.getTabWidget().setDividerDrawable(null);
    }

    /*
     * 单个tab单元的View展示
     *
     * @param index Tab序号
     * @return 单元Tab的View
     */
    private View getTabItemView(int index) {
        //将xml布局转换为view对象
        View view = layoutInflater.inflate(R.layout.tab_content, null);

        ImageView mImageView = (ImageView) view.findViewById(R.id.tab_imageview);
        TextView mTextView = (TextView) view.findViewById(R.id.tab_textview);
        mImageView.setBackgroundResource(mTabIcons[index]);
        mTextView.setText(sTabTexts[index]);
        return view;
    }

    @Override
    public void onPageScrollStateChanged(int arg0) {

    }

    @Override
    public void onPageScrolled(int arg0, float arg1, int arg2) {

    }

    @Override
    public void onPageSelected(int arg0) {
        TabWidget widget = mFragmentTabHost.getTabWidget();
        int oldFocusability = widget.getDescendantFocusability();
        widget.setDescendantFocusability(ViewGroup.FOCUS_BLOCK_DESCENDANTS);
        mFragmentTabHost.setCurrentTab(arg0);
        widget.setDescendantFocusability(oldFocusability);

    }

    @Override
    public void onTabChanged(String tabId) {
        int position = mFragmentTabHost.getCurrentTab();
        mViewPager.setCurrentItem(position);
    }
}
