package com.korzh.fragmentswithtabs.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.korzh.fragmentswithtabs.R;
import com.korzh.fragmentswithtabs.fragment.RootFragment;

/**
 * Created by akorzh on 01.06.2017.
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_content, RootFragment.newInstance())
                .commit();
    }
}
