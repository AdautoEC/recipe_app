package com.k4tr1n4.marvelcomics.comics.domain.repository

import com.k4tr1n4.marvelcomics.comics.data.remote.model.SearchItemModel
import com.k4tr1n4.marvelcomics.core.network.model.LoadingEvent
import kotlinx.coroutines.flow.Flow

interface MLRepository {
    fun getSearchItems(
        query: String?,
        offset: Int
    ): Flow<LoadingEvent<SearchItemModel>>
}