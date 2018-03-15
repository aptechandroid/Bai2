package vn.uits.bai2;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

/**
 * Copyright © 2017 BAP CO., LTD
 * Created by PHUQUY on 3/7/18.
 */

public class MyThread extends Thread {

    private static final String TAG = MyThread.class.getSimpleName();

    private Handler mHandler;

    @Override
    public void run() {
        System.out.println("My Thread Running ");
        Looper.prepare();
        mHandler = new Handler() {

            @Override
            public void handleMessage(Message msg) {
                Log.d(TAG, "handleMessage: ");

            }
        };

        Looper.loop();
    }
}
