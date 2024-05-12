package com.k4tr1n4.mlteste.products.domain.data_source

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.k4tr1n4.mlteste.products.domain.data_source.ItemDataSource.Companion.PAGE_SIZE
import com.k4tr1n4.mlteste.products.domain.repository.MLRepository
import javax.inject.Inject

class ItemDataSourceFactory @Inject constructor(
    private val repository: MLRepository
) {

    fun getItems(search: String?) = Pager(
        config = PagingConfig(
            pageSize = PAGE_SIZE,
            enablePlaceholders = false
        ),
        pagingSourceFactory = {
            ItemDataSource(repository = repository, search = search)
        }
    ).flow
}