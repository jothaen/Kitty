package com.jothaen.kitty.ui.main

import com.jothaen.kitty.common.BasePresenter
import com.jothaen.kitty.common.BaseView

interface MainContract {

    interface View : BaseView {
        fun showRandomKittyScreen()
        fun showFavoritesScreen()
    }

    interface Presenter: BasePresenter<View> {
        fun onStart()
        fun onRandomClicked()
        fun onFavoritesClicked()
    }
}