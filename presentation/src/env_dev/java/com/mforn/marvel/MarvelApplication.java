package com.mforn.marvel;

import android.util.Log;

import com.facebook.stetho.Stetho;
import com.squareup.leakcanary.LeakCanary;


public class MarvelApplication extends BaseApplication {
    private final static String TAG = "MarvelApplication ";

    @Override
    public void onCreate() {
        super.onCreate();

        initStetho();
        initLeakCanary();
    }

    private void initStetho() {
        if (BuildConfig.INIT_STETHO) {
            Log.i(TAG, "Stetho initialized");
            Stetho.initializeWithDefaults(this);
        }
    }

    private void initLeakCanary() {
        if (BuildConfig.INIT_LEAKCANARY) {
            Log.i(TAG, "LeakCanary initialized");
            LeakCanary.install(this);
        }
    }
}
