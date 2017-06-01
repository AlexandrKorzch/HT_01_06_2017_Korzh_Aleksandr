package com.korzh.fragmentswithtabs.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.korzh.fragmentswithtabs.R;
import com.korzh.fragmentswithtabs.fragment.RootFragment;
import com.korzh.fragmentswithtabs.util.rxbus.MainBus;
import com.korzh.fragmentswithtabs.util.rxbus.event.ChangeFragmentFromDrawerEvent;
import com.korzh.fragmentswithtabs.util.rxbus.event.ChangeFragmentFromPagerEvent;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

import static com.korzh.fragmentswithtabs.general.Const.FIRST_FRAGMENT;
import static com.korzh.fragmentswithtabs.general.Const.SECOND_FRAGMENT;
import static com.korzh.fragmentswithtabs.general.Const.THIRD_FRAGMENT;

/**
 * Created by akorzh on 01.06.2017.
 */
public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout mDrawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawer.addDrawerListener(toggle);
        toggle.syncState();

        final NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        initOnFragmentChangeListener(toolbar, navigationView);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_content, RootFragment.newInstance())
                .commit();
    }

    private void initOnFragmentChangeListener(final Toolbar toolbar, final NavigationView navigationView) {
        MainBus.getInstance()
                .getBusObservable()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Object>() {
                    @Override
                    public void call(Object o) {
                        if(o instanceof ChangeFragmentFromPagerEvent){
                            toolbar.setTitle(((ChangeFragmentFromPagerEvent)o).getTitle());
                            navigationView
                                    .getMenu()
                                    .getItem(((ChangeFragmentFromPagerEvent)o).getFragmentNumber())
                                    .setChecked(true);
                        }
                    }
                });
    }

    @Override
    public void onBackPressed() {
        if (mDrawer.isDrawerOpen(GravityCompat.START)) {
            mDrawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        return id == R.id.action_settings || super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        int fragmentNumber = 0;

        if (id == R.id.menu_first) {
            fragmentNumber = FIRST_FRAGMENT;
        } else if (id == R.id.menu_second) {
            fragmentNumber = SECOND_FRAGMENT;
        } else if (id == R.id.menu_third) {
            fragmentNumber = THIRD_FRAGMENT;
        }

        MainBus.getInstance().post(new ChangeFragmentFromDrawerEvent(fragmentNumber));
        mDrawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
