package com.k4tr1n4.marvelcomics.comics.domain.use_case

import androidx.paging.PagingData
import androidx.paging.map
import com.k4tr1n4.marvelcomics.comics.data.remote.model.ItemModel.DataModel.ResultModel.ThumbnailModel
import com.k4tr1n4.marvelcomics.comics.domain.data_source.ItemDataSourceFactory
import com.k4tr1n4.marvelcomics.core.network.model.LoadingEvent
import com.k4tr1n4.marvelcomics.core.network.model.mapSuccess
import com.k4tr1n4.marvelcomics.comics.domain.model.ComicsModel
import com.k4tr1n4.marvelcomics.comics.domain.repository.ComicsRepository
import com.k4tr1n4.marvelcomics.comics.domain.use_case.GetComicsUseCase.Companion.HTTPS
import com.k4tr1n4.marvelcomics.comics.domain.use_case.GetComicsUseCase.Companion.HTTP
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.mapLatest
import javax.inject.Inject

class GetComicsUseCase @Inject constructor(
    private val dataSource: ItemDataSourceFactory,
) {
    companion object{
        const val DEFAULT_DESCRIPTION = ""
        const val HTTP = "http://"
        const val HTTPS = "https://"
    }
    operator fun invoke(): Flow<PagingData<ComicsModel>> {
        return dataSource.getPassengers().mapLatest {pagingData ->
            pagingData.map {result ->
                ComicsModel(
                    title = result.title,
                    description = result.description?: DEFAULT_DESCRIPTION,
                    pathImg = result.thumbnail.getFullPath(),
                )
            }
        }
    }
}

fun ThumbnailModel.getFullPath() = "$path.$extension".replace(HTTP, HTTPS)
fun String.getThumbnailModel(): ThumbnailModel {
    val values = this.split(".")
    if(values.size == 2)
        return ThumbnailModel(
            path = values[0].replace(".", ""),
            extension = values[1]
        )
    return ThumbnailModel(path = "", extension = "")
}