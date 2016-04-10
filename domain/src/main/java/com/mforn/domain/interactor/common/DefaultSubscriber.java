package com.mforn.domain.interactor.common;


import rx.Subscriber;

/**
 * Default subscriber base abstract class to be used when only one element is emitted
 */
public abstract class DefaultSubscriber<T> extends Subscriber<T> {
    protected T result;

    @Override
    public void onCompleted() {
        if (result != null) {
            onSuccess(result);
        } else {
            onFailure(new Exception("Response object is null"));
        }
    }

    @Override
    public void onError(Throwable e) {
        onFailure(e);
    }

    @Override
    public void onNext(T t) {
        result = t;
    }

    protected abstract void onSuccess(T t);

    protected abstract void onFailure(Throwable exception);
}
