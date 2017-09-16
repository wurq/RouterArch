package com.wurq.base.view;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

public class FragmentAdapter extends FragmentPagerAdapter {

//	private String[] mFNames;
//	private Fragment[] mFragments;
//	private Context mContext;
//	private Bundle mBundle;
	List<Fragment> list ;//= new ArrayList<Fragment>(2);



	public FragmentAdapter(FragmentManager fm,List<Fragment> list) {
		super(fm);
		this.list = list;
	}
	//写构造方法，方便赋值调用

//	public FragmentAdapter(Context context, FragmentManager fm, String[] fNames) {
//		this(context, fm, fNames, null);
//	}
//
//	public FragmentAdapter(Context context, FragmentManager fm,
//                           String[] fNames, Bundle bundle) {
//		super(fm);
//		mContext = context;
//		mFNames = fNames;
//		mBundle = bundle;
//		mFragments = new Fragment[fNames.length];
//	}


//	@Override
//	public Fragment getItem(int arg0) {
//		return list.get(arg0);
//	}//根据Item的位置返回对应位置的Fragment，绑定item和Fragment
//
//	@Override
//	public int getCount() {
//		return list.size();
//	}//设置Item的数量
	@Override
	public Fragment getItem(int arg0) {
//		if (mFragments[arg0] == null) {
//			mFragments[arg0] = Fragment.instantiate(mContext, mFNames[arg0],
//					mBundle);
//		}
		return list.get(arg0);
	}

	@Override
	public int getCount() {
//		return mFNames.length;
		return list.size();//.length;
	}


//	public Fragment getFragmentByIndex(int index)
//	{
//		if(mFragments == null || mFragments.length == 0 || index > mFragments.length )
//		{
//			return null;
//		}
//		return mFragments[index];
//	}


}
