package com.jothaen.kitty.common

import io.reactivex.disposables.CompositeDisposable

interface BaseView

interface BasePresenter<T: BaseView> {
    fun bind(view: T)
    fun unbind()
    fun destroy()
}

abstract class Presenter<T: BaseView> : BasePresenter<T> {

    protected val disposables = CompositeDisposable()
    protected var view: T? = null

    override fun bind(view: T) {
        this.view = view
    }

    override fun unbind() {
        this.view = null
    }

    override fun destroy() {
        disposables.clear()
        unbind()
    }

}