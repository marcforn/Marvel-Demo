package com.mforn.marvel.injector.module;

import com.mforn.data.backend.BackendDataSource;
import com.mforn.data.database.DatabaseDataSource;
import com.mforn.data.database.openhelper.DatabaseHelper;
import com.mforn.domain.repository.BackendRepository;
import com.mforn.domain.repository.DatabaseRepository;
import com.mforn.marvel.BaseApplication;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Dagger2 module to provide general app constructors
 */
@Module
public class ApplicationModule {

    private final BaseApplication mApplication;


    public ApplicationModule(BaseApplication application) {
        this.mApplication = application;
    }

    @Provides
    @Singleton
    BaseApplication provideBaseApplicationContext() {
        return mApplication;
    }

    @Provides
    @Singleton
    BackendRepository provideBackendRepository(BackendDataSource backendDataSource) {
        return backendDataSource;
    }

    @Provides
    @Singleton
    DatabaseRepository provideDatabaseRepository(DatabaseDataSource databaseDataSource) {
        return databaseDataSource;
    }

    @Provides
    @Singleton
    DatabaseHelper getDatabaseHelper(BaseApplication application) {
        return new DatabaseHelper(application);
    }
}
