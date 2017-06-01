package com.korzh.fragmentswithtabs.util.rxbus.event;

/**
 * Created by akorzh on 01.06.2017.
 */

public class ChangeFragmentFromDrawerEvent {

    private final int mFragmentNumber;

    public ChangeFragmentFromDrawerEvent(int fragmentNumber) {
        mFragmentNumber = fragmentNumber;
    }

    public int getFragmentNumber() {
        return mFragmentNumber;
    }
}
