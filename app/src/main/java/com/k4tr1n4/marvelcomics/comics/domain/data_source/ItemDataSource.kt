package com.k4tr1n4.marvelcomics.comics.domain.data_source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.k4tr1n4.marvelcomics.comics.data.remote.model.SearchItemModel.Result
import com.k4tr1n4.marvelcomics.comics.domain.repository.MLRepository
import com.k4tr1n4.marvelcomics.core.network.model.LoadingEvent

class ItemDataSource (
    private val search: String?,
    private val repository: MLRepository
) : PagingSource<Int, Result>() {
    companion object {
        private const val STARTING_PAGE_INDEX = 0
        const val PAGE_SIZE = 10
    }

    override fun getRefreshKey(state: PagingState<Int, Result>): Int? {
        return state.anchorPosition?.let { anchorPosition->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1) ?:
            state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Result> {
        val position = params.key ?: STARTING_PAGE_INDEX
        val offset = position * PAGE_SIZE
        var result: LoadResult<Int, Result> = LoadResult.Error(Exception())
        repository.getSearchItems(offset = offset, query = search).collect{
            result = when(it){
                is LoadingEvent.Error -> LoadResult.Error(it.exception)
                is LoadingEvent.Loading -> LoadResult.Error(Exception())
                is LoadingEvent.Success->
                    LoadResult.Page(
                        data = it.data.results,
                        prevKey = if(position == STARTING_PAGE_INDEX) null else -1,
                        nextKey = if (offset >= (it.data.paging.total)) null else position + 1
                    )
            }
        }

        return result
    }
}