package com.jothaen.kitty.di

import android.content.Context
import com.google.gson.Gson
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import com.jothaen.kitty.data.remote.CatsApi
import com.jothaen.kitty.db.FavoritesStorage
import com.jothaen.kitty.db.SharedPrefsFavoritesStorage
import com.jothaen.kitty.ui.main.MainContract
import com.jothaen.kitty.ui.main.MainPresenter
import com.jothaen.kitty.ui.random.RandomKittyContract
import com.jothaen.kitty.ui.random.RandomKittyPresenter
import com.jothaen.kitty.usecase.GetRandomCatUseCase
import com.jothaen.kitty.usecase.GetRandomCatUseCaseImpl
import com.squareup.picasso.Picasso
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import java.util.concurrent.TimeUnit

val mainModule = module {
    factory<MainContract.Presenter> { MainPresenter() }
}

val networkModule = module {
    factory {
        Retrofit.Builder()
                .baseUrl("https://api.thecatapi.com/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(getOkHttpClient())
                .build()
    }

    factory { get<Retrofit>().create(CatsApi::class.java) }
    factory<GetRandomCatUseCase> { GetRandomCatUseCaseImpl(get(), get()) }
}

val randomModule = module {
    factory<RandomKittyContract.Presenter> { RandomKittyPresenter(get(), get()) }
    factory { Picasso.get() }
}

val dataModule = module {
    factory { androidContext().getSharedPreferences("favorites", Context.MODE_PRIVATE) }
    factory { Gson() }
    factory<FavoritesStorage> { SharedPrefsFavoritesStorage(get(), get()) }
}

private fun getOkHttpClient(): OkHttpClient {
    return OkHttpClient().newBuilder().apply {
        readTimeout(10, TimeUnit.SECONDS)
        connectTimeout(5, TimeUnit.SECONDS)
        addInterceptor { chain ->
            val request = chain.request().newBuilder().addHeader("x-api-key", "213ea3d8-5ffc-49fb-a1ca-1baa37307f85").build()
            chain.proceed(request)
        }

    }.build()
}