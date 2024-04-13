package com.k4tr1n4.marvelcomics.comics.data.remote

import com.k4tr1n4.marvelcomics.comics.data.model.ItemModel
import com.k4tr1n4.marvelcomics.core.network.model.LoadingEvent
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET

interface ComicService {
    @GET("comics")
    fun getComics(): Flow<LoadingEvent<ItemModel>>
}