package com.jothaen.kitty.usecase

import com.jothaen.kitty.data.remote.CatsApi
import com.jothaen.kitty.data.remote.KittyImageDto
import com.jothaen.kitty.extensions.applyStandardSchedulers
import io.reactivex.Observable

interface GetRandomCatUseCase {
    fun get(): Observable<KittyImageDto>
}

class GetRandomCatUseCaseImpl(private val catsApi: CatsApi) : GetRandomCatUseCase {
    override fun get(): Observable<KittyImageDto> = catsApi
            .getCats(limit = 1, mimeTypes = "png")
            .applyStandardSchedulers()
            .map { it.first() }
}