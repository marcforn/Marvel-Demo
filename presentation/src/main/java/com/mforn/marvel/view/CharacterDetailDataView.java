package com.mforn.marvel.view;

/**
 * Interface representing a Character Detail view that will use to load data.
 */
public interface CharacterDetailDataView<T> {

    void showLoading();

    void hideLoading();

    void showError(String message);

    void showData(T t);

    void setFavorite(boolean isFavorite);
}
