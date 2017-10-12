package com.example.marosu.secretchat.util

import org.reactivestreams.Subscriber
import org.reactivestreams.Subscription

/**
 * Created by Marius-Andrei Rosu on 10/11/2017.
 */
open class SafeSubscriber<T> : Subscriber<T> {

    override fun onError(t: Throwable?) {}

    override fun onSubscribe(s: Subscription?) {}

    override fun onComplete() {}

    override fun onNext(t: T) {}
}
