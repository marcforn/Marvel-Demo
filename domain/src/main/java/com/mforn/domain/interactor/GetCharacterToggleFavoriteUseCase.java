package com.mforn.domain.interactor;

import com.mforn.domain.interactor.common.UseCase;
import com.mforn.domain.model.CharacterDetail;
import com.mforn.domain.repository.DatabaseRepository;

import javax.inject.Inject;

import rx.Observable;

/**
 * This class is an implementation of {@link UseCase} that represents a use case for
 * toggling character favorite status.
 */
public class GetCharacterToggleFavoriteUseCase extends UseCase {
    private final DatabaseRepository databaseRepository;
    private CharacterDetail characterDetail;


    @Inject
    public GetCharacterToggleFavoriteUseCase(DatabaseRepository databaseRepository) {
        this.databaseRepository = databaseRepository;
    }

    public void setCharacterDetail(CharacterDetail characterDetail) {
        this.characterDetail = characterDetail;
    }

    @Override
    protected Observable buildUseCaseObservable() {
        return this.databaseRepository.toggleFavorite(characterDetail);
    }
}
