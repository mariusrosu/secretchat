package com.example.marosu.secretchat.base

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import io.reactivex.ObservableTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

/**
 * Created by Marius-Andrei Rosu on 8/29/2017.
 */
abstract class BasePresenter<V : BaseContract.View> : BaseContract.Presenter<V>, LifecycleObserver {
    var disposables = CompositeDisposable()
    var view: V? = null

    override fun attachView(view: V) {
        this.view = view
    }

    override fun detachView() {
        view = null
        disposables.dispose()
    }

    override fun isViewAttached() = view != null

    override fun attachLifecycle(lifecycle: Lifecycle) {
        lifecycle.addObserver(this)
    }

    override fun detachLifecycle(lifecycle: Lifecycle) {
        lifecycle.removeObserver(this)
    }

    override fun onPresenterDestroy() {
        //Implement this to clean up the presenter
    }

    protected fun <T> applySchedulers(): ObservableTransformer<T, T> = ObservableTransformer { observable ->
        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
    }
}
