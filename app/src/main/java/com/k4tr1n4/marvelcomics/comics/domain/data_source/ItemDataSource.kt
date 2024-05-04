package com.k4tr1n4.marvelcomics.comics.domain.data_source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.k4tr1n4.marvelcomics.comics.data.remote.model.ItemModel.DataModel.ResultModel
import com.k4tr1n4.marvelcomics.comics.domain.repository.ComicsRepository
import com.k4tr1n4.marvelcomics.core.network.model.LoadingEvent

class ItemDataSource (
    private val repository: ComicsRepository
) : PagingSource<Int, ResultModel>() {
    companion object {
        private const val STARTING_PAGE_INDEX = 0
        const val PAGE_SIZE = 20
    }

    override fun getRefreshKey(state: PagingState<Int, ResultModel>): Int? {
        return state.anchorPosition?.let { anchorPosition->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1) ?:
            state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ResultModel> {
        val position = params.key ?: STARTING_PAGE_INDEX
        val offset = position * PAGE_SIZE
        var result: LoadResult<Int, ResultModel> = LoadResult.Error(Exception())
        repository.getComics(offset = offset).collect{
            result = when(it){
                is LoadingEvent.Error -> LoadResult.Error(it.exception)
                is LoadingEvent.Loading -> LoadResult.Error(Exception())
                is LoadingEvent.Success->
                    LoadResult.Page(
                        data = it.data.data.results,
                        prevKey = if(position == STARTING_PAGE_INDEX) null else -1,
                        nextKey = if (offset >= (it.data.data.total ?: 0)) null else position + 1
                    )
            }
        }

        return result
    }
}