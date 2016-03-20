package com.mforn.marvel.injector.component;


import com.mforn.marvel.injector.PerActivity;
import com.mforn.marvel.injector.module.ActivityModule;
import com.mforn.marvel.injector.module.CharacterModule;
import com.mforn.marvel.view.fragments.CharacterDetailFragment;
import com.mforn.marvel.view.fragments.CharacterFavDetailFragment;
import com.mforn.marvel.view.fragments.CharacterFavListFragment;
import com.mforn.marvel.view.fragments.CharacterListFragment;

import dagger.Component;

/**
 * Dagger2 Marvel Character Component
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = {ActivityModule.class, CharacterModule.class})
public interface CharacterComponent extends ActivityComponent {

    // Marvel requests
    void inject(CharacterListFragment characterListFragment);

    void inject(CharacterDetailFragment characterDetailFragment);

    // Database requests
    void inject(CharacterFavListFragment characterFavListFragment);

    void inject(CharacterFavDetailFragment characterFavDetailFragment);

}
