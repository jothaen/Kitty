package com.jothaen.kitty.usecase

import com.jothaen.kitty.data.local.KittyImage
import com.jothaen.kitty.data.remote.CatsApi
import com.jothaen.kitty.data.remote.KittyImageDto
import com.jothaen.kitty.db.FavoritesStorage
import com.jothaen.kitty.extensions.applyStandardSchedulers
import io.reactivex.Observable

interface GetRandomCatUseCase {
    fun get(): Observable<KittyImage>
}

class GetRandomCatUseCaseImpl(private val catsApi: CatsApi, private val favoritesStorage: FavoritesStorage)
    : GetRandomCatUseCase {
    override fun get(): Observable<KittyImage> = catsApi
            .getCats(limit = 1, mimeTypes = "png")
            .applyStandardSchedulers()
            .map { it.first() }
            .map { dtoToModel(it) }

    private fun dtoToModel(kittyImageDto: KittyImageDto): KittyImage = with(kittyImageDto) {
        val isLiked = favoritesStorage.exists(id)
        return KittyImage(id, url, isLiked)
    }
}