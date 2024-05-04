package com.k4tr1n4.marvelcomics.comics.data.remote

import com.k4tr1n4.marvelcomics.comics.data.remote.model.ItemModel
import com.k4tr1n4.marvelcomics.core.network.model.LoadingEvent
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import retrofit2.http.Query

interface ComicService {
    @GET("comics")
    fun getComics(@Query("offset") offset: Int? = 0): Flow<LoadingEvent<ItemModel>>
}