package com.jothaen.kitty.extensions

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

fun <T> Observable<T>.applyStandardSchedulers() = this
        .subscribeOn(Schedulers.newThread())
        .observeOn(AndroidSchedulers.mainThread())

