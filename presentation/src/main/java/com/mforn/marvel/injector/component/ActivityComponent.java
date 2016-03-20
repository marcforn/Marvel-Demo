package com.mforn.marvel.injector.component;

import android.app.Activity;

import com.mforn.marvel.injector.PerActivity;
import com.mforn.marvel.injector.module.ActivityModule;

import dagger.Component;

/**
 * Dagger2 Base Activity Component.
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    //Exposed to sub-graphs.
    Activity activity();
}
