package com.mforn.marvel.view;

/**
 * Interface representing a View that will use to load data.
 */
public interface LoadDataView<T> {

    void showLoading();

    void hideLoading();

    void showError(String message);

    void showData(T t);
}
