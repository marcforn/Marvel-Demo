package com.mforn.marvel.adapter;

/**
 * Interface to communicate adapter items to view
 *
 * @param <T>
 */
public interface RecyclerClickInterface<T> {

    void onItemClick(T t);
}
