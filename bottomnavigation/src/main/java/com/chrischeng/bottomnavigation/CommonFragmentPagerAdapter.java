package com.chrischeng.bottomnavigation;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CommonFragmentPagerAdapter extends FragmentPagerAdapter {

    private Context mContext;
    private List<Fragment> mFragments = new ArrayList<>();
    private int[] mBotImageResIds;
    private int[] mTopImageResIds;
    private int[] mTextResIds;
    private int[] mTextColors;

    public CommonFragmentPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    public CommonFragmentPagerAdapter(Context context, FragmentManager fm, Fragment... fragments) {
        super(fm);
        mContext = context;
        init(fragments);
    }

    public GradientTabView getTabView(int pos) {
        GradientTabView v = new GradientTabView(mContext);
        v.setImageResources(mBotImageResIds[pos], mTopImageResIds[pos]);
        v.setText(mTextResIds[pos]);
        if (mTextColors.length >= 2)
            v.setTextColors(mTextColors[0], mTextColors[1]);
        return v;
    }

    public void addFragments(Fragment... fragments) {
        mFragments.addAll(Arrays.asList(fragments));
    }

    public void addFragment(Fragment fragment) {
        mFragments.add(fragment);
    }

    public void addFragments(Class... classes) {
        for (Class cls : classes)
            addFragment(cls);
    }

    public void addFragment(Class cls) {
        try {
            Class<?> clazz = Class.forName(cls.getName());
            mFragments.add((Fragment) clazz.newInstance());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setResources(int[] botImageResIds, int[] topImageResIds, int[] textResIds, int[] textColors) {
        mBotImageResIds = botImageResIds;
        mTopImageResIds = topImageResIds;
        mTextResIds = textResIds;
        mTextColors = textColors;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    private void init(Fragment... fragments) {
        addFragments(fragments);
    }
}
