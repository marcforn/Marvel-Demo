package com.mforn.domain.interactor.common;

import rx.Subscriber;

/**
 * No operation subscriber abstract class (allows you to implemented required methods).
 */
public abstract class NoOperationSubscriber<T> extends Subscriber<T> {
    @Override
    public void onCompleted() {
        // no-op by default.
    }

    @Override
    public void onError(Throwable e) {
        // no-op by default.
    }

    @Override
    public void onNext(T t) {
        // no-op by default.
    }
}
