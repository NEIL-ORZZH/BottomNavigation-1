package com.chrischeng.bottomnavigation;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

public class BottomNavigation extends LinearLayout {

    private ViewPager mViewPager;
    private TabLayout mTabLayout;

    public BottomNavigation(Context context) {
        this(context, null);
    }

    public BottomNavigation(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public void setAdapter(CommonFragmentPagerAdapter adapter) {
        mViewPager.setAdapter(adapter);
        mTabLayout.setupWithViewPager(mViewPager);

        for (int i = 0; i < mTabLayout.getTabCount(); i++) {
            TabLayout.Tab tab = mTabLayout.getTabAt(i);
//            if (tab != null)
//                tab.setCustomView(adapter.getTabView(i));
        }
    }

    public void setCurrentItem(int item) {
        PagerAdapter apdater = mViewPager.getAdapter();
        if (apdater != null && apdater.getCount() > item)
            mViewPager.setCurrentItem(item);
    }

    private void init() {
        setOrientation(LinearLayout.VERTICAL);
        findView();
    }

    private void findView() {
        View v = LayoutInflater.from(getContext()).inflate(R.layout.bottom_navigation, this, true);
        mViewPager = (ViewPager) v.findViewById(R.id.vp);
        mTabLayout = (TabLayout) v.findViewById(R.id.tabLayout);
    }
}
