package com.k4tr1n4.marvelcomics.comics.domain.repository

import com.k4tr1n4.marvelcomics.comics.data.remote.model.ItemModel
import com.k4tr1n4.marvelcomics.core.network.model.LoadingEvent
import kotlinx.coroutines.flow.Flow

interface ComicsRepository {
    fun getComics(): Flow<LoadingEvent<ItemModel>>
}