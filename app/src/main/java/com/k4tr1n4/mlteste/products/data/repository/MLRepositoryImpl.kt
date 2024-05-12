package com.k4tr1n4.mlteste.products.data.repository

import com.k4tr1n4.mlteste.products.data.remote.MLDataStore
import com.k4tr1n4.mlteste.products.data.remote.model.SearchItemModel
import com.k4tr1n4.mlteste.products.domain.repository.MLRepository
import com.k4tr1n4.mlteste.core.network.model.LoadingEvent
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MLRepositoryImpl @Inject constructor(
    private val dataStore: MLDataStore,
): MLRepository {

    override fun getSearchItems(
        query: String?,
        offset: Int
    ): Flow<LoadingEvent<SearchItemModel>> = dataStore.getSearchItems(query, offset)

}