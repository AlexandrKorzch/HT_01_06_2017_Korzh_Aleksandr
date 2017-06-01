package com.korzh.fragmentswithtabs.util.rxbus.event;

/**
 * Created by akorzh on 01.06.2017.
 */

public class ChangeFragmentFromPagerEvent {

    private final String mTitle;
    private final int mFragmentNumber;

    public ChangeFragmentFromPagerEvent(String title, int fragmentNumber) {
        mTitle = title;
        mFragmentNumber = fragmentNumber;
    }

    public String getTitle() {
        return mTitle;
    }

    public int getFragmentNumber() {
        return mFragmentNumber;
    }
}
