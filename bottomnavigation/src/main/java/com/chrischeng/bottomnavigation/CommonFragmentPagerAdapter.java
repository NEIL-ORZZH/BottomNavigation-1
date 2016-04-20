package com.chrischeng.bottomnavigation;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CommonFragmentPagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> mFragments;

    public CommonFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public CommonFragmentPagerAdapter(FragmentManager fm, Fragment... fragments) {
        super(fm);
        init(fragments);
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

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    private void init(Fragment... fragments) {
        mFragments = new ArrayList<>();
        addFragments(fragments);
    }
}
