package com.mforn.domain.interactor;

import com.mforn.domain.interactor.common.UseCase;
import com.mforn.domain.repository.DatabaseRepository;

import javax.inject.Inject;

import rx.Observable;

/**
 * This class is an implementation of {@link UseCase} that represents a use case for
 * retrieving favorite character detail.
 */
public class GetCharacterFavDetailUseCase extends UseCase {
    private final DatabaseRepository databaseRepository;
    private final long characterId;


    @Inject
    public GetCharacterFavDetailUseCase(DatabaseRepository databaseRepository, long characterId) {
        this.databaseRepository = databaseRepository;
        this.characterId = characterId;
    }

    @Override
    protected Observable buildUseCaseObservable() {
        return this.databaseRepository.getFavoriteCharacterDetail(characterId);
    }
}
