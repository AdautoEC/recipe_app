package com.k4tr1n4.marvelcomics.comics.domain.use_case

import com.k4tr1n4.marvelcomics.comics.data.remote.model.ItemModel.DataModel.ResultModel.ThumbnailModel
import com.k4tr1n4.marvelcomics.core.network.model.LoadingEvent
import com.k4tr1n4.marvelcomics.core.network.model.mapSuccess
import com.k4tr1n4.marvelcomics.comics.domain.model.ComicsModel
import com.k4tr1n4.marvelcomics.comics.domain.repository.ComicsRepository
import com.k4tr1n4.marvelcomics.comics.domain.use_case.GetComicsUseCase.Companion.HTTPS
import com.k4tr1n4.marvelcomics.comics.domain.use_case.GetComicsUseCase.Companion.HTTP
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetComicsUseCase @Inject constructor(private val repository: ComicsRepository) {
    companion object{
        const val DEFAULT_DESCRIPTION = ""
        const val HTTP = "http://"
        const val HTTPS = "https://"
    }
    operator fun invoke(): Flow<LoadingEvent<List<ComicsModel>>> {
        return repository.getComics().mapSuccess {
            it.data.results.map { results ->
                ComicsModel(
                    title = results.title,
                    description = results.description?: DEFAULT_DESCRIPTION,
                    pathImg = results.thumbnail.getFullPath(),
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