package com.chrischeng.bottomnavigation;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class BottomNavigation extends LinearLayout implements View.OnClickListener {

    private NoScrollViewPager mViewPager;
    private TabLayout mTabLayout;
    private OnPageChangeListener mListener;
    private List<GradientTabView> mTabViews;
    private boolean mIgnoreScrolled;

    public BottomNavigation(Context context) {
        this(context, null);
    }

    public BottomNavigation(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public void setOnPageChangeListener(OnPageChangeListener listener) {
        mListener = listener;
    }

    public void setAdapter(CommonFragmentPagerAdapter adapter) {
        mViewPager.setAdapter(adapter);
        mTabLayout.setupWithViewPager(mViewPager);
        mTabViews = new ArrayList<>();

        for (int i = 0; i < mTabLayout.getTabCount(); i++) {
            TabLayout.Tab tab = mTabLayout.getTabAt(i);
            if (tab != null) {
                GradientTabView tabView = adapter.getTabView(i);
                tabView.setTag(i);
                tabView.setOnClickListener(this);
                mTabViews.add(tabView);
                tab.setCustomView(tabView);
            }
        }

        setCurrentItem(0);
    }

    public int getCurrentItem() {
        return mViewPager.getCurrentItem();
    }

    public void setCurrentItem(int item) {
        PagerAdapter apdater = mViewPager.getAdapter();
        if (apdater != null && apdater.getCount() > item) {
            mIgnoreScrolled = true;
            highlight(item);
            mViewPager.setCurrentItem(item, mViewPager.getScrollable());
        }
    }

    public void setScrollable(boolean scrollable) {
        mViewPager.setScrollable(scrollable);
    }

    public void setOffScreenPageLimit(int limit) {
        mViewPager.setOffscreenPageLimit(limit);
    }

    @Override
    public void onClick(View v) {
        setCurrentItem((int) v.getTag());
    }

    private void init(AttributeSet attrs) {
        setOrientation(LinearLayout.VERTICAL);
        initView();
        initAttrs(attrs);
        initListener();
    }

    private void initView() {
        LayoutInflater.from(getContext()).inflate(R.layout.bottom_navigation, this, true);
        mViewPager = (NoScrollViewPager) findViewById(R.id.vp);
        mTabLayout = (TabLayout) findViewById(R.id.tabLayout);
    }

    private void initAttrs(AttributeSet attrs) {
        Resources res = getResources();

        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.BottomNavigation);
        mViewPager.setOffscreenPageLimit(a.getInteger(R.styleable.BottomNavigation_bn_tab_vp_limit,
                res.getInteger(R.integer.default_vp_limit)));
        a.recycle();
    }

    private void initListener() {
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (positionOffset == 0f)
                    mIgnoreScrolled = false;

                if (!mIgnoreScrolled && positionOffset > 0) {
                    mTabViews.get(position).getImageView().setAlpha(1 - positionOffset);
                    mTabViews.get(position).getTextView().setAlpha(1 - positionOffset);
                    mTabViews.get(position + 1).getImageView().setAlpha(positionOffset);
                    mTabViews.get(position + 1).getTextView().setAlpha(positionOffset);
                }

                if (mListener != null)
                    mListener.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {
                if (mListener != null)
                    mListener.onPageSelected(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                if (state == ViewPager.SCROLL_STATE_IDLE) {
                    mIgnoreScrolled = false;
                    highlight(mViewPager.getCurrentItem());
                }

                if (mListener != null)
                    mListener.onPageScrollStateChanged(state);
            }
        });
    }

    private void highlight(int pos) {
        for (GradientTabView v : mTabViews)
            v.reset();

        mTabViews.get(pos).highlight();
    }
}
