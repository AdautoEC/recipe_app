package com.k4tr1n4.mlteste.products.domain.repository

import com.k4tr1n4.mlteste.products.data.remote.model.SearchItemModel
import com.k4tr1n4.mlteste.core.network.model.LoadingEvent
import kotlinx.coroutines.flow.Flow

interface MLRepository {
    fun getSearchItems(
        query: String?,
        offset: Int
    ): Flow<LoadingEvent<SearchItemModel>>
}