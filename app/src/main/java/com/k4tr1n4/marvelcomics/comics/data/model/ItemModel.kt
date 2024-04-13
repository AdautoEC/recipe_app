package com.k4tr1n4.marvelcomics.comics.data.model

import com.google.gson.annotations.SerializedName

data class ItemModel(
    @SerializedName("data")
    val data: DataModel
){
    data class DataModel(
        @SerializedName("results")
        val results: List<ResultModel>
    ){
        data class ResultModel(
            @SerializedName("title")
            val title: String,
            @SerializedName("description")
            val description: String?,
            @SerializedName("thumbnail")
            val thumbnail: ThumbnailModel
        ){
            data class ThumbnailModel(
                @SerializedName("path")
                val path: String,
                @SerializedName("extension")
                val extension: String,
            )
        }
    }
}