package com.jothaen.kitty.ui.favorites

import com.jothaen.kitty.common.Presenter
import com.jothaen.kitty.db.FavoritesStorage

class FavoritesPresenter(private val favoritesStorage: FavoritesStorage)
    : Presenter<FavoritesContract.View>(), FavoritesContract.Presenter {

    override fun onStart() = with(favoritesStorage.getAll()) {
        if (isEmpty()) view?.showEmptyListPlaceholder() else view?.showList(this)
    } ?: Unit

    override fun onRemoveClicked(id: String) {
        favoritesStorage.delete(id)
        view?.removeFromList(id)
        if (favoritesStorage.isEmpty()) view?.showEmptyListPlaceholder()
    }

}