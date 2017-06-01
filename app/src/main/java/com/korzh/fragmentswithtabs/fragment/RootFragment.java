package com.korzh.fragmentswithtabs.fragment;


import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.korzh.fragmentswithtabs.R;
import com.korzh.fragmentswithtabs.adapter.SectionsPagerAdapter;
import com.korzh.fragmentswithtabs.util.rxbus.MainBus;
import com.korzh.fragmentswithtabs.util.rxbus.event.ChangeFragmentFromDrawerEvent;
import com.korzh.fragmentswithtabs.util.rxbus.event.ChangeFragmentFromPagerEvent;
import com.korzh.fragmentswithtabs.view.DepthPageTransformer;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

/**
 * Created by akorzh on 01.06.2017.
 */
public class RootFragment extends Fragment {

    private String[] mTitles;

    public static RootFragment newInstance() {
        return new RootFragment();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Resources res = getResources();
        mTitles = res.getStringArray(R.array.tabs_titles_array);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_root, container, false);

        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(
                getActivity().getSupportFragmentManager(),  mTitles);

        final ViewPager viewPager = (ViewPager) view.findViewById(R.id.container);
        viewPager.setAdapter(sectionsPagerAdapter);
        viewPager.setPageTransformer(true, new DepthPageTransformer());
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}
            @Override
            public void onPageScrollStateChanged(int state) {}
            @Override
            public void onPageSelected(int position) {
                MainBus.getInstance().post(new ChangeFragmentFromPagerEvent(mTitles[position], position));
            }
        });

        MainBus.getInstance().post(new ChangeFragmentFromPagerEvent(
                mTitles[viewPager.getCurrentItem()],
                viewPager.getCurrentItem()));

        initOnFragmentChangedListener(viewPager);

        TabLayout tabLayout = (TabLayout) view.findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        return view;
    }

    private void initOnFragmentChangedListener(final ViewPager viewPager) {
        MainBus.getInstance()
                .getBusObservable()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Object>() {
                    @Override
                    public void call(Object o) {
                        if(o instanceof ChangeFragmentFromDrawerEvent){
                            viewPager.setCurrentItem(((ChangeFragmentFromDrawerEvent)o).getFragmentNumber());
                        }
                    }
                });
    }
}
