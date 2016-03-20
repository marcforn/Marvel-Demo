package com.mforn.marvel;

import android.app.Application;

import com.mforn.marvel.injector.component.ApplicationComponent;
import com.mforn.marvel.injector.component.DaggerApplicationComponent;
import com.mforn.marvel.injector.module.ApplicationModule;

/**
 * Base Application class to handle dependency injection.
 */
public class BaseApplication extends Application {
    private ApplicationComponent mApplicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        this.initializeInjector();
    }

    private void initializeInjector() {
        this.mApplicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    public ApplicationComponent getApplicationComponent() {
        return this.mApplicationComponent;
    }
}
