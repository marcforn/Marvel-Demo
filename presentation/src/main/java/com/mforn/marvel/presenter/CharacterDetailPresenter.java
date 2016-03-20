package com.mforn.marvel.presenter;

import android.support.annotation.NonNull;

import com.mforn.domain.interactor.GetCharacterDetailUseCase;
import com.mforn.domain.interactor.GetCharacterToggleFavoriteUseCase;
import com.mforn.domain.interactor.common.DefaultSubscriber;
import com.mforn.domain.model.CharacterDetail;
import com.mforn.marvel.mapper.CharacterModelDataMapper;
import com.mforn.marvel.model.CharacterDetailModel;
import com.mforn.marvel.presenter.common.Presenter;
import com.mforn.marvel.view.CharacterDetailDataView;

import javax.inject.Inject;

/**
 * {@link Presenter} that controls communication between Character Detail views and models of the presentation
 * layer.
 */
public class CharacterDetailPresenter implements Presenter {
    private CharacterDetailDataView<CharacterDetailModel> loadDataView;

    private final GetCharacterDetailUseCase getCharacterDetailUseCase;
    private final GetCharacterToggleFavoriteUseCase getCharacterToggleFavoriteUseCase;
    private final CharacterModelDataMapper characterModelDataMapper;

    private CharacterDetail characterDetail;


    @Inject
    public CharacterDetailPresenter(
            GetCharacterDetailUseCase getCharacterDetailUseCase,
            GetCharacterToggleFavoriteUseCase getCharacterToggleFavoriteUseCase,
            CharacterModelDataMapper characterModelDataMapper) {
        this.getCharacterDetailUseCase = getCharacterDetailUseCase;
        this.getCharacterToggleFavoriteUseCase = getCharacterToggleFavoriteUseCase;
        this.characterModelDataMapper = characterModelDataMapper;
    }

    public void setView(CharacterDetailDataView<CharacterDetailModel> view) {
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
        this.getCharacterDetailUseCase.unsubscribe();
        this.getCharacterToggleFavoriteUseCase.unsubscribe();
        this.loadDataView = null;
    }

    /**
     * Initializes the presenter by start retrieving data
     */
    public void getUserDetails() {
        this.showViewLoading();

        DefaultSubscriber<CharacterDetail> subscriber = new DefaultSubscriber<CharacterDetail>() {
            @Override
            protected void onSuccess(@NonNull CharacterDetail characterDetail) {
                CharacterDetailPresenter.this.setData(characterDetail);
                CharacterDetailPresenter.this.hideViewLoading();
                CharacterDetailPresenter.this.showResultInView(characterDetail);
            }

            @Override
            protected void onFailure(Throwable exception) {
                CharacterDetailPresenter.this.setData(null);
                CharacterDetailPresenter.this.hideViewLoading();
                CharacterDetailPresenter.this.showErrorMessage(exception.getMessage());
            }
        };

        this.getCharacterDetailUseCase.execute(subscriber);
    }

    public void toggleFavorite() {
        final CharacterDetail characterDetail = this.getData();

        DefaultSubscriber<Boolean> subscriber = new DefaultSubscriber<Boolean>() {
            @Override
            protected void onSuccess(@NonNull Boolean aBoolean) {
                characterDetail.setFavorite(aBoolean);
                CharacterDetailPresenter.this.setData(characterDetail);
                CharacterDetailPresenter.this.showIsFavorite(aBoolean);
            }

            @Override
            protected void onFailure(Throwable exception) {
                characterDetail.setFavorite(false);
                CharacterDetailPresenter.this.setData(characterDetail);
                CharacterDetailPresenter.this.showIsFavorite(false);
            }
        };

        this.getCharacterToggleFavoriteUseCase.setCharacterDetail(this.getData());
        this.getCharacterToggleFavoriteUseCase.execute(subscriber);
    }

    /**
     * Handle Character data
     */
    private void setData(CharacterDetail characterDetail) {
        this.characterDetail = characterDetail;
    }

    private CharacterDetail getData() {
        return this.characterDetail;
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

    private void showResultInView(CharacterDetail characterDetail) {
        this.loadDataView.showData(characterModelDataMapper.transform(characterDetail));
    }

    private void showIsFavorite(boolean isFavorite) {
        this.loadDataView.setFavorite(isFavorite);
    }
}
