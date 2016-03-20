package com.mforn.marvel.injector.component;

import com.mforn.domain.repository.BackendRepository;
import com.mforn.domain.repository.DatabaseRepository;
import com.mforn.marvel.BaseApplication;
import com.mforn.marvel.injector.module.ApplicationModule;
import com.mforn.marvel.view.activity.common.BaseActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Dagger2 Application Component.
 */
@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
    void inject(BaseActivity baseActivity);

    //Exposed to sub-graphs.
    BaseApplication app();

    BackendRepository backendRepository();

    DatabaseRepository databaseRepository();
}
