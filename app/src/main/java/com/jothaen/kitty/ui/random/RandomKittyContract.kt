package com.jothaen.kitty.ui.random

import com.jothaen.kitty.common.BasePresenter
import com.jothaen.kitty.common.BaseView

interface RandomKittyContract {

    interface View: BaseView {
        fun showProgress()
        fun hideProgress()
        fun showError()
        fun displayKittyImage(url: String)
    }

    interface Presenter: BasePresenter<View> {
        fun onStart()
        fun onGetRandomKittyClicked()
    }
}