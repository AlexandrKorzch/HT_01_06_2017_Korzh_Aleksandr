package com.korzh.fragmentswithtabs.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.korzh.fragmentswithtabs.fragment.FirstFragment;
import com.korzh.fragmentswithtabs.fragment.SecondFragment;
import com.korzh.fragmentswithtabs.fragment.ThirdFragment;

/**
 * Created by akorzh on 01.06.2017.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    private static final int FRAGMENT_COUNT = 3;
    private final String[] mTitles;

    public SectionsPagerAdapter(FragmentManager fm, String[] titles) {
        super(fm);
        mTitles = titles;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return FirstFragment.newInstance();
            case 1:
                return SecondFragment.newInstance();
            case 2: {
                return ThirdFragment.newInstance();
            }
        }
        return null;
    }

    @Override
    public int getCount() {
        return FRAGMENT_COUNT;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles[position];
    }
}