package com.mforn.marvel.view.activity.interfaces;

/**
 * Interface to communicate Fragment to parent ListActivity
 */
public interface ListActivityInterface {

    void showSnackBarMessage(String message);

    void navigateToActivityDetail(Class activity, int flowCode, long characterId);

}
