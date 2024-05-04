package com.k4tr1n4.marvelcomics.comics.domain.data_source

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.k4tr1n4.marvelcomics.comics.domain.data_source.ItemDataSource.Companion.PAGE_SIZE
import com.k4tr1n4.marvelcomics.comics.domain.repository.ComicsRepository
import javax.inject.Inject

class ItemDataSourceFactory @Inject constructor(
    private val repository: ComicsRepository
) {

    fun getPassengers() = Pager(
        config = PagingConfig(
            pageSize = PAGE_SIZE,
            enablePlaceholders = false
        ),
        pagingSourceFactory = {ItemDataSource(repository)}
    ).flow
}