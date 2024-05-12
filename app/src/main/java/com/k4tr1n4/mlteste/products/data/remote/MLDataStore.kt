package com.k4tr1n4.mlteste.products.data.remote

import com.k4tr1n4.mlteste.products.data.remote.model.SearchItemModel
import com.k4tr1n4.mlteste.core.network.model.LoadingEvent
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MLDataStore @Inject constructor(
    private val service: MLService
) {
    fun getSearchItems(
        query: String?,
        offset: Int?
    ): Flow<LoadingEvent<SearchItemModel>> { return service.getSearchItems(query, offset) }
}