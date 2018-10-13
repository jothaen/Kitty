package com.jothaen.kitty.di

import com.jothaen.kitty.ui.main.MainContract
import com.jothaen.kitty.ui.main.MainPresenter
import org.koin.dsl.module.module

val diModule = module {
    factory<MainContract.Presenter> { MainPresenter() }
}