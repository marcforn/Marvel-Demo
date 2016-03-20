package com.mforn.domain.interactor;

import com.mforn.domain.interactor.common.UseCase;
import com.mforn.domain.repository.DatabaseRepository;

import javax.inject.Inject;

import rx.Observable;

/**
 * This class is an implementation of {@link UseCase} that represents a use case for
 * retrieving favorite character collection
 */
public class GetCharacterFavListUseCase extends UseCase {
    private final DatabaseRepository mDatabaseRepository;


    @Inject
    public GetCharacterFavListUseCase(DatabaseRepository databaseRepository) {
        this.mDatabaseRepository = databaseRepository;
    }

    @Override
    protected Observable buildUseCaseObservable() {
        return this.mDatabaseRepository.getFavoriteCharacterList();
    }
}
