package com.k4tr1n4.marvelcomics.comics.data.repository

import com.k4tr1n4.marvelcomics.comics.data.remote.MLDataStore
import com.k4tr1n4.marvelcomics.comics.data.remote.model.SearchItemModel
import com.k4tr1n4.marvelcomics.comics.domain.repository.MLRepository
import com.k4tr1n4.marvelcomics.core.network.model.LoadingEvent
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