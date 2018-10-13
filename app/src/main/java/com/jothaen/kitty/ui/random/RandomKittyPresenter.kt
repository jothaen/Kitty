package com.jothaen.kitty.ui.random

import com.jothaen.kitty.common.Presenter
import com.jothaen.kitty.data.remote.KittyImageDto
import com.jothaen.kitty.usecase.GetRandomCatUseCase

class RandomKittyPresenter(private val getRandomCatUseCase: GetRandomCatUseCase)
    : Presenter<RandomKittyContract.View>(), RandomKittyContract.Presenter {

    override fun onStart() = getRandomCat()

    override fun onGetRandomKittyClicked() = getRandomCat()

    private fun getRandomCat() {
        view?.showProgress()
        disposables.add(getRandomCatUseCase.get().subscribe(::onGetKittySuccess, ::onGetKittyFailure))
    }

    private fun onGetKittySuccess(data: KittyImageDto) = view?.run {
        hideProgress()
        displayKittyImage(data.url)
    } ?: Unit

    private fun onGetKittyFailure(throwable: Throwable) = view?.run {
        hideProgress()
        showError()
    } ?: Unit
}