package com.example.marosu.secretchat.base

import android.arch.lifecycle.ViewModel

/**
 * Created by Marius-Andrei Rosu on 8/29/2017.
 */
class BaseViewModel<V : BaseContract.View, P : BaseContract.Presenter<V>> : ViewModel() {
    var presenter: P? = null

    override fun onCleared() {
        super.onCleared()
        presenter?.onPresenterDestroy()
        presenter = null
    }
}
