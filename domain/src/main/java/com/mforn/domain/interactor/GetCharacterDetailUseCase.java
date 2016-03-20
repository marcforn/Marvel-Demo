package com.mforn.domain.interactor;

import com.mforn.domain.interactor.common.UseCase;
import com.mforn.domain.model.CharacterDetail;
import com.mforn.domain.repository.BackendRepository;
import com.mforn.domain.repository.DatabaseRepository;

import javax.inject.Inject;

import rx.Observable;
import rx.functions.Func1;

/**
 * This class is an implementation of {@link UseCase} that represents a use case for
 * retrieving character detail (Backend and Database requests).
 */
public class GetCharacterDetailUseCase extends UseCase {
    private final BackendRepository backendRepository;
    private final DatabaseRepository databaseRepository;
    private final long characterId;


    @Inject
    public GetCharacterDetailUseCase(BackendRepository backendRepository, DatabaseRepository databaseRepository, long characterId) {
        this.backendRepository = backendRepository;
        this.databaseRepository = databaseRepository;
        this.characterId = characterId;
    }

    @Override
    protected Observable buildUseCaseObservable() {
        // Marvel REST request
        Observable<CharacterDetail> observable = this.backendRepository.getCharacterDetail(characterId);

        // DDBB request to get isFavorite
        Func1<CharacterDetail, Observable<CharacterDetail>> flatmap = databaseRepository::isCharacterFavorite;

        return observable.flatMap(flatmap);
    }
}
