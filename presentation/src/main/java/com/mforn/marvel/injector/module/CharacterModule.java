package com.mforn.marvel.injector.module;

import com.mforn.domain.interactor.GetCharacterDetailUseCase;
import com.mforn.domain.interactor.GetCharacterFavDetailUseCase;
import com.mforn.domain.interactor.GetCharacterFavListUseCase;
import com.mforn.domain.interactor.GetCharacterListUseCase;
import com.mforn.domain.interactor.GetCharacterToggleFavoriteUseCase;
import com.mforn.domain.model.Character;
import com.mforn.domain.model.CharacterDetail;
import com.mforn.domain.repository.BackendRepository;
import com.mforn.domain.repository.DatabaseRepository;
import com.mforn.marvel.injector.PerActivity;

import dagger.Module;
import dagger.Provides;

/**
 * Dagger2 module to provide general Marvel Character constructors.
 */
@Module
public class CharacterModule {
    private long characterId;


    public CharacterModule() {
    }

    public CharacterModule(long characterId) {
        this.characterId = characterId;
    }

    @Provides
    @PerActivity
    GetCharacterListUseCase provideGetCharacterListUseCase(BackendRepository backendRepository) {
        return new GetCharacterListUseCase(backendRepository);
    }

    @Provides
    @PerActivity
    GetCharacterDetailUseCase provideGetCharacterDetailUseCase(BackendRepository backendRepository, DatabaseRepository databaseRepository) {
        return new GetCharacterDetailUseCase(backendRepository, databaseRepository, characterId);
    }

    @Provides
    @PerActivity
    GetCharacterFavListUseCase provideGetCharacterFavListUseCase(DatabaseRepository databaseRepository) {
        return new GetCharacterFavListUseCase(databaseRepository);
    }

    @Provides
    @PerActivity
    GetCharacterFavDetailUseCase provideGetCharacterFavDetailUseCase(DatabaseRepository databaseRepository) {
        return new GetCharacterFavDetailUseCase(databaseRepository, characterId);
    }

    @Provides
    @PerActivity
    GetCharacterToggleFavoriteUseCase provideGetCharacterToggleFavoriteUseCase(DatabaseRepository databaseRepository) {
        return new GetCharacterToggleFavoriteUseCase(databaseRepository);
    }
}
