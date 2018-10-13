package com.jothaen.kitty.ui.main

import com.jothaen.kitty.common.Presenter

class MainPresenter : Presenter<MainContract.View>(), MainContract.Presenter {
    override fun onStart() = onRandomClicked()
    override fun onRandomClicked() = view?.showRandomKittyScreen() ?: Unit
    override fun onFavoritesClicked() = view?.showFavoritesScreen() ?: Unit
}