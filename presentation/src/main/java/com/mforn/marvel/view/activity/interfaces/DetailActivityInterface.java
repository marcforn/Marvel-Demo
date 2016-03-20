package com.mforn.marvel.view.activity.interfaces;

/**
 * Interface to communicate Fragment to parent DetailActivity
 */
public interface DetailActivityInterface {

    void setToolbarTitle(String title);

    void showSnackBarMessage(boolean isError, String message);
}
