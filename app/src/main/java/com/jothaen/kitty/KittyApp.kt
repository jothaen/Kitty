package com.jothaen.kitty

import android.app.Application
import com.jothaen.kitty.di.diModule
import org.koin.android.ext.android.startKoin

class KittyApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin(this, listOf(diModule))
    }
}