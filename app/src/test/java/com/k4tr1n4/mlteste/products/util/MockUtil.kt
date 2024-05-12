package com.k4tr1n4.mlteste.products.util

import com.k4tr1n4.mlteste.products.data.remote.model.ItemModel
import com.k4tr1n4.mlteste.products.data.remote.model.ItemModel.DataModel
import com.k4tr1n4.mlteste.products.data.remote.model.ItemModel.DataModel.ResultModel
import com.k4tr1n4.mlteste.products.data.remote.model.ItemModel.DataModel.ResultModel.ThumbnailModel
import com.k4tr1n4.mlteste.products.domain.model.MLItemModel


object MockUtil {
    fun mockItemModel() = ItemModel(
        data = DataModel(
            results = listOf(
                ResultModel(
                    title = "Título do Quadrinho",
                    description = "Descrição do Quadrinho",
                    thumbnail = ThumbnailModel(
                        path = "caminho/para/imagem",
                        extension = "jpg"
                    )
                )
            )
        )
    )

    fun mockComicsModel() = MLItemModel(
        title = "Título do Quadrinho",
        description = "Descrição do Quadrinho",
        pathImg = "caminho/para/imagem.jpg"
    )

    fun mockComicsListModel() = listOf(MLItemModel(
        title = "Título do Quadrinho",
        description = "Descrição do Quadrinho",
        pathImg = "caminho/para/imagem.jpg"
    ))

    fun mockThrowable() = Throwable(message = "error")

}