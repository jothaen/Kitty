package com.jothaen.kitty.ui.favorites

import com.jothaen.kitty.common.BasePresenter
import com.jothaen.kitty.common.BaseView
import com.jothaen.kitty.data.local.KittyImage

interface FavoritesContract {

    interface View: BaseView {
        fun showList(favorites: List<KittyImage>)
        fun showEmptyListPlaceholder()
        fun removeFromList(id: String)
    }

    interface Presenter: BasePresenter<View> {
        fun onStart()
        fun onRemoveClicked(id: String)
    }
}