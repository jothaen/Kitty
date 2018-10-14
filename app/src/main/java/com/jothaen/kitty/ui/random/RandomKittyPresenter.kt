package com.jothaen.kitty.ui.random

import com.jothaen.kitty.common.Presenter
import com.jothaen.kitty.data.local.KittyImage
import com.jothaen.kitty.db.FavoritesStorage
import com.jothaen.kitty.usecase.GetRandomCatUseCase

class RandomKittyPresenter(private val getRandomCatUseCase: GetRandomCatUseCase,
                           private val favoritesStorage: FavoritesStorage)
    : Presenter<RandomKittyContract.View>(), RandomKittyContract.Presenter {

    private var randomKittyImage: KittyImage? = null

    override fun onStart() = getRandomCat()

    override fun onGetRandomKittyClicked() = getRandomCat()

    override fun onHeartClicked() = handleLikeStateChange() ?: Unit

    private fun getRandomCat() {
        view?.showProgress()
        disposables.add(getRandomCatUseCase.get().subscribe(::onGetKittySuccess, ::onGetKittyFailure))
    }

    private fun handleLikeStateChange() = randomKittyImage?.let {
        if (favoritesStorage.exists(it.id)) favoritesStorage.delete(it.id) else favoritesStorage.put(it)
        view?.setHeartButtonState(favoritesStorage.exists(it.id))
    }

    private fun onGetKittySuccess(data: KittyImage) = view?.run {
        randomKittyImage = data
        hideProgress()
        displayKittyImage(data.url)
        setHeartButtonState(data.isLiked)
    } ?: Unit

    private fun onGetKittyFailure(throwable: Throwable) = view?.run {
        hideProgress()
        showError()
    } ?: Unit
}