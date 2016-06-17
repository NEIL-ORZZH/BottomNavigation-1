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

    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    private boolean mSmoothly;
    private OnPageChangeListener mListener;
    private List<GradientTabView> mTabViews;
    private boolean mIsClick;

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

    public void setCurrentItem(int item) {
        PagerAdapter apdater = mViewPager.getAdapter();
        if (apdater != null && apdater.getCount() > item)
            mViewPager.setCurrentItem(item, mSmoothly);

        highlight(item);
    }

    public void setScrollSmoothly(boolean smoothly) {
        mSmoothly = smoothly;
    }

    @Override
    public void onClick(View v) {
        mIsClick = true;
        int pos = (int) v.getTag();
        highlight(pos);
        setCurrentItem(pos);
    }

    private void init(AttributeSet attrs) {
        setOrientation(LinearLayout.VERTICAL);
        initView();
        initAttrs(attrs);
        initListener();
    }

    private void initView() {
        LayoutInflater.from(getContext()).inflate(R.layout.bottom_navigation, this, true);
        mViewPager = (ViewPager) findViewById(R.id.vp);
        mTabLayout = (TabLayout) findViewById(R.id.tabLayout);
    }

    private void initAttrs(AttributeSet attrs) {
        Resources res = getResources();

        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.BottomNavigation);
        mSmoothly = a.getBoolean(R.styleable.BottomNavigation_bn_tab_vp_scroll_smoothly, res.getBoolean(R.bool.default_nav_scroll_smoothly));
        mViewPager.setOffscreenPageLimit(a.getInteger(R.styleable.BottomNavigation_bn_tab_vp_limit, res.getInteger(R.integer.default_vp_limit)));
        a.recycle();
    }

    private void initListener() {
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (mListener != null)
                    mListener.onPageScrolled(position, positionOffset, positionOffsetPixels);

                if (!mIsClick && positionOffset > 0) {
                    mTabViews.get(position).getImageView().setAlpha(1 - positionOffset);
                    mTabViews.get(position).getTextView().setAlpha(1 - positionOffset);
                    mTabViews.get(position + 1).getImageView().setAlpha(positionOffset);
                    mTabViews.get(position + 1).getTextView().setAlpha(positionOffset);
                }
            }

            @Override
            public void onPageSelected(int position) {
                if (mListener != null)
                    mListener.onPageSelected(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                if (mListener != null)
                    mListener.onPageScrollStateChanged(state);

                if (state == ViewPager.SCROLL_STATE_IDLE)
                    mIsClick = false;
            }
        });
    }

    private void highlight(int pos) {
        for (GradientTabView v : mTabViews)
            v.reset();

        mTabViews.get(pos).highlight();
    }
}
