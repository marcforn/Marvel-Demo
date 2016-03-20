package com.mforn.marvel.presenter;

import android.support.annotation.NonNull;

import com.mforn.domain.interactor.GetCharacterFavListUseCase;
import com.mforn.domain.interactor.common.DefaultSubscriber;
import com.mforn.domain.model.Character;
import com.mforn.marvel.mapper.CharacterModelDataMapper;
import com.mforn.marvel.model.CharacterModel;
import com.mforn.marvel.presenter.common.Presenter;
import com.mforn.marvel.view.LoadDataView;

import java.util.List;

import javax.inject.Inject;

/**
 * {@link Presenter} that controls communication between Character favorite collection views and models of the presentation
 * layer.
 */
public class CharacterFavListPresenter implements Presenter {
    private LoadDataView<List<CharacterModel>> loadDataView;

    private final GetCharacterFavListUseCase getCharacterFavListUseCase;
    private final CharacterModelDataMapper characterModelDataMapper;


    @Inject
    public CharacterFavListPresenter(GetCharacterFavListUseCase getCharacterFavListUseCase, CharacterModelDataMapper characterModelDataMapper) {
        this.getCharacterFavListUseCase = getCharacterFavListUseCase;
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
        this.getCharacterFavListUseCase.unsubscribe();
        this.loadDataView = null;
    }

    /**
     * Initializes the presenter by start retrieving data
     */
    public void getUserDetails() {
        this.showViewLoading();

        DefaultSubscriber<List<Character>> subscriber = new DefaultSubscriber<List<Character>>() {
            @Override
            protected void onSuccess(@NonNull List<Character> characterList) {
                CharacterFavListPresenter.this.hideViewLoading();
                CharacterFavListPresenter.this.showResultInView(characterList);
            }

            @Override
            protected void onFailure(Throwable exception) {
                CharacterFavListPresenter.this.hideViewLoading();
                CharacterFavListPresenter.this.showErrorMessage(exception.getMessage());
            }
        };

        this.getCharacterFavListUseCase.execute(subscriber);
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
        if (characterList.isEmpty()) {
            this.loadDataView.showError("No favorites found. Please add one.");
        }

        this.loadDataView.showData(characterModelDataMapper.transform(characterList));
    }
}
