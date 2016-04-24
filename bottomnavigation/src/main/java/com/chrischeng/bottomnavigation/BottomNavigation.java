package com.chrischeng.bottomnavigation;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class BottomNavigation extends LinearLayout {

    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    private List<GradientTabView> mTabViews;

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
        mTabViews = new ArrayList<>();

        for (int i = 0; i < mTabLayout.getTabCount(); i++) {
            TabLayout.Tab tab = mTabLayout.getTabAt(i);
            if (tab != null) {
                GradientTabView tabView = adapter.getTabView(i);
                mTabViews.add(tabView);
                tab.setCustomView(tabView);
            }
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
        initListener();
    }

    private void findView() {
        View v = LayoutInflater.from(getContext()).inflate(R.layout.bottom_navigation, this, true);
        mViewPager = (ViewPager) v.findViewById(R.id.vp);
        mTabLayout = (TabLayout) v.findViewById(R.id.tabLayout);
    }

    private void initListener() {
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (positionOffset > 0) {
                    mTabViews.get(position).getImageView().setAlpha(1 - positionOffset);
                    mTabViews.get(position).getTextView().setAlpha(1 - positionOffset);
                    mTabViews.get(position + 1).getImageView().setAlpha(positionOffset);
                    mTabViews.get(position + 1).getTextView().setAlpha(positionOffset);
                }
            }

            @Override
            public void onPageSelected(int position) {
                highlight(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void highlight(int pos) {
        for (GradientTabView v : mTabViews)
            v.reset();

        mTabViews.get(pos).highlight();
    }
}
