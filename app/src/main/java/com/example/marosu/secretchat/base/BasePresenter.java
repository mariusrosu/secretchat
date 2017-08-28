package com.example.marosu.secretchat.base;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;

import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Marius-Andrei Rosu on 8/7/2017.
 */
public abstract class BasePresenter<V extends BaseContract.View> implements BaseContract.Presenter<V>, LifecycleObserver {
    protected CompositeDisposable disposables = new CompositeDisposable();
    private V view;

    @Override
    final public void attachView(V view) {
        this.view = view;
    }

    @Override
    final public void detachView() {
        view = null;
        disposables.dispose();
    }

    @Override
    final public boolean isViewAttached() {
        return view != null;
    }

    @Override
    final public V getView() {
        return view;
    }

    @Override
    final public void attachLifecycle(Lifecycle lifecycle) {
        lifecycle.addObserver(this);
    }

    @Override
    final public void detachLifecycle(Lifecycle lifecycle) {
        lifecycle.removeObserver(this);
    }

    @Override
    public void onPresenterDestroy() {
        //Implement this to clean up the presenter
    }

    protected <T> ObservableTransformer<T, T> applySchedulers() {
        return observable -> observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
