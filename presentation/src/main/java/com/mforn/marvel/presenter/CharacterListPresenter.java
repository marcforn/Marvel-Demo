package com.mforn.marvel.presenter;

import android.support.annotation.NonNull;

import com.mforn.domain.interactor.GetCharacterListUseCase;
import com.mforn.domain.interactor.common.DefaultSubscriber;
import com.mforn.domain.model.Character;
import com.mforn.marvel.mapper.CharacterModelDataMapper;
import com.mforn.marvel.model.CharacterModel;
import com.mforn.marvel.presenter.common.Presenter;
import com.mforn.marvel.view.LoadDataView;

import java.util.List;

import javax.inject.Inject;

/**
 * {@link Presenter} that controls communication between Character collection views and models of the presentation
 * layer.
 */
public class CharacterListPresenter implements Presenter {
    private LoadDataView<List<CharacterModel>> loadDataView;

    private final GetCharacterListUseCase getCharacterListUseCase;
    private final CharacterModelDataMapper characterModelDataMapper;

    private int page;

    @Inject
    public CharacterListPresenter(GetCharacterListUseCase getCharacterListUseCase, CharacterModelDataMapper characterModelDataMapper) {
        this.getCharacterListUseCase = getCharacterListUseCase;
        this.characterModelDataMapper = characterModelDataMapper;
    }

    public void setView(LoadDataView<List<CharacterModel>> view) {
        this.loadDataView = view;
    }

    /**
     * Presenter Lifecycle
     */
    @Override
    public void resume() {
    }

    @Override
    public void pause() {
    }

    @Override
    public void destroy() {
        this.getCharacterListUseCase.unsubscribe();
        this.loadDataView = null;
    }

    /**
     * Initializes the presenter by start retrieving data
     */
    public void initialize() {
        this.showViewLoading();
        this.getUserDetails(0);
    }

    public void loadMoreCharacters() {
        this.showViewLoading();
        this.getUserDetails(++page);
    }

    private void getUserDetails(int page) {
        DefaultSubscriber<List<Character>> subscriber = new DefaultSubscriber<List<Character>>() {
            @Override
            protected void onSuccess(@NonNull List<Character> characterList) {
                CharacterListPresenter.this.hideViewLoading();
                CharacterListPresenter.this.showResultInView(characterList);
            }

            @Override
            protected void onFailure(Throwable exception) {
                CharacterListPresenter.this.hideViewLoading();
                CharacterListPresenter.this.showErrorMessage(exception.getMessage());
            }
        };

        this.getCharacterListUseCase.setPage(page);
        this.getCharacterListUseCase.execute(subscriber);
    }

    /**
     * View communication interface
     */
    private void showViewLoading() {
        this.loadDataView.showLoading();
    }

    private void hideViewLoading() {
        this.loadDataView.hideLoading();
    }

    private void showErrorMessage(String errorMessage) {
        this.loadDataView.showError(errorMessage);
    }

    private void showResultInView(List<Character> characterList) {
        this.loadDataView.showData(characterModelDataMapper.transform(characterList));
    }
}
