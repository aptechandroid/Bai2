package vn.uits.bai2;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import vn.uits.bai2.fragment.OneFragment;

/**
 * Copyright © 2017 BAP CO., LTD
 * Created by PHUQUY on 3/6/18.
 */

public class TowActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);
        replaceFragment(new OneFragment());
    }

    private void onThread() {
        Handler handler = new Handler(Looper.getMainLooper());
    }

    /**
     * add fragment
     *
     * @param fragment
     */
    private void addFragment(@NonNull Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.mFrameLayout, fragment)
                .disallowAddToBackStack()
                .commit();
    }

    /**
     * replace fragment
     *
     * @param fragment
     */
    private void replaceFragment(@NonNull Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.mFrameLayout, fragment)
                .addToBackStack(null)
                .commit();
    }
}
