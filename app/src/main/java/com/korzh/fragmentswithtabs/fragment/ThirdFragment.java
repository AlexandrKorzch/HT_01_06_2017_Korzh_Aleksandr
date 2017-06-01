package com.korzh.fragmentswithtabs.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.korzh.fragmentswithtabs.R;

/**
 * Created by akorzh on 01.06.2017.
 */
public class ThirdFragment extends Fragment {

    public static ThirdFragment newInstance() {
        return new ThirdFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_therd, container, false);
    }
}
