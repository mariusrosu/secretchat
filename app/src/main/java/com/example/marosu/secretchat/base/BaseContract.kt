package com.example.marosu.secretchat.base

import android.arch.lifecycle.Lifecycle

/**
 * Created by Marius-Andrei Rosu on 8/29/2017.
 */
interface BaseContract {
    interface View

    interface Presenter<V : View> {
        fun attachLifecycle(lifecycle: Lifecycle)

        fun detachLifecycle(lifecycle: Lifecycle)

        fun attachView(view: V)

        fun detachView()

        fun isViewAttached(): Boolean

        fun onPresenterDestroy()
    }
}
