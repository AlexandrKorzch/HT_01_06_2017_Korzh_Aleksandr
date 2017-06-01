package com.korzh.fragmentswithtabs.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.korzh.fragmentswithtabs.fragment.FirstFragment;
import com.korzh.fragmentswithtabs.fragment.SecondFragment;
import com.korzh.fragmentswithtabs.fragment.ThirdFragment;

import static com.korzh.fragmentswithtabs.general.Const.FIRST_FRAGMENT;
import static com.korzh.fragmentswithtabs.general.Const.FRAGMENTS_COUNT;
import static com.korzh.fragmentswithtabs.general.Const.SECOND_FRAGMENT;
import static com.korzh.fragmentswithtabs.general.Const.THIRD_FRAGMENT;

/**
 * Created by akorzh on 01.06.2017.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    private final String[] mTitles;

    public SectionsPagerAdapter(FragmentManager fm, String[] titles) {
        super(fm);
        mTitles = titles;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case FIRST_FRAGMENT:
                return FirstFragment.newInstance();
            case SECOND_FRAGMENT:
                return SecondFragment.newInstance();
            case THIRD_FRAGMENT: {
                return ThirdFragment.newInstance();
            }
        }
        return null;
    }

    @Override
    public int getCount() {
        return FRAGMENTS_COUNT;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles[position];
    }
}