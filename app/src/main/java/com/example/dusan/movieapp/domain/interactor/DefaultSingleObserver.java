package com.example.dusan.movieapp.domain.interactor;

import io.reactivex.observers.DisposableSingleObserver;

public class DefaultSingleObserver<T> extends DisposableSingleObserver<T> {

    @Override
    public void onSuccess(T t) {
        // no-op by default.
    }

    @Override
    public void onError(Throwable e) {
        // no-op by default.
    }
}
