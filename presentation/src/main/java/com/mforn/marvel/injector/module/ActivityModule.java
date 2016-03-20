package com.mforn.marvel.injector.module;

import android.app.Activity;

import com.mforn.marvel.injector.PerActivity;

import dagger.Module;
import dagger.Provides;

/**
 * Dagger2 module to provide general activity constructors
 */
@Module
public class ActivityModule {

    private final Activity activity;


    public ActivityModule(Activity activity) {
        this.activity = activity;
    }

    @Provides
    @PerActivity
    Activity activity() {
        return this.activity;
    }
}
