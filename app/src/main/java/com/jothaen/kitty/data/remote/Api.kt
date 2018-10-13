package com.jothaen.kitty.data.remote

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface CatsApi {
    @GET("images/search")
    fun getCats(@Query("limit") limit: Int, @Query("mime_types") mimeTypes: String): Observable<List<KittyImageDto>>
}