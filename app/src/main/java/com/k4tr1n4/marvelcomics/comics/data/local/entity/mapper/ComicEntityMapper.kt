package com.k4tr1n4.marvelcomics.comics.data.local.entity.mapper

import com.k4tr1n4.marvelcomics.comics.data.local.entity.ComicsEntity
import com.k4tr1n4.marvelcomics.comics.data.remote.model.ItemModel
import com.k4tr1n4.marvelcomics.comics.data.remote.model.ItemModel.DataModel
import com.k4tr1n4.marvelcomics.comics.data.remote.model.ItemModel.DataModel.ResultModel
import com.k4tr1n4.marvelcomics.comics.domain.model.ComicsModel
import com.k4tr1n4.marvelcomics.comics.domain.use_case.GetComicsUseCase.Companion.DEFAULT_DESCRIPTION
import com.k4tr1n4.marvelcomics.comics.domain.use_case.getFullPath
import com.k4tr1n4.marvelcomics.comics.domain.use_case.getThumbnailModel

object ComicEntityMapper: EntityMapper<ItemModel, List<ComicsEntity>> {
    override fun asEntity(domain: ItemModel): List<ComicsEntity> =
        domain.data.results.map { comic ->
            ComicsEntity(
                title = comic.title,
                description = comic.description?: DEFAULT_DESCRIPTION,
                pathImg = comic.thumbnail.getFullPath()
            )
        }


    override fun asDomain(entity: List<ComicsEntity>): ItemModel =
        ItemModel(
            data = DataModel(
                offset = null,
                limit = null,
                total = null,
                count = null,
                results = entity.map {comic ->
                    ResultModel(
                        title = comic.title,
                        description = comic.description,
                        thumbnail = comic.pathImg.getThumbnailModel()
                    )
                }
            )
        )
}

fun ItemModel.asEntity(): List<ComicsEntity> = ComicEntityMapper.asEntity(this)

fun List<ComicsEntity>.asDomain(): ItemModel = ComicEntityMapper.asDomain(this)
