package com.k4tr1n4.mlteste.products.util

import androidx.paging.LoadState
import androidx.paging.LoadStates
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.k4tr1n4.mlteste.products.data.remote.model.SearchItemModel
import com.k4tr1n4.mlteste.products.domain.model.MLItemModel
import okio.buffer
import okio.source
import java.lang.reflect.Type
import java.nio.charset.StandardCharsets


object MockUtil {
    private inline fun <reified T> fromJson(fileName: String): T {
        val inputStream = javaClass.classLoader!!.getResourceAsStream("api-response/$fileName")
        val source = inputStream.source().buffer()
        val type: Type = object : TypeToken<T>() {}.type
        return Gson().fromJson(source.readString(StandardCharsets.UTF_8), type)
    }
    fun mockSearchItemModel(): SearchItemModel = fromJson("/SearchResponse.json")

    fun mockMLItemModel(): MLItemModel = mockSearchItemModel().results[0].toMLItemModel()
    fun mockMLList(): List<MLItemModel> = mockSearchItemModel().results.map {
        it.toMLItemModel()
    }

    private fun SearchItemModel.Result.toMLItemModel(): MLItemModel {
        return MLItemModel(
            title = title,
            price = price,
            pathImg = thumbnail,
            details = MLItemModel.Details(
                title = title,
                price = price,
                pathImg = thumbnail,
                permalink = permalink,
                condition = condition,
                availableQuantity = availableQuantity,
                moreDetails = attributes.map { attribute ->
                    MLItemModel.Details.MoreDetails(
                        name = attribute.name,
                        value = attribute.valueName ?: "N/A"
                    )
                }
            )
        )
    }
    fun mockThrowable() = Throwable("default error")

    fun mockLoadStatesError() =
        LoadStates(
            refresh = LoadState.Error(mockThrowable()),
            prepend = LoadState.Error(mockThrowable()),
            append = LoadState.Error(mockThrowable())
        )

    fun mockLoadStatesLoading() =
        LoadStates(
            refresh = LoadState.Loading,
            prepend = LoadState.Loading,
            append = LoadState.Loading
        )
}