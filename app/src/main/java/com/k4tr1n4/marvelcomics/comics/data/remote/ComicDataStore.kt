package com.k4tr1n4.marvelcomics.comics.data.remote

import com.k4tr1n4.marvelcomics.comics.data.model.ItemModel
import com.k4tr1n4.marvelcomics.core.network.model.LoadingEvent
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ComicDataStore @Inject constructor(
    private val service: ComicService
) {
    fun getRecipe(): Flow<LoadingEvent<ItemModel>> {
        return service.getComics()
    }
}