package com.k4tr1n4.mlteste.products.data.remote

import com.k4tr1n4.mlteste.products.data.remote.model.SearchItemModel
import com.k4tr1n4.mlteste.core.network.model.LoadingEvent
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import retrofit2.http.Query

interface MLService {
    @GET("search")
    fun getSearchItems(
        @Query("q") query: String? = null,
        @Query("offset") offset: Int? = 0,
    ): Flow<LoadingEvent<SearchItemModel>>
}