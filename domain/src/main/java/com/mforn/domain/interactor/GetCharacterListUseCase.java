package com.mforn.domain.interactor;

import com.mforn.domain.interactor.common.UseCase;
import com.mforn.domain.repository.BackendRepository;

import javax.inject.Inject;

import rx.Observable;

/**
 * This class is an implementation of {@link com.mforn.domain.interactor.common.UseCase} that represents a use case for
 * retrieving character collection
 */
public class GetCharacterListUseCase extends UseCase {
    private final BackendRepository backendRepository;
    private int page;


    @Inject
    public GetCharacterListUseCase(BackendRepository backendRepository) {
        this.backendRepository = backendRepository;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    @Override
    protected Observable buildUseCaseObservable() {
        return this.backendRepository.getCharacters(page);
    }
}
